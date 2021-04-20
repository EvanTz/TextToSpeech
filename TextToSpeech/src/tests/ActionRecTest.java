package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.ActionRecording;
import commands.DocumentToSpeech;
import commands.ReplayManager;
import model.Document;
import model.FakeTTSFacade;

class ActionRecTest {
	private static Document doc;
	private static FakeTTSFacade fakeTTS;
	private static String sampleInput;
	private static DocumentToSpeech docToSpeech;
	private static ReplayManager rm;
	private static ActionRecording actionRec;
	private static String text1;
	private static String text2;
	private static String text3;
	private static String expected_out;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		
		doc = new Document();
		
		fakeTTS = new FakeTTSFacade();
		
		docToSpeech = new DocumentToSpeech();
		docToSpeech.setDocument(doc);
		
		doc.setAudioManager(fakeTTS);
		doc.open(sampleInput, "docx", "None");
		
		rm = new ReplayManager();
		rm.setDoc(doc);
		
		docToSpeech.setReplayManager(rm);
		
		actionRec = new ActionRecording();
		actionRec.setReplayManager(rm);
		
		text1 = "this is the 1st text to speech command";
		text2 = "this is the 2nd text to speech command";
		text3 = "this is the 3rd text to speech command";
		
		expected_out = text1+" "+text2+" "+text3;
	}

	@Test
	void startRecTest() {
		assertEquals(false, rm.isActiveRecording());
		actionRec.activateRec();
		assertEquals(true, rm.isActiveRecording());
	}
	
	@Test
	void replayRecTest() {
		actionRec.activateRec();
		JTextArea textArea = new JTextArea();
		docToSpeech.setTextArea(textArea);
		
		textArea.setText(text1);
		docToSpeech.playAllContents();
		docToSpeech.setPlayed(false);
		
		textArea.setText(text2);
		docToSpeech.playAllContents();
		docToSpeech.setPlayed(false);
		
		textArea.setText(text3);
		docToSpeech.playAllContents();
		
		actionRec.playRec();
		assertEquals(expected_out, fakeTTS.getPlayedContents());
		
	}
	
	@Test
	void endRecTest() {
		actionRec.activateRec();
		assertEquals(true, rm.isActiveRecording());
		actionRec.endRec();
		assertEquals(false, rm.isActiveRecording());
	}

}

















