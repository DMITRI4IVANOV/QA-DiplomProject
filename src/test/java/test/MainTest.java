package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DBHelper;
import page.MainPage;
import page.CreditPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {
    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void test1_shouldOpenPagePayment() {
        var paymentPage = new PaymentPage();
        paymentPage.buyPayment();
        paymentPage.sheetPayment();
    }

    @Test
    void test2_shouldOpenPageCredit() {
        var creditPage = new CreditPage();
        creditPage.buyCredit();
        creditPage.sheetCredit();
    }
}
