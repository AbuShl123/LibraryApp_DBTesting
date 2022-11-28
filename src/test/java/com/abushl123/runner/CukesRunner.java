package com.abushl123.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt" ,
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber/cucumber.json"
        },
        features = "src/test/resources/features" ,
        glue = "com/abushl123/steps",
        dryRun = false,
        tags = "@smoke",
        publish = true
)
public class CukesRunner {

}




