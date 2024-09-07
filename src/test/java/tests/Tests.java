package tests;

import helpers.ReadExcelFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageLogin;
import pages.PageRegister;

import java.io.IOException;

import static helpers.Screenshooter.takeScreenshot;
import static helpers.WebDriverManager.goToURL;
import static helpers.WebDriverManager.setWindowsSize;

public class Tests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        setWindowsSize("maximized", driver);
        goToURL("https://demo.guru99.com/test/newtours/", driver);
    }

    @Test(description = "Ejecución de login success")
    public void testLogin() throws IOException {
        ReadExcelFile readExcelFile = new ReadExcelFile();


        String urlFile = "C:\\Users\\PREDATOR\\Documents\\users.xlsx";
        String sheetName = "Sheet1";
        String colUser = readExcelFile.readExcel(urlFile, sheetName, "User");
        System.out.println("Valor de la columna User: " + colUser);

        String excelUser = readExcelFile.getCellValue(urlFile, sheetName, 0, 0);
        String excelPassword = readExcelFile.getCellValue(urlFile, sheetName, 0, 1);


        System.out.println("Valor de la celda 0,0 : " + excelUser);
        System.out.println("Valor de la celda 0,1 : " + excelPassword);

        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.loginM(excelUser,excelPassword);
        By elementIdentifier = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3");
        String valueIdentifier = driver.findElement(elementIdentifier).getText().trim();
        Assert.assertEquals(valueIdentifier, "Login Successfully", "El valor actual no coincide con el valor esperado");
    }

    @Test(description = "Registro de nuevo usuario")
    public void testRegister() throws IOException {
        ReadExcelFile readExcelFile = new ReadExcelFile();
        String urlFile = "C:\\Users\\PREDATOR\\Documents\\users.xlsx";
        String sheetName = "Sheet2";

        String excelUser = readExcelFile.getCellValue(urlFile, sheetName, 0, 0);
        String excelPassword = readExcelFile.getCellValue(urlFile, sheetName, 0, 1);
        String excelcol3 = readExcelFile.getCellValue(urlFile, sheetName, 0, 2);
        String excelcol4 = readExcelFile.getCellValue(urlFile, sheetName, 0, 3);


        System.out.println("Valor de la celda (hoja2) 0,0 : " + excelUser);
        System.out.println("Valor de la celda (hoja2) 0,1 : " + excelPassword);
        System.out.println("Valor de la celda (hoja2) 0,2 : " + excelcol3);
        System.out.println("Valor de la celda (hoja2) 0,3 : " + excelcol4);

        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.loginM(excelUser,excelPassword);
        PageRegister pageRegister = new PageRegister(driver);
        pageRegister.setRegister("Edgar", "Garcia", "4121200630", "edgar@gmail.com", "aldama 12","Comonfort", "Guanajuato", "38200", "edgargv", "pass123.", "pass123.","MEXICO");

        By elementIdentifier = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font");
        Boolean valueIdentifier = driver.findElement(elementIdentifier).getText().contains("Thank you for registering.");
        Assert.assertTrue(valueIdentifier);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(!result.isSuccess()){
            takeScreenshot("Error", driver);
        }
        driver.close();
    }
}
