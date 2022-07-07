import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    String firstName = "Bob";
    String lastName = "Rob";
    String userEmail = "Bob@sobaka.com";
    String userNumber = "1111111111";
    String dateOfBirthInput = "20 May 2022";
    String subjectsInput = "Maths";
    String currentAddress = "ulitsa Lesnaya, 5, kv. 176 Moscow Russia 125075";

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
        $(".practice-form-wrapper").shouldHave(Condition.text("Student Registration Form"));
    }

    @Test
    void fillFormTest() {
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

    @AfterEach
    void checkTable() {
        $(".modal-content").isEnabled();
        $("table").$("tbody").$(byText(firstName + " " + lastName));
        $("table").$("tbody").$(byText(userEmail));
        $("table").$("tbody").$(byText("Male"));
        $("table").$("tbody").$(byText(userNumber));
        $("table").$("tbody").$(byText(dateOfBirthInput));
        $("table").$("tbody").$(byText(subjectsInput));
        $("table").$("tbody").$(byText("Sports"));
        $("table").$("tbody").$(byText("image.jpg"));
        $("table").$("tbody").$(byText(currentAddress));
        $("table").$("tbody").$(byText("NCR" + " " + "Delhi"));
        $("#closeLargeModal").click();
    }
}
