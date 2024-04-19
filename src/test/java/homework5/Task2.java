package homework5;

import com.github.javafaker.Faker;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Task2 {

    @Test
    public void downloadTest() throws IOException {
        Faker faker = new Faker();
        String paragraph = faker.lorem().paragraph(5);
        File fileUpload = new File("homeworkOlgaS.txt");
        FileUtils.writeStringToFile(fileUpload, paragraph);

        Playwright playwright = Playwright.create();
        Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
        page.navigate("https://the-internet.herokuapp.com/upload");
        page.setInputFiles("#file-upload", fileUpload.toPath());
        page.locator("#file-submit").click();

        page.navigate("https://the-internet.herokuapp.com/download");

        Download download = page.waitForDownload(() ->{
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(fileUpload.getName())).click();
        });

        File fileDownload = new File("/Users/olga.stanchevska/Documents", download.suggestedFilename());
        download.saveAs(fileDownload.toPath());
        Assert.assertTrue(FileUtils.contentEquals(fileUpload, fileDownload));
    }

}
