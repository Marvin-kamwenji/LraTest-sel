package LRA;

import EmailSetup.SendEmailError;
import EmailSetup.SendEmailOkay;
import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;


public class LRATest extends TimerTask {

    private WebDriver driver;
    String baseURL, nodeURL;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver","E:\\Selenium libraries\\Chrome driver version 100\\chromedriver.exe");
//        nodeURL = "http://localhost:53930";
        nodeURL = "http://localhost:4444/";
        baseURL = "https://portal.efd.lra.gov.lr/app/";
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        capability.setVersion("100.0");

        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void login22() throws InterruptedException, IOException, EmailException {
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector(".usernameField")).click();
        driver.findElement(By.cssSelector(".usernameField")).sendKeys("admin");
        driver.findElement(By.name("passwordField")).click();
        driver.findElement(By.name("passwordField")).sendKeys("95u*3TW]BF+~");
        driver.findElement(By.cssSelector(".v-button-caption")).click();

        Thread.sleep(20000);

//CLICKING DASHBOARD
//        driver.findElement(By.xpath("//*[@id=\"app-96801\"]/div/div[2]/div/div/div/div/div[1]/div/div/div[3]/div/div[2]/span/span[2]\n" +
//                "\n")).click();
//        Thread.sleep(5000);

//CLICKING GENERAL REPORTS
//        driver.findElement(By.xpath("//*[@id=\"app-96801\"]/div/div[2]/div/div/div/div/div[1]/div/div/div[3]/div/div[3]/div[1]/span/span\n" +
//                "\n" +
//                "\n")).click();
//        Thread.sleep(5000);


        //GETTING RESPONSE OF SITE
        //URL connection
        HttpURLConnection cont=
                (HttpURLConnection)new URL("https://portal.efd.lra.gov.lr/app/#main/0")
                        .openConnection();
        // pass HEAD as parameter to setRequestMethod
        cont.setRequestMethod("HEAD");
        // obtain Response code
        cont.connect();
        int rs = cont.getResponseCode();
        System.out.println("Http response code: " + rs);


        Thread.sleep(10000);

        if (rs == 200)
        {
            SendEmailOkay.sendingEmailToUser();
        }
        else
        {
            SendEmailError.sendingEmailToUser();
        }


        driver.findElement(By.cssSelector(".v-button-wrap > .v-icon")).click();

    }

    /*===========================================================================================*/
    /*==========================SCHEDULING PROGRAM TO RUN EVERY 15 MINUTES=======================*/
    /*===========================================================================================*/

    @Override
    public void run() {
        try {
            login22();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
