package helpers;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    public static void setWindowsSize(String size, WebDriver driver){
        if (size.toLowerCase().equals("maximized")){
            driver.manage().window().maximize();
        } else if (size.toLowerCase().equals("fullscreen")) {
            driver.manage().window().fullscreen();
        }else {
            System.out.println("Error: opción no válida");
        }
    }

    public static void goToURL(String url, WebDriver driver){
        driver.navigate().to(url);
    }
}
