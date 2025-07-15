package TestPackage;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Lecture02_InteractWithInputs {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.locator("input#user-message").type("Hey Tester");
        page.locator("id=showInput").click();
        String message = page.locator("#message").textContent();
        System.out.println(message);
        assertThat(page.locator("#message")).hasText("Hey Tester");

        // type vs fill : Moreover they are same but
        // type : Paste data in Text Field word by word, it will take time
        // fill : Paste the whole data in 1 go, saves time

        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");

        //slow
        page.locator("#textbox").type("The HTML input input is an input element to enter an array of different values. Each input type checkbox has value attribute which is used to define the value submitted by the checkbox.");
        //fast
        page.locator("#textbox").fill("The HTML input input is an input element to enter an array of different values. Each input type checkbox has value attribute which is used to define the value submitted by the checkbox.");

        // GET / FETCH input values

        page.navigate("https://letcode.in/edit");
        String inputValue = page.locator("#getMe").inputValue();
        System.out.println(inputValue);
        String placeholderValue = page.locator("#fullName").getAttribute("placeholder");
        System.out.println(placeholderValue);
        Locator fullNamelocator = page.locator("#fullName");
        assertThat(fullNamelocator).hasAttribute("placeholder", "Enter first & last name");
        page.locator("id=clearMe").clear();

        //handling the Checkbox
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        Locator isAge = page.locator("(//input[@type='checkbox'])[1]");
        //verifying checkbox is NOT checked
        assertThat(isAge).not().isChecked();
        //check the checkbox
        isAge.check();
        //verifying checkbox is checked
        assertThat(isAge).isChecked();

        playwright.close();
    }

}
