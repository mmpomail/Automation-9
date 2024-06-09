package ru.netology.auto;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide()); }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void tearDownAll() {SelenideLogger.removeListener("allure");}

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void testPlanningAndRePlanningMeeting() {
        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int dayFirstMeeting = 4;
        String firstMeeting = DataGenerator.generateDate(dayFirstMeeting);
        int daySecondMeeting = 7;
        String secondMeeting = DataGenerator.generateDate(daySecondMeeting);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeeting);
        $("[name='name']").setValue(DataGenerator.generateName("ru"));
        $("[name='phone']").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__title").shouldHave(Condition.text("Успешно!")).shouldBe(Condition.visible,
                Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " +
                firstMeeting)).shouldBe(Condition.visible);

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(secondMeeting);
        $(".button").click();
        $("[data-test-id= replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id= replan-notification]").shouldHave
                (Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id= replan-notification] button").click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " +
                secondMeeting)).shouldBe(Condition.visible);
    }
}
