package com.desafio.accenture.qa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = "com.desafio.accenture.qa",
        tags = "@ui",
        plugin = {
                "pretty",
                "html:target/cucumber-ui-report.html"
        },
        monochrome = true
)
public class UiTestRunner {
}
