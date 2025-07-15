package TestPackage;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Lecture01_LaunchBrowser {
    public static void main(String[] args) {

        //Create an instance of playwright
        Playwright playwright = Playwright.create();

        //Launch the browser using the Required Launch Options
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        //If chromium : playwright.chromium().launch
        //If Firefox : playwright.firefox().launch
        //If Safari : playwright.webkit().launch

        //An instance of a new tab in a browser is assigned and the operation will happen in that tab
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php");
        Locator myAccount = page.locator("(//a/div/span[contains(text(), 'My account')])[2]");
        myAccount.hover();
        page.locator("(//a/div/span[contains(text(), \"Login\")])[1]").click();
        assertThat(page).hasTitle("Account Login");
        page.getByPlaceholder("E-Mail Address").type("koushik350@gmail.com");
        page.getByPlaceholder("Password").type("Pass123$");
        page.locator("//input[@type = 'submit']").click();
        assertThat(page).hasTitle("My Account");

        //Close Tab
        page.close();

        //Close Browser
        browser.close();

        // Close Playwright Run
        playwright.close();
    }
}
