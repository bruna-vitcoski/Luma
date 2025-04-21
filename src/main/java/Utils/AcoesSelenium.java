package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AcoesSelenium {

    public void click(WebDriver driver, By by, int esperaSegundos) 
    {
        WebElement elemento = (new WebDriverWait(driver, Duration.ofSeconds(esperaSegundos)))
                .until(ExpectedConditions.elementToBeClickable(by));
        elemento.click();
    }

    public WebElement enviarDados(WebDriver driver, By by, String valor, int esperaSegundos) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(esperaSegundos));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        WebElement elemento = driver.findElement(by);
        elemento.clear();
        elemento.sendKeys(valor);
        return elemento;
    }

    public void esperarElementoVisivel(WebDriver driver, By by, int esperaSegundos) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(esperaSegundos));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
