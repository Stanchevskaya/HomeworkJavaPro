package homework4;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;

public class Part2 {

    @Test
    public void testHoverElement() {
        Playwright playwright = Playwright.create();
        Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
        page.navigate("https://playwright.dev/java/");
        page.waitForSelector("(//div[@class='col col--6'])[4]");
        ElementHandle element = page.querySelector("(//div[@class='col col--6'])[4]");
        Assert.assertNotNull(element, "Element was not found");
        element.scrollIntoViewIfNeeded();
        element.hover();
        page.waitForTimeout(3000);
    }
}
