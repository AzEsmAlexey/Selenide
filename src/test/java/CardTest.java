import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldRegisterForm() {
        //Configuration.holdBrowserOpen = true;

        open("http://localhost:9999/");
        $("[data-test-id=\"city\"] input").setValue("Москва");
        $("[data-test-id=\"name\"] input").setValue("Вещий Олег");
        $("[data-test-id=\"date\"] input").click();
        $("[data-test-id=\"date\"] input").clear();
        $("[data-test-id=\"date\"] input").setValue("22122023");
        $("[data-test-id=\"phone\"] input").setValue("+79024417754");
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldBe(visible, Duration.ofSeconds(12));
    }

}
