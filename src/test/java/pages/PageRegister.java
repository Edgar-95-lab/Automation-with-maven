package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static helpers.Helpers.sleepSeconds;

public class PageRegister {
    private WebDriver driver;
    private By registerLink;
    //Contact Information
    private By fnameField;
    private By lnameField;
    private By phoneField;
    private By emailField;

    //Mailing Information
    private By addressField;
    private By cityField;
    private By stateField;
    private By postalcodeField;
    private By countryDrop;

    //User Information
    private By usernameField;
    private By passwordField;
    private By confpasswordField;
    private By sendButton;

    public PageRegister(WebDriver driver){
        this.driver = driver;
        registerLink = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a");
        //Contact Information
        fnameField = By.name("firstName");
        lnameField = By.name("lastName");
        phoneField = By.name("phone");
        emailField = By.name("userName");

        //Mailing Information
        addressField = By.name("address1");
        cityField = By.name("city");
        stateField = By.name("state");
        postalcodeField = By.name("postalCode");
        countryDrop = By.name("country");

        //User Information
        usernameField = By.name("email");
        passwordField = By.name("password");
        confpasswordField = By.name("confirmPassword");
        sendButton = By.name("submit");
    }

    public void setRegister(String fname, String lname, String phone,
                            String email, String address, String city,
                            String state, String postalcode, String username,
                            String password, String confpassword, String opt){
        driver.findElement(registerLink).click();
        sleepSeconds(2);
        //Contact Information
        driver.findElement(fnameField).sendKeys(fname);
        driver.findElement(lnameField).sendKeys(lname);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(emailField).sendKeys(email);

        //Mailing Information
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(postalcodeField).sendKeys(postalcode);
        Select selectCountry = new Select(driver.findElement(countryDrop));
        selectCountry.selectByVisibleText(opt);

        //User Information
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confpasswordField).sendKeys(confpassword);
        driver.findElement(sendButton).click();
        sleepSeconds(2);
    }
}
