package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.DocumentToSpeech;
import model.Document;
import model.FakeTTSFacade;

class DocToSpeechTest {
	private static String text;
	private static String selectedText; 
	private static List<String> textList; 
	private static List<String> tableCells; 
	private static List<String> selectedTableCells;
	private static JTextArea textArea;
	private static DefaultTableModel model;
	private static JTable table;
	private static Document doc;
	private static FakeTTSFacade fakeTTS;
	private static String sampleInput;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		fakeTTS = new FakeTTSFacade();
		doc = new Document();
		doc.setAudioManager(fakeTTS);
		
		textList = new ArrayList<>();
		
		
		text = "Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
				+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		
		textList.add(text);
		
		selectedText = "Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		
		textArea = new JTextArea();
		textArea.setText(text);
		
		model= new DefaultTableModel();
		table = new JTable(model);
		
		tableCells =  new ArrayList<>(Arrays.asList(
				"column0,column1,column2",
				"row1,row1 col1,row1 col2",
				"row2,row2 col1,row2 col2",
				"row3,row3 col1,row3 col2"));
		
		selectedTableCells =  new ArrayList<>(Arrays.asList(
				"column0","column1","column2",
				"row1","row1 col1","row1 col2",
				"row2","row2 col1","row2 col2"));
		
		ArrayList<ArrayList<String>> cells = new ArrayList<>();
		for(String i : tableCells){
			cells.add( new ArrayList<String>(Arrays.asList((i.split(",")))));
		}
		if (!cells.isEmpty()) {
			model.setColumnCount(cells.get(0).size());
		}
		else {
			model.setColumnCount(0);
		}
		
		model.setRowCount(cells.size());
		
		for(List<String> row : cells) {
			for (String col : row) {
				model.setValueAt(col, cells.indexOf(row), row.indexOf(col));
			}
		}
		
		
	}
		
	@Test
	void testDocumentTTStextAreaAllContents() {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		doc.open(sampleInput, "docx", "None");
		textArea.setText(doc.getContents().get(0));
		DocumentToSpeech dts = new DocumentToSpeech();
		dts.setDocument(doc);
		dts.setTextArea(textArea);
		dts.playAllContents();
		assertEquals(text, fakeTTS.getPlayedContents());
		
		// wait before stopping playing contents
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// test that speaking stops
		doc.stopPlayingContents();
		assertEquals(false, fakeTTS.getPlayingStatus());
	}
	
	@Test
	void testDocumentTTStextAreaPartContents() {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		doc.open(sampleInput, "docx", "None");
		textArea.setText(doc.getContents().get(0));
		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(selectedText.length());
		
		DocumentToSpeech dts = new DocumentToSpeech();
		dts.setDocument(doc);
		dts.setTextArea(textArea);
		
		dts.playSelectedContents();
		assertEquals(selectedText, fakeTTS.getPlayedContents());
	}
	
	@Test
	void testDocumentTTSTableAllContents() {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		doc.open(sampleInput, "xlsx", "None");
		doc.setContents(tableCells);
		DocumentToSpeech dts = new DocumentToSpeech();
		dts.setDocument(doc);
		dts.setTable(table);
		dts.setTableModel(model);
		
		dts.playAllContents();
		assertEquals(String.join(" ",tableCells), fakeTTS.getPlayedContents());
	}
	
	@Test
	void testDocumentTTSTablePartContents() {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		doc.open(sampleInput, "xlsx", "None");
		doc.setContents(tableCells);
		
		DocumentToSpeech dts = new DocumentToSpeech();
		dts.setDocument(doc);
		dts.setTable(table);
		dts.setTableModel(model);
		
		table.setRowSelectionInterval(0, 2);
		table.setColumnSelectionInterval(0, 2);
		
		dts.playSelectedContents();
		assertEquals(String.join(" ",selectedTableCells), fakeTTS.getPlayedContents());
	}
	
	@Test
	void testVolumeRatePitch() {
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		doc.open(sampleInput, "docx", "None");
		textArea.setText(doc.getContents().get(0));
		DocumentToSpeech dts = new DocumentToSpeech();
		dts.setDocument(doc);
		dts.setTextArea(textArea);
		
		dts.setVolRatePitch(0.6, 145, 600);
		dts.playAllContents();
		
		assertEquals(145, fakeTTS.getRate());
		assertEquals(600, fakeTTS.getPitch());
		assertEquals(0.6, Math.round(fakeTTS.getVolume() * 100.0)/100.0);
		//assertEquals(0.6, fakeTTS.getVolume());  // this does not work for some reason: expected: 0.6, actual: 0.6000000238418579
	}
	
}


















