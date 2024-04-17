package homework4;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Part1 {

    @Test
    public void alertsTest() {
        Playwright playwright = Playwright.create();
        Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
        page.waitForTimeout(2000);
        String text = "Homework";
        page.onceDialog(alert -> {
            alert.accept(text);});
        page.evaluate("() => jsPrompt()");
        page.waitForTimeout(5000);
        assertThat(page.locator("#result")).containsText("You entered: " + text);
    }

}


