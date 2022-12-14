package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.Constants;

import static utils.Utils.dotEnv;

public class Environment {

    private String homeUrl;
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Environment.class.getName());


    public Environment(WebDriver driver){
        this.driver = driver;
    }

    public void openBrowser(){
        String environment = dotEnv().get("ENV");
        if (environment.equalsIgnoreCase("DEV")){
            homeUrl = Constants.DEV_URL;
        } else if (environment.equalsIgnoreCase("QA")) {
            homeUrl = Constants.QA_URL;
        }
        logger.info("Opening browser: " + dotEnv().get("BROWSER") + " navigating to: " + homeUrl);
        driver.get(homeUrl);
    }
}
