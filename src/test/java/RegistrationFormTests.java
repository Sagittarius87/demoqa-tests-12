import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

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
        $("#genterWrapper").$(byText("Male")).click();
        //$(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2022");
        $(".react-datepicker__day--005:not(.react-datepicker__day--outside-month)").click();
        //$("#dateOfBirthInput").setValue(dateOfBirthInput).pressEnter();
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        //$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/image.jpg");
        //$("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));
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
        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));
        $("table").$("tbody").$(byText(firstName + " " + lastName));
        $("table").$("tbody").$(byText(userEmail));
        $("table").$("tbody").$(byText("Male"));
        $("table").$("tbody").$(byText(userNumber));
        $("table").$("tbody").$(byText(dateOfBirthInput));
        $("table").$("tbody").$(byText(subjectsInput));
        $("table").$("tbody").$(byText("Sports"));
        $("table").$("tbody").$(byText("img/image.jpg"));
        $("table").$("tbody").$(byText(currentAddress));
        $("table").$("tbody").$(byText("NCR" + " " + "Delhi"));
        $("#closeLargeModal").click();
    }
}
