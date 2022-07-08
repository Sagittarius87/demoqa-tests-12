import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    String firstName = "Bob";
    String lastName = "Rob";
    String userEmail = "Bob@sobaka.com";
    String[] genterWrapper = new String[]{"Male", "Female", "Other"};
    String userNumber = "1111111111";
    String dateOfBirthMonth = "May";
    String dateOfBirthYear = "2022";
    String dateOfBirthDay = "20";
    String[] subjectsInput = new String[]{"Maths", "Chemistry", "Computer Sciens", "Commerce", "Economics"};
    String[] hobbiesWrapper = new String[]{"Sports", "Reading", "Music"};
    String currentAddress = "ulitsa Lesnaya, 5, kv. 176 Moscow Russia 125075";
    String filePath = "img/image.jpg";
    String fileName = "image.jpg";
    String[] state = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    String[][] city = new String[][]{
            {"Delhi", "Gurgaon", "Noida"},
            {"Agra", "Lucknow", "Merrut"},
            {"Karnal", "Panipat"},
            {"Jaipur", "Jaiselmer"}
    };

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
        $("#genterWrapper").$(byText(genterWrapper[0])).click();
        //$(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(dateOfBirthMonth);
        $(".react-datepicker__year-select").selectOption(dateOfBirthYear);
        $(".react-datepicker__day--0" + dateOfBirthDay + ":not(.react-datepicker__day--outside-month)").click();
        //$("#dateOfBirthInput").setValue(dateOfBirthInput).pressEnter();
        $("#subjectsInput").setValue(subjectsInput[0]).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbiesWrapper[0])).click();
        //$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath(filePath);
        //$("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state[0])).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city[0][0])).click();
        $("#submit").click();
    }

    @AfterEach
    void checkTable() {
        $(".modal-content").isEnabled();
        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                Condition.text(firstName + " " + lastName),
                Condition.text(userEmail),
                Condition.text(genterWrapper[0]),
                Condition.text(userNumber),
                Condition.text(dateOfBirthDay + " " + dateOfBirthMonth + "," + dateOfBirthYear),
                Condition.text(subjectsInput[0]),
                Condition.text(hobbiesWrapper[0]),
                //Condition.text(filePath),
                Condition.text(fileName),
                Condition.text(currentAddress),
                Condition.text(state[0] + " " + city[0][0])
        );
        $("#closeLargeModal").click();
    }
}
