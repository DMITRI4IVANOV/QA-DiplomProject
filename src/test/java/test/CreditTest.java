package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DBHelper;
import data.DataHelper;
import page.CreditPage;
import page.MainPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void clearTables() {
        DBHelper.clearTables();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void test19_shouldFillCreditFormCard1() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var creditPage = mainPage.buyCredit();
        var authInfo = DataHelper.getAuthInfoAllValidApproved();
        creditPage.fillPaymentForm(authInfo);
        creditPage.checkOperationIsSuccessful();
        assertEquals("APPROVED", DBHelper.getStatusCredit());
    }

    @Test
    void test20_shouldFillCreditFormCard2() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var creditPage = mainPage.buyCredit();
        var authInfo = DataHelper.getAuthInfoAllValidDeclined();
        creditPage.fillPaymentForm(authInfo);
        creditPage.checkOperationIsNotSuccessful();
        assertEquals("DECLINED", DBHelper.getStatusCredit());
    }

    @Test
    void test21_shouldNotFillCreditForm() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var creditPage = mainPage.buyCredit();
        var authInfo = DataHelper.getAuthInfoNoData();
        creditPage.fillPaymentForm(authInfo);
        creditPage.cardNumberError();
        creditPage.cardMonthError();
        creditPage.cardYearError();
        creditPage.cardHolderError();
        creditPage.cardCVCError();
    }

    @Test
    void test22_shouldFillCreditFormNotValidCardNumber() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumberNotValid();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.checkOperationIsNotSuccessful();
    }

    @Test
    void test23_shouldFillCreditFormNotValidCardNumber15() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumberNotValid15();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardNumberError();
    }

    @Test
    void test24_shouldFillCreditFormNotValidCardNumber17() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumberNotValid17();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.checkOperationIsNotSuccessful();
    }

    @Test
    void test25_shouldNotBeHiddenNotificationOnPageCredit() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumberNotValid();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.checkOperationIsNotSuccessful();
        creditPage.checkOperationIsSuccessful();
    }

    @Test
    void test26_shouldFillCreditFormNotValidFormatCardMonth() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonthNotValidFormat();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardMonthError();
    }

    @Test
    void test27_shouldFillCreditFormNotValidCardMonth() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonthNotValid();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardMonthError2();
    }

    @Test
    void test28_shouldFillCreditFormNotValidCardYear() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardYearNotValid1();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardYearError2();
    }

    @Test
    void test29_shouldFillCreditFormPreviousMonthCurrentYear() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var creditPage = mainPage.buyCredit();
        var authInfo = DataHelper.getAuthInfoMonthYear();
        creditPage.fillPaymentForm(authInfo);
        creditPage.cardMonthError2();
    }

    @Test
    void test30_shouldFillCreditFormNotValidCardYear2() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardYearNotValid2();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardYearError3();
    }

    @Test
    void test31_shouldFillCreditFormNotValidCyrillicCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidCyrillic();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardHolderError2();
    }

    @Test
    void test32_shouldFillCreditFormNotValidLatinCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidLatin();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardHolderError2();
    }

    @Test
    void test33_shouldFillCreditFormNotValidNumbersCardHolder() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolderNotValidNumbers();
        var cardCVC =DataHelper.getCardCVC();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardHolderError2();
    }

    @Test
    void test34_shouldFillCreditFormNotValidCardCVC() {
        var mainPage = new MainPage();
        mainPage.buyCredit();
        var cardNumber = DataHelper.getCardNumber1();
        var cardMonth =DataHelper.getCardMonth();
        var cardYear =DataHelper.getCardNextYear();
        var cardHolder =DataHelper.getCardHolder();
        var cardCVC =DataHelper.getCardCVCNotValid();
        var creditPage = new CreditPage();
        creditPage.differentValuesPayment(cardNumber, cardMonth, cardYear,cardHolder, cardCVC);
        creditPage.cardCVCError();
    }
}
