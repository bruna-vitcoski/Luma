package Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class GerenciamentoDeDrivers {

    static WebDriver driver;

    public static WebDriver abrirBrowser(String browser) 
    {
        if (browser.equalsIgnoreCase("chrome")) 
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("force-device-scale-factor=1.0");
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("edge")) 
        {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }
}
