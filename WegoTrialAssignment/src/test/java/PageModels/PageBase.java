package PageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageBase
{
    protected WebDriver _driver;
    protected String url = null;
    protected String title = null;
    protected String getUrl()
    {
        return url;
    }
    protected String getTitle()
    {
        return title;
    }
    public void NavigateTo() throws Exception
    {
        _driver.get(url);
        EnsurePageLoaded();
    }

    public void EnsurePageLoaded() throws Exception
    {
        if (!((_driver.getCurrentUrl().equals(url)) && (_driver.getTitle().contains(title))))
        {
            throw new Exception(String.format("Page loading failed. Page URL = %s. Page source = %s", _driver.getCurrentUrl(), _driver.getPageSource()));
        }
    }
    public List<WebElement> getWebElementsFromJS(String jsSelector)
    {
        return this.getWebElementsFromJS(jsSelector, null);
    }
    public List<WebElement> getWebElementsFromJS(String jsSelector, WebElement argument)
    {
        JavascriptExecutor jsExecuter = (JavascriptExecutor) _driver;
        Object element = null;
        if(argument != null)
        {
            element = jsExecuter.executeScript(jsSelector, argument);
        }
        else
        {
            element = jsExecuter.executeScript(jsSelector);
        }

        return (List<WebElement>) element;
    }
    public WebElement getWebElementFromJS(String jsSelector)
    {
        return this.getWebElementFromJS(jsSelector, null);
    }
    public WebElement getWebElementFromJS(String jsSelector, WebElement argument)
    {
        JavascriptExecutor jsExecuter = (JavascriptExecutor) _driver;
        Object element = null;
        if(argument != null)
        {
            element = jsExecuter.executeScript(jsSelector, argument);
        }
        else
        {
            element = jsExecuter.executeScript(jsSelector);
        }
        return (WebElement) element;
    }
    public void AllowtoSubscribe() throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(_driver, Duration.ofSeconds(25));
//        WebElement subscribe =  wait.until(ExpectedConditions.elementToBeClickable(By.id("allow")));
        Thread.sleep(15000);
        _driver.switchTo().frame("webpush-onsite");
        String allowLocator = "return document.querySelector(\"#deny\")";
        WebElement subscribe =  getWebElementFromJS(allowLocator);
        subscribe.click();
        _driver.switchTo().defaultContent();

    }
}
