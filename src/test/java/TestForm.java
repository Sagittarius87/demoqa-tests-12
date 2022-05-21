import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileWriter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1680x1050";
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void openSite() {
        open("/automation-practice-form");
    }

    @Test
    void fillForm() {
        String firstName = "Bob";
        String lastName = "Rob";
        String userEmail = "Bob@sobaka.com";
        String userNumber = "1111111111";
        String dateOfBirthInput = "20 May 2022";
        String subjectsInput = "M";
        String currentAddress = "ulitsa Lesnaya, 5, kv. 176\nMoscow\nRussia\n125075";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").setValue(dateOfBirthInput).pressEnter();
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();
    }
}
