package com.qa.opencart.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private Actions action;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        action = new Actions(this.driver);
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public WebElement getElement(By locator, int timeOut) {
        return waitForElementVisible(locator, timeOut);
    }

    public WebElement getElement(String locatorType, String locatorValue) {
        return driver.findElement(getBy(locatorType, locatorValue));
    }

    public By getBy(String locatorType, String locatorValue) {
        By locator = null;
        switch (locatorType.toUpperCase()) {
            case "ID":
                locator = By.id(locatorValue);
                break;
            case "NAME":
                locator = By.name(locatorValue);
                break;
            case "CLASS":
                locator = By.className(locatorValue);
                break;
            case "XPATH":
                locator = By.xpath(locatorValue);
                break;
            case "CSS":
                locator = By.cssSelector(locatorValue);
                break;
            case "LINKTEXT":
                locator = By.linkText(locatorValue);
                break;
            case "PARTIALLINKTEXT":
                locator = By.partialLinkText(locatorValue);
                break;
            case "TAGNAME":
                locator = By.tagName(locatorValue);
                break;
            default:
                System.out.println("Please pass the right locator type: " + locatorType);
                break;
        }
        return locator;
    }

    public void doSendKeys(By locator, String value) {
        nullCheck(value);
        getElement(locator).sendKeys(value);
    }

    public void doSendKeys(String locatorType, String locatorValue, String value) {
        nullCheck(value);
        getElement(locatorType, locatorValue).sendKeys(value);
    }

    public void doSendKeys(By locator, CharSequence... value) {
        nullCheck(value);
        getElement(locator).sendKeys(value);
    }


    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void doClick(String locatorType, String locatorValue) {
        getElement(locatorType, locatorValue).click();
    }

    public String doElementGetText(By locator) {
        String text = getElement(locator).getText();
        System.out.println("Fetched text is: " + text);
        return text;
    }

    public String getElementDomPropertyValue(By locator, String property) {
        nullCheck(property);
        return getElement(locator).getDomProperty(property);
    }

    public String getElementDomAttributeValue(By locator, String attribute) {
        nullCheck(attribute);
        return getElement(locator).getDomAttribute(attribute);
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on the page: " + locator);
            return false;
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            return getElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on the page: " + locator);
            return false;
        }
    }

    public boolean isElementSelected(By locator) {
        try {
            return getElement(locator).isSelected();
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on the page: " + locator);
            return false;
        }
    }

    private void nullCheck(CharSequence... value) {
        if (value == null) {
            throw new RuntimeException("===== Value can not be NULL ===");
        }
    }

    //****** FindElements Utils ***********************//
    public List<String> getElementTextList(By locator) {
        List<WebElement> eleList = getElements(locator);
        List<String> eleTextList = new ArrayList<String>();
        for (WebElement ele : eleList) {
            String text = ele.getText();
            if (text.length() != 0) {
                System.out.println(text);
                eleTextList.add(text);
            }
        }
        return eleTextList;
    }

    public int getElementCounts(By locator) {
        int elementCount = getElements(locator).size();
        System.out.println("Element count: " + elementCount);
        return elementCount;
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public boolean checkElementDisplayed(By locator) {
        if (getElements(locator).size() == 1) {
            System.out.println("element: " + locator + "is displayed on the page one time.");
            return true;
        }
        return false;
    }

    public boolean checkElementDisplayed(By locator, int expectedElementCount) {
        if (getElements(locator).size() == expectedElementCount) {
            System.out.println("element: " + locator + "is displayed on the page " + expectedElementCount + " time.");
            return true;
        }
        return false;
    }

    //If you want to click on any element in particular section on page
    public void clickElement(By locator, String elementValue) {
        List<WebElement> eleList = getElements(locator);
        System.out.println("Total number of elements: " + eleList.size());

        for (WebElement ele : eleList) {
            String text = ele.getText();
            if (text.contains(elementValue)) {
                ele.click();
                break;
            }
        }
    }

    //********* Drop-down utils for my Select based dropdown *********************//

    public boolean doSelectDropDownByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        try {
            select.selectByIndex(index);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(index + " is not present in the dropdown.");
            return false;
        }
    }

    public boolean doSelectDropDownByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        try {
            select.selectByValue(value);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(value + " is not present in the dropdown.");
            return false;
        }
    }

    public boolean doSelectDropDownByVisibleText(By locator, String visibleText) {
        Select select = new Select(getElement(locator));
        try {
            select.selectByVisibleText(visibleText);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(visibleText + " is not present in the dropdown.");
            return false;
        }

    }

    public boolean selectDropDownValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> lists = select.getOptions();
        System.out.println("Size: " + lists.size());

        boolean flag = false;
        for (WebElement ele : lists) {
            String text = ele.getText();
            System.out.println(text);
            if (text.equals(value)) {
                ele.click();
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println(value + " is selected.");
            return true;
        } else {
            System.out.println(value + " is not selected");
            return false;
        }
    }

    public List<String> getDropDownValueList(By locator) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        System.out.println("Size: " + optionsList.size());
        List<String> optionsValueList = new ArrayList<String>(); //PC=0
        for (WebElement option : optionsList) {
            String text = option.getText();
            optionsValueList.add(text.trim());
        }
        return optionsValueList;
    }

    public boolean getDropDownValueList(By locator, List<String> expOptionsList) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        System.out.println("Size: " + optionsList.size());
        List<String> optionsValueList = new ArrayList<String>(); //PC=0
        for (WebElement option : optionsList) {
            String text = option.getText();
            optionsValueList.add(text.trim());
        }
        if (optionsValueList.containsAll(expOptionsList)) {
            return true;
        } else {
            return false;
        }
    }

    //********* Drop down util = non select based----*//

    /**
     * This method is used to select the choices with three different use cases:
     * 1. Single selection: selectChoice(choice,choicesList,"choice 6");
     * 2. Multi Selection: selectChoice(choice, choicesList, "choice 6", "choice 5", "choice 6 2 3");
     * 3. All selection: use 'all/All' to select all choices -> selectChoice(choice, choicesList, "all");
     *
     * @param choice
     * @param choicesList
     * @param choiceValue
     * @throws InterruptedException
     */
    public void selectChoice(By choice, By choicesList, String... choiceValue) throws InterruptedException {
        doClick(choice);
        Thread.sleep(2000);
        List<WebElement> choices = getElements(choicesList);
        System.out.println(choices.size());
        if (choiceValue[0].equalsIgnoreCase("all")) {
            for (WebElement e : choices) {
                e.click();
            }
        } else {
            for (WebElement e : choices) {
                String text = e.getText();
                System.out.println(text);
                for (String value : choiceValue) {
                    if (text.trim().equals(value)) {
                        e.click();
                        break;
                    }
                }
            }
        }
    }

    //************** Actions Util ******************//


    public void doMoveToElement(By locator) throws InterruptedException {
        action.moveToElement(getElement(locator)).build().perform();
        Thread.sleep(2000);
    }

    //It will handle parent and child menu
    public void handleParentAndSubMenu(By parentMenu, By subMenu) throws InterruptedException {
        doMoveToElement(parentMenu);
        doClick(subMenu);
    }

    public void handle4LevelMenuHandle(By level1Menu, By level2Menu, By level3Menu, By level4Menu) throws InterruptedException {
        doClick(level1Menu);
        Thread.sleep(2000);
        doMoveToElement(level2Menu);
        doMoveToElement(level3Menu);
        doClick(level4Menu);
    }

    public void doActionClick(By locator) {
        action.click(getElement(locator)).build().perform();
    }

    public void doActionSendKeys(By locator, String value) {
        action.sendKeys(getElement(locator), value).build().perform();
    }

    public void doSendKeysWithPause(By locator, String value, long pauseTime) {
        char[] val = value.toCharArray();
        for (char ch : val) {
            action.sendKeys(getElement(locator), String.valueOf(ch)).pause(pauseTime).build().perform();
        }

    }

    //******** Wait utils*****************//
    public WebElement waitForElementPresence(By locator, int waitTimeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(By locator, int waitTimeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForAllElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * An expectation for checking that all elements present on the web page that match the locator are visible.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     * @param locator
     * @param timeOut
     * @return
     */
    public List<WebElement> waitForAllElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }



    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     * @param locator
     * @param timeout
     */
    public void clickWhenReady(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickWithWait(By locator, int timeOut) {
        waitForElementVisible(locator, timeOut).click();
    }

    public void sendKeysWithWait(By locator, int timeOut, CharSequence... value) {
        waitForElementVisible(locator, timeOut).sendKeys(value);
    }

    //************** Wait for JS script popup alert *********//

    public Alert waitForAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(int timeOut) {
        waitForAlert(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlert(timeOut).dismiss();
    }

    public String getTextAlert(int timeOut) {
        return waitForAlert(timeOut).getText();
    }

    public void sendKeysAlert(int timeOut, String value) {
        waitForAlert(timeOut).sendKeys(value);
    }

    //**************** WAIT FOR TITLE ****************************//
    public String waitForTitleContains(String fractionTitle, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.titleContains(fractionTitle));
            return driver.getTitle();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String waitForTitleIs(String title, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.titleIs(title));
            return driver.getTitle();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String waitForURLContains(String fractionURL, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.urlContains(fractionURL));
            return driver.getCurrentUrl();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String waitForURLIs(String url, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.urlToBe(url));
            return driver.getTitle();
        } catch (TimeoutException e) {
            return null;
        }
    }

    //*************** WAIT FOR FRAME *****************************//

    public void waitForFrameAndSwitchToIt(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void waitForFrameAndSwitchToIt(String frameNameOrId, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
    }

    public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    //************** WAIT FOR WINDOWS ****************************//
    public boolean waitForWindow(int expectedWindowsCount, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowsCount));
        } catch (Exception e) {
            System.out.println("Expected Number of Windows are Not Correct.");
            return false;
        }

    }

    public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("==== ELEMENT NOT FOUND ========");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("==== ELEMENT NOT FOUND ========");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
