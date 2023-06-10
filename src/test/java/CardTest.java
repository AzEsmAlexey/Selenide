import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.SetValueOptions.withText;

public class CardTest {



    @Test
    void shouldRegisterForm() {
        //Configuration.holdBrowserOpen = true;
        DataGenerateService planningDate = new DataGenerateService();

        open("http://localhost:9999/");
        $("[data-test-id=\"city\"] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate.generateDate(10));
        $("[data-test-id=\"name\"] input").setValue("Вещий Олег");
        $("[data-test-id=\"phone\"] input").setValue("+79024417754");
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate.generateDate(10)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

}
