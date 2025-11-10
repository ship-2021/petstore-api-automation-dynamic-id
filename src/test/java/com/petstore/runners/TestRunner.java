package com.petstore.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Path to feature files
        glue = {"com.petstore.stepdefinitions"},  // Step definitions package
        plugin = {
                "pretty",                             // Console output
                "html:target/cucumber-report.html",   // HTML report
                "json:target/cucumber-report.json"    // Optional JSON report
        },
        monochrome = true
)
public class TestRunner {
}
