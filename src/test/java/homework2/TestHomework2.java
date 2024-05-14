package homework2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class TestHomework2 {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.timeout = 10000;
    }
    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
    @Test
    public void test6() {
        open("/dynamic_controls");

        $("#input-example input").shouldBe(Condition.disabled);

        $("#input-example button").click();

        $("#input-example input").shouldBe(Condition.enabled);

        String testString = "Test string";
        $("#input-example input").setValue(testString);

        String inputValue = $("#input-example input").getValue();
        assertEquals(inputValue, testString);

        $("#input-example button").click();

        $("#input-example input").shouldBe(Condition.disabled);

        $("#message").shouldHave(Condition.text("It's disabled!"));
    }

    @Test
    public void test7() {
        open("/dynamic_controls");

        $("#checkbox").shouldBe(Condition.visible).shouldBe(Condition.enabled);
        $("#checkbox input").shouldNotBe(Condition.checked);

        $("#checkbox input").click();
        $("#checkbox input").shouldBe(Condition.checked);

        $("#checkbox-example button").click();
        $("#loading").should(Condition.appear).should(Condition.disappear);

        $("#checkbox").shouldNot(Condition.exist);

        $("#checkbox-example button").click();
        $("#loading").should(Condition.appear).should(Condition.disappear);

        $("#checkbox").shouldBe(Condition.visible);

        $("#message").shouldHave(Condition.text("It's back!"));
    }

    @Test
    public void test8() {
        open("/javascript_alerts");

        $$(("button")).filter(Condition.text("Click for JS Alert")).first().click();

        Selenide.switchTo().alert().accept();

        $("#result").shouldHave(Condition.text("You successfully clicked an alert"));
    }
}
