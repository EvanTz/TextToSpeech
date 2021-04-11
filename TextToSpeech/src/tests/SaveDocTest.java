package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.OpenDocument;
import commands.SaveDocument;
import input.DocumentReader;
import input.ExcelReader;
import input.ReaderAtbashDecorator;
import input.ReaderRot13Decorator;
import input.WordReader;
import model.Document;
import output.DocumentWriter;
import output.ExcelWriter;
import output.WordWriter;
import output.WriterAtbashDecorator;
import output.WriterRot13Decorator;

class SaveDocTest {

	private static ArrayList<String> wordWriterInput;
	private static ArrayList<String> wordWriterInputSmall;
	private static ArrayList<String> excelWriterInput;
	private DocumentWriter wordWriter;
	private DocumentWriter excelWriter;
	private DocumentReader wordReader;
	private ExcelReader excelReader;
	private WriterAtbashDecorator writerAtBashDecorator;
	private List<String> encodedContents;
	private ReaderAtbashDecorator readerAtBashDecorator;
	private WriterRot13Decorator writerRot13Decorator;
	private ReaderRot13Decorator readerRot13Decorator;
	private Document doc;
	private SaveDocument saveDocument;
	private OpenDocument openDocument;
	private String sampleInput;
	private Document doc1;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		wordWriterInput = new ArrayList<String>();
		wordWriterInput.add("Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
				+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		
		wordWriterInputSmall = new ArrayList<String>(Arrays.asList("Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
		
		excelWriterInput = new ArrayList<>(Arrays.asList(
				"column0,column1,column2",
				"row1,row1 col1,row1 col2",
				"row2,row2 col1,row2 col2",
				"row3,row3 col1,row3 col2"));
	}

	

	@BeforeEach
	void setUp() throws Exception {
	}
/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	@Test
	public final void testWordWriter() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		wordWriter = new WordWriter(sampleInput, wordWriterInput);
		wordReader = new WordReader(sampleInput);
		assertEquals(wordWriterInput, wordReader.read());	
	}
	
	@Test
	public final void testExcelWriter() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		excelWriter = new ExcelWriter(sampleInput, excelWriterInput);
		excelReader = new ExcelReader(sampleInput);
		assertEquals(excelWriterInput, excelReader.read());
	}

	@Test 
	public final void testWriterAtBashDecoderForWord() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleAtbashEncodingSmall.docx");
		writerAtBashDecorator = new WriterAtbashDecorator(wordWriterInputSmall);
		writerAtBashDecorator.write();
		encodedContents = writerAtBashDecorator.getOutput();
		wordWriter = new WordWriter(sampleInput, encodedContents);
		wordReader = new WordReader(sampleInput);
		readerAtBashDecorator = new ReaderAtbashDecorator(wordReader);
		assertEquals(wordWriterInputSmall,readerAtBashDecorator.read());
		
	}
	@Test 
	public final void testWriterAtBashDecocoderForExcel() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleAtbash.xlsx");
		writerAtBashDecorator = new WriterAtbashDecorator(excelWriterInput);
		writerAtBashDecorator.write();
		encodedContents = writerAtBashDecorator.getOutput();
		excelWriter = new ExcelWriter(sampleInput, encodedContents);
		excelReader = new ExcelReader(sampleInput);
		readerAtBashDecorator = new ReaderAtbashDecorator(excelReader);
		assertEquals(excelWriterInput,readerAtBashDecorator.read());
	}
	
	@Test
	public final void testWriterRot13DecoderForWord() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleRot13EncodingSmall.docx");
		writerRot13Decorator = new WriterRot13Decorator(wordWriterInputSmall);
		writerRot13Decorator.write();
		encodedContents = writerRot13Decorator.getOutput();
		wordWriter = new WordWriter(sampleInput, encodedContents);
		wordReader = new WordReader(sampleInput);
		readerRot13Decorator = new ReaderRot13Decorator(wordReader);
		assertEquals(wordWriterInputSmall,readerRot13Decorator.read());
		
	}
	@Test 
	public final void testWriterRot13DeocoderForExcel() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleSaveRot13.xlsx");
		writerRot13Decorator = new WriterRot13Decorator(excelWriterInput);
		writerRot13Decorator.write();
		encodedContents = writerRot13Decorator.getOutput();
		excelWriter = new ExcelWriter(sampleInput, encodedContents);
		excelReader = new ExcelReader(sampleInput);
		readerRot13Decorator = new ReaderRot13Decorator(excelReader);
		assertEquals(excelWriterInput,readerRot13Decorator.read());
	}
	@Test
	public final void testSaveDocWord() {
		saveDocument = new SaveDocument();
		doc = new Document();
		saveDocument.setDocument(doc);
		doc.setContents(wordWriterInput);
		
		openDocument = new OpenDocument();
		doc1 = new Document();
		openDocument.setDocument(doc1);
		
		// test saving of normal text document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		doc.save(sampleInput, "docx", "None");
		openDocument.openDoc(sampleInput, "docx", "None");
		assertEquals(wordWriterInput, doc1.getContents());
		
		
		//test saving of atbach encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleAtbashEncoding.docx");
		doc.save(sampleInput, "docx", "Atbash");
		openDocument.openDoc(sampleInput, "docx", "Atbash");
		assertEquals(wordWriterInput, doc1.getContents());
		
		//test saving of rot13 encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleRot13Encoding.docx");
		doc.save(sampleInput, "docx", "Rot13");
		openDocument.openDoc(sampleInput, "docx", "Rot13");
		assertEquals(wordWriterInput, doc1.getContents());

	}
	
	@Test
	public final void testSaveDocExcel() {
		saveDocument = new SaveDocument();
		doc = new Document();
		saveDocument.setDocument(doc);
		doc.setContents(excelWriterInput);
		
		openDocument = new OpenDocument();
		doc1 = new Document();
		openDocument.setDocument(doc1);
		
		// test saving of normal text document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		doc.save(sampleInput, "xlsx", "None");
		openDocument.openDoc(sampleInput, "xlsx", "None");
		assertEquals(excelWriterInput, doc1.getContents());
		
		//test saving of atbach encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleAtbash.xlsx");
		doc.save(sampleInput, "xlsx", "Atbash");
		openDocument.openDoc(sampleInput, "xlsx", "Atbash");
		assertEquals(excelWriterInput, doc1.getContents());
		
		//test saving of rot13 encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleSaveRot13.xlsx");
		doc.save(sampleInput, "xlsx", "Rot13");
		openDocument.openDoc(sampleInput, "xlsx", "Rot13");
		assertEquals(excelWriterInput, doc1.getContents());
		
		
	}
	

}
























