package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter  {
	private String docPath;
	private List<String> content;

	public ExcelWriter(String docPath, List<String> content) {
		this.docPath = docPath;
		this.content = content;

	
	}
	@Override
	public void write() {
		ArrayList<ArrayList<String>> cells = new ArrayList<>();
		//File file = new File(docPath);
		//file.delete();
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
       /*
        Object[][] content = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };
		*/
        
        int rowNum = 0;
       
        for(String i : content) {
        	cells.add(new ArrayList<String>(Arrays.asList(i.split(","))));
        }

        for (List<String> line : cells) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : line) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(docPath);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        	
	}

}