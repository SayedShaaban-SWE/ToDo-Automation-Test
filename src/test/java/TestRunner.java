import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //Indicate path of package features .
        features = {
                "C:\\Users\\sayed\\IdeaProjects\\AutomationAppiumDemo\\src\\test\\resources\\features"
        },

        //Indicate only the name of stepDefinitions package.
        glue = {
                "step_defs"
        },
        //Plugins for Reporting
        plugin={    "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cukes.xml",
                "rerun:target/rerun.txt"},
        //Indicate tags in features files if exist .
        tags = ""
)

public class TestRunner{
}
