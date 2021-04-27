package Requirements;

import java.io.File;


import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	static XSSFSheet sheet1;
	static XSSFWorkbook ExcelWBook;
	
	//Setting up path to Excel Sheet	
	public static void setExcelFile() throws Exception {
			
		//String path ="C:\\Users\\HP LAPTOP\\Desktop\\Data.xlsx";  
		String path ="C:\\Users\\HP LAPTOP\\eclipse-workspace\\2020-12\\Mentor_Project\\Excel Sheet\\Data.xlsx";//path of Excel sheet
		File src = new File(path);                                //Create an object of File class to read Excel file
		FileInputStream fis = new FileInputStream(src);           //Create an object of FileInputStream class to read excel file
		ExcelWBook = new XSSFWorkbook(fis);
	}
	
	//Method to get data from Excel sheet
	public static String getCellData(int i, int j) {	
		sheet1 = ExcelWBook.getSheetAt(0);                      		//Getting the first sheet
		String data = sheet1.getRow(i).getCell(j).getStringCellValue(); //Getting the value of the "ith" row "jth" column
		return data;                                             		//returning the value
	}

}
