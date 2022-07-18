package com.generic_library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class FileLibrary {

	//getting key value of property file
	public String getPropertyOf(String path, String key) throws IOException {

		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(path);
		p.load(fis);
		String prop = p.getProperty(key);
		return prop;
	}
	
	//updating key value of property file
	public void setPropertyOf(String path,String key,String value,String feedback) throws IOException {

		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream(path,true);
		p.setProperty(key,value);
		p.store(fos, feedback);
		
	}

	//read data from excel file
	public String readDataFrom(String path, String sheetName,int row,int cell) throws IOException {

		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		fis.close();
		DataFormatter df = new DataFormatter();
		String data = df.formatCellValue(sheet.getRow(row).getCell(cell));
		return data;
	}
	
	// write data into excel file
	public void writeDataTo(String path,String sheet,int cell,String data) throws IOException {
		
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sh = workbook.getSheet(sheet);
		int noOfRows = sh.getPhysicalNumberOfRows();
		sh.createRow(noOfRows+1).createCell(cell).setCellValue(data);
		FileOutputStream fos = new FileOutputStream(path);
		fos.flush();
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	
	// screenshot by Base64 method
		public String captureShotAsB64(WebDriver driver) throws IOException {
		String base64Code = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return base64Code;
	}

	// screenshot
		public String captureShotOf(WebDriver driver, String imgName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File trgt = new File("./CAS-screenshots/" + imgName + ".jpg");
		FileUtils.copyFile(src, trgt);
		return trgt.getAbsolutePath();
	}
		
		@DataProvider(name="ApiUsers")
		public String[][] dataFrom() throws IOException {

			FileInputStream fis = new FileInputStream("./src/test/resources/data/dataProviderSheet01.xlsx");
			
			Workbook workbook =  WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("data02");
			int rows = sheet.getPhysicalNumberOfRows();
			int cellNum = sheet.getRow(0).getLastCellNum();
			
			String[][] data = new String[rows-1][cellNum];
			
			for(int i=0;i<rows-1;i++) {
				for(int j=0;j<cellNum;j++) {
					DataFormatter df = new DataFormatter();
					data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
				}
			}
			workbook.close();
			fis.close();
			
			return data;
		}

		
		
	
	
	
	
	
}
