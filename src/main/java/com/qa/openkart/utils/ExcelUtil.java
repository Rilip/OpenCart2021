package com.qa.openkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



//import org.apache.poi.ss.usermodel.Sheet;   // apache.poi


public class ExcelUtil {

	//private static final String TEST_DATA_SHEET_PATH=" ./src/test/resources/testdata/OpenkartAppTestData.xlsx";
	private static final String TEST_DATA_SHEET_PATH =  "./src/test/resources/testdata/opencartapptestdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetname) {
		
		Object data[][];  //2-d
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetname);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//data=new Object[4][6];  //hardcoded values
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//to fill the data
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
	}
	
	
}
