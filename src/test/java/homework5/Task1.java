package homework5;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Task1  {

        @Test
        public void testFileUpload() throws IOException {
            Faker faker = new Faker();
            String paragraph = faker.lorem().paragraph(5);
            File file = new File("/Users/olga.stanchevska/Documents/homework.txt\"homework.txt");
            FileUtils.writeStringToFile(file, paragraph);

            Playwright playwright = Playwright.create();
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("https://the-internet.herokuapp.com/upload");
            page.setInputFiles("#file-upload", file.toPath());
            page.locator("#file-submit").click();
            assertThat(page.locator("#uploaded-files")).containsText(file.getName());
        }

}
