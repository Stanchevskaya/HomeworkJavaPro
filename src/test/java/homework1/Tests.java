package homework1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static org.assertj.core.api.Assertions.assertThat;
public class Tests {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
    }
    protected void openUrl(String relativeUrl) {
        open(relativeUrl);
    }

    @Test
    public void test1() {
        openUrl("/javascript_alerts");
        $("button[onclick='jsAlert()']").click();
        String alertText = switchTo().alert().getText();
        assertThat(alertText).isEqualTo("I am a JS Alert");
        switchTo().alert().accept();
        $("#result").shouldHave(text("You successfully clicked an alert"));
    }
    @Test
    public void test2() {
        openUrl("/javascript_alerts");
        $("button[onclick='jsConfirm()']").click();
        String alertText = switchTo().alert().getText();
        assertThat(alertText).isEqualTo("I am a JS Confirm");
        switchTo().alert().dismiss();
        $("#result").shouldHave(text("You clicked: Cancel"));
    }

    @Test
    public void test3() {
        openUrl("/javascript_alerts");
        $("button[onclick='jsPrompt()']").click();
        String alertText = switchTo().alert().getText();
        assertThat(alertText).isEqualTo("I am a JS prompt");
        switchTo().alert().sendKeys("Test message");
        switchTo().alert().accept();
        $("#result").shouldHave(text("You entered: Test message"));
    }

    @Test
    public void test4() {
        openUrl("/javascript_alerts");
        $("button[onclick='jsConfirm()']").click(usingJavaScript());
        String alertText = switchTo().alert().getText();
        assertThat(alertText).isEqualTo("I am a JS Confirm");
        switchTo().alert().accept();
        $("#result").shouldHave(text("You clicked: Ok"));
    }

    @Test
    public void test5() {
        openUrl("/javascript_alerts");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jsExecutor.executeScript("document.querySelector('button[onclick=\"jsConfirm()\"]')?.click();");
        String alertText = switchTo().alert().getText();
        assertThat(alertText).isEqualTo("I am a JS Confirm");
        switchTo().alert().accept();
        $("#result").shouldHave(text("You clicked: Ok"));
    }


}
