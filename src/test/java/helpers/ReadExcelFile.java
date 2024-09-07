package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ReadExcelFile {

    public String readExcel(String filePath, String sheetName, String header) throws IOException {
        File file = new File(filePath);
        String value = "";
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newXssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newXssfWorkbook.getSheet(sheetName);

        int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();

        for (int i = 0; i <= rowCount; i++){

            XSSFRow row = newSheet.getRow(i);
            XSSFRow row2 = newSheet.getRow(i+1);

            for (int j = 0; j < row.getLastCellNum(); j++){
                System.out.println(row.getCell(j).getStringCellValue() + "||");
                if(row.getCell(j).getStringCellValue().equals(header)){
                    value = row2.getCell(j).getStringCellValue();
                }
            }
        }
        return value;
    }

    public String getCellValue(String filePath, String sheetName, int rowNumber, int cellNumber) throws IOException{
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newsheet = newWorkbook.getSheet(sheetName);
        XSSFRow row = newsheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(cellNumber);

        return cell.getStringCellValue();
    }

    public String dataValue(String filePath, String sheetName, int rowNumber, int cellNumber) throws IOException{
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newsheet = newWorkbook.getSheet(sheetName);
        XSSFRow row = newsheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(cellNumber);

        return cell.getStringCellValue();
    }
}
