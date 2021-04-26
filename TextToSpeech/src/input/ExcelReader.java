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
		String rowString = "";
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
				
				rowString = "";
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();

					if(currentCell.getCellType() == CellType.STRING) {
						rowString += currentCell.getStringCellValue() + ",";
					}
					else if(currentCell.getCellType() == CellType.NUMERIC) {
						rowString +=currentCell.getStringCellValue() + ",";
					}
					else {
						rowString +=" " + ",";
					}
				}
				
				content.add(rowString.substring(0,rowString.length()-1));
			}	
		workbook.close();
		} catch (FileNotFoundException e) {
			
			//  Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} 
		
		return content;
	}
	
}
