package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) this.driver;
    }

    public String getTitleByJS() {
        return js.executeScript("return document.title;").toString();
    }

    public String getURLByJS() {
        return js.executeScript("return document.URL;").toString();
    }

    public void refreshBrowserByJS() {
        js.executeScript("history.go(0)");
    }

    public void navigateToBackPage() {
        js.executeScript("history.go(-1)");
    }

    public void navigateToForwardPage() {
        js.executeScript("history.go(1)");
    }

    public void generateJSAlert(String message) {
        js.executeScript("alert('" + message + "')");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().dismiss();
    }

    public String getPageInnerText() {
        return js.executeScript("return document.documentElement.innerText;").toString();
    }

    public void scrollPageDown() {
        js.executeScript("return window.scrollTo(0,document.body.scrollHeight)");
    }

    public void scrollPageDown(int height) {
        js.executeScript("return window.scrollTo(0,'" + height + "')");
    }

    public void scrollPageUp() {
        js.executeScript("return window.scrollTo(document.body.scrollHeight,0)");
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public void drawBorder(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);

    }

    public void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundColor"); //Capture the existing color of element
        for (int i = 0; i < 3; i++) {
            changeColor("rgb(255,255,0)", element); // Yellow
            changeColor(bgColor, element);
        }
    }

    private void changeColor(String color, WebElement element) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
    }

    /**
     * example: "document.bosy.style.zoom = '400.0%'"
     *
     * @param zoomPercentage
     */
    public void zoomChromeEdgeFirefox(String zoomPercentage) {
        String zoom = "document.body.style.zoom = '" + zoomPercentage + "'";
        js.executeScript(zoom);
    }

    /**
     * example: "document.body.style.MozTransform = 'scale(0.5)'"
     *
     * @param zoomPercentage
     */
    public void zoomFirefox(String zoomPercentage) {
        String zoom = "document.body.style.MozTransform = 'scale(" + zoomPercentage + ")'";
        js.executeScript(zoom);

    }

    public void clickElementByJS(WebElement element) {
        js.executeScript("arguments[0].click()", element);

    }

}
