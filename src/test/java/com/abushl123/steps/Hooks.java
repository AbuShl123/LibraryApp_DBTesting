package com.abushl123.steps;

import com.abushl123.utility.ConfigurationReader;

import com.abushl123.utility.DB_Util;
import com.abushl123.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("Setting up the scenario");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("Closing the scenario");
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Driver.closeDriver();
    }

    @Before("@db")
    public void setupDB(){
        DB_Util.createConnection();
        System.out.println("connecting to database...");
    }

    @After("@db")
    public void destroyDB(){
        DB_Util.destroy();
        System.out.println("closing connection...");
    }
}
