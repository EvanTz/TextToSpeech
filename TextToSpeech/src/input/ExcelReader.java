package input;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader implements DocumentReader{
	
	private String docPath;

	public ExcelReader(String docPath) {
		this.docPath = docPath;
		
	}

	public List<String> read(){
		List<String> content = new ArrayList<String>();
		FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(new File(docPath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator <Row> iterator = datatypeSheet.iterator();
			
			while(iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					
					if(currentCell.getCellType() == CellType.STRING) {
						content.add(currentCell.getStringCellValue());
					}
					else if(currentCell.getCellType() == CellType.NUMERIC) {
						content.add(currentCell.getStringCellValue());
					}
				}	
			}	
		workbook.close();
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
		return content;
	}
}
