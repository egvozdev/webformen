package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class SearchSteps {

    HomePage homePage = new HomePage();

    @Given("User opens Verbformen homepage")
    public void open_homepage() {
        homePage.openHomePage();
    }

    @When("User enters word {string}")
    public void enter_word(String word) {
        homePage.enterWord(word);
    }

    @Then("Russian translation should be displayed")
    public void verify_translation() {
        Assert.assertTrue(homePage.isRussianTranslationDisplayed(),
                "Russian translation was not displayed!");
    }
    @Then("Translation should contain {string}")
    public void verify_specific_translation(String word) {
        Assert.assertTrue(homePage.isTranslationDisplayed(word),
                "Russian translation was not displayed!");
    }
}