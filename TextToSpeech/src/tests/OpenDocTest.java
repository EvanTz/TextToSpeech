package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.OpenDocument;
import input.DocumentReader;
import input.ExcelReader;
import input.ReaderAtbashDecorator;
import input.ReaderRot13Decorator;
import input.WordReader;
import model.Document;

class OpenDocTest {
	private static List<String> wordReaderExpectedOut;
	private static List<String> wordReaderExpectedOutSmall;
	private static List<String> excelReaderExpectedOut;
	private DocumentReader wordReader;
	private DocumentReader excelReader;
	private ReaderAtbashDecorator readerAtbashDecorator;
	private ReaderRot13Decorator readerRot13Decorator;
	private OpenDocument openDocument;
	private Document doc;
	private String sampleWordDoc;
	private String sampleExcelDoc;
	private String sampleInput;
	
	private void setupAtbash() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleAtbashEncodingSmall.docx");
		wordReader = new WordReader(sampleInput);
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleAtbash.xlsx");
		excelReader = new ExcelReader(sampleInput);
	}
	
	private void setupRot13() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleRot13EncodingSmall.docx");
		wordReader = new WordReader(sampleInput);
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleSaveRot13.xlsx");
		excelReader = new ExcelReader(sampleInput);
	
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		wordReaderExpectedOut = new ArrayList<String>();
		wordReaderExpectedOut.add("Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
				+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		
		wordReaderExpectedOutSmall = new ArrayList<String>(Arrays.asList("Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
		
		excelReaderExpectedOut = new ArrayList<>(Arrays.asList(
				"column0,column1,column2",
				"row1,row1 col1,row1 col2",
				"row2,row2 col1,row2 col2",
				"row3,row3 col1,row3 col2"));
	}

	@BeforeEach
	void setUp() throws Exception {
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	public final void testWordReader() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		wordReader = new WordReader(sampleInput);
		assertEquals(wordReaderExpectedOut, wordReader.read());
	}
	
	@Test
	public final void testExcelReader() {
		String sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		excelReader = new ExcelReader(sampleInput);
		assertEquals(excelReaderExpectedOut,excelReader.read());
	}
	
	@Test
	public final void testAtBashDecoderForWord() {
		setupAtbash();
		readerAtbashDecorator = new ReaderAtbashDecorator(wordReader);
		assertEquals(wordReaderExpectedOutSmall, readerAtbashDecorator.read());
	}
	
	@Test
	public final void testAtBashDecoderForExcel() {
		setupAtbash();
		readerAtbashDecorator = new ReaderAtbashDecorator(excelReader);
		assertEquals(excelReaderExpectedOut, readerAtbashDecorator.read());
	}
	
	@Test
	public final void testRot13DecoderForWord() {
		setupRot13();
		readerRot13Decorator = new ReaderRot13Decorator(wordReader);
		assertEquals(wordReaderExpectedOutSmall, readerRot13Decorator.read());
	}
	
	@Test
	public final void testRot13DecoderForExcel() {
		setupRot13();
		readerRot13Decorator = new ReaderRot13Decorator(excelReader);
		assertEquals(excelReaderExpectedOut, readerRot13Decorator.read());
	}
	
	@Test
	public final void testOpenDocWord() {
		openDocument = new OpenDocument();
		doc = new Document();
		openDocument.setDocument(doc);
		
		// test opening of normal text document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSample.docx");
		openDocument.openDoc(sampleInput, "docx", "None");
		assertEquals(wordReaderExpectedOut, doc.getContents());
		
		// test opening of atbash encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleAtbashEncoding.docx");
		openDocument.openDoc(sampleInput, "docx", "Atbash");
		assertEquals(wordReaderExpectedOut, doc.getContents());
		
		// test opening of rot13 encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\LoremIpsumSampleRot13Encoding.docx");
		openDocument.openDoc(sampleInput, "docx", "Rot13");
		assertEquals(wordReaderExpectedOut, doc.getContents());
	}
	
	@Test
	public final void testOpenDocExcel() {
		openDocument = new OpenDocument();
		doc = new Document();
		openDocument.setDocument(doc);
		
		// test opening of normal text document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSample.xlsx");
		openDocument.openDoc(sampleInput, "xlsx", "None");
		assertEquals(excelReaderExpectedOut, doc.getContents());
		
		// test opening of atbash encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleAtbash.xlsx");
		openDocument.openDoc(sampleInput, "xlsx", "Atbash");
		assertEquals(excelReaderExpectedOut, doc.getContents());
		
		// test opening of rot13 encoded document
		sampleInput = (System.getProperty("user.dir")+"\\Resources\\InputSamples\\ExcelSampleSaveRot13.xlsx");
		openDocument.openDoc(sampleInput, "xlsx", "Rot13");
		assertEquals(excelReaderExpectedOut, doc.getContents());
	}
	
}
