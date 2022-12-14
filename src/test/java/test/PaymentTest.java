package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DBHelper;
import data.DataHelper;
import page.MainPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
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

    @AfterAll
    static void clearTables() {
        DBHelper.clearTables();
    }

    @Test
    void test3_shouldFillPaymentFormCard1() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var paymentPage = mainPage.buyPayment();
        var authInfo = DataHelper.getAuthInfoAllValidApproved();
        paymentPage.fillPaymentForm(authInfo);
        paymentPage.checkOperationIsSuccessful();
        assertEquals("APPROVED", DBHelper.getStatusPayment());
    }

    @Test
    void test4_shouldFillPaymentFormCard2() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var paymentPage = mainPage.buyPayment();
        var authInfo = DataHelper.getAuthInfoAllValidDeclined();
        paymentPage.fillPaymentForm(authInfo);
        paymentPage.checkOperationIsNotSuccessful();
        assertEquals("DECLINED", DBHelper.getStatusPayment());
    }

    @Test
    void test5_shouldNotFillPaymentForm() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var paymentPage = mainPage.buyPayment();
        var authInfo = DataHelper.getAuthInfoNoData();
        paymentPage.fillPaymentForm(authInfo);
        paymentPage.cardNumberError();
        paymentPage.cardMonthError();
        paymentPage.cardYearError();
        paymentPage.cardHolderError();
        paymentPage.cardCVCError();
    }

    @Test
    void test6_shouldFillPaymentFormNotValidCardNumber() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumberNotValid();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.checkOperationIsNotSuccessful();
    }

    @Test
    void test7_shouldFillPaymentFormNotValidCardNumber15() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumberNotValid15();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardNumberError();
    }

    @Test
    void test8_shouldFillPaymentFormNotValidCardNumber17() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumberNotValid17();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.checkOperationIsNotSuccessful();
    }

    @Test
    void test9_shouldNotBeHiddenNotificationOnPagePayment() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumberNotValid();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.checkOperationIsNotSuccessful();
        paymentPage.checkOperationIsSuccessful();
    }

    @Test
    void test10_shouldFillPaymentFormNotValidFormatCardMonth() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonthNotValidFormat();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardMonthError();
    }

    @Test
    void test11_shouldFillPaymentFormNotValidCardMonth() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonthNotValid();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardMonthError2();
    }

    @Test
    void test12_shouldFillPaymentFormNotValidCardYear() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardYearNotValid1();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardYearError2();
    }

    @Test
    void test13_shouldFillPaymentFormPreviousMonthCurrentYear() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var paymentPage = mainPage.buyPayment();
        var authInfo = DataHelper.getAuthInfoMonthYear();
        paymentPage.fillPaymentForm(authInfo);
        paymentPage.cardMonthError2();
    }

    @Test
    void test14_shouldFillPaymentFormNotValidCardYear2() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardYearNotValid2();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardYearError3();
    }

    @Test
    void test15_shouldFillPaymentFormNotValidCyrillicCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidCyrillic();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardHolderError2();
    }

    @Test
    void test16_shouldFillPaymentFormNotValidLatinCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidLatin();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardHolderError2();
    }

    @Test
    void test17_shouldFillPaymentFormNotValidNumbersCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidNumbers();
        var cardCVC =DataHelper.getCardCVC();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardHolderError2();
    }

    @Test
    void test18_shouldFillPaymentFormNotValidCardCVC() {
        var mainPage = new MainPage();
        mainPage.buyPayment();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVCNotValid();
        var paymentPage = new PaymentPage();
        paymentPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        paymentPage.cardCVCError();
    }
}
