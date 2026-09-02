package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;

    public OptionsManager(Properties properties) {
        this.prop = properties;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOption = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("---- Running in headless mode ----- ");
            chromeOption.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("---- Running in incognito mode ----- ");
            chromeOption.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            chromeOption.setCapability("browserName", "chrome");
        }
        return chromeOption;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOption = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            firefoxOption.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            firefoxOption.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            firefoxOption.setCapability("browserName", "firefox");
        }
        return firefoxOption;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOption = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            edgeOption.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            edgeOption.addArguments("--inprivate");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            edgeOption.setCapability("browserName", "edge");
        }
        return edgeOption;
    }
}
