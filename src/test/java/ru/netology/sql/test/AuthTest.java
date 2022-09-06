package ru.netology.sql.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @AfterAll
    public static void clearTables(){
        DataHelper.shouldClearDataFromTables();
    }

    @Test
    void shouldLoginWithFirstAccount() {
        var loginPage = new LoginPage();
        var user = DataHelper.getFirstUserInfo();
        loginPage.validLogin(user);
        var verificationPage = new VerificationPage();
        var verificationCode = DataHelper.getVerificationCode(user);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.shouldAppearPersonalAccountHeading();
    }
    @Test
    void shouldLoginWithSecondAccount() {
        var loginPage = new LoginPage();
        var user = DataHelper.getSecondUserInfo();
        loginPage.validLogin(user);
        var verificationPage = new VerificationPage();
        var verificationCode = DataHelper.getVerificationCode(user);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.shouldAppearPersonalAccountHeading();
    }

    @Test
    void shouldNotLoginWithIncorrectAccount() {
        var loginPage = new LoginPage();
        var user = DataHelper.getIncorrectUserInfo();
        loginPage.validLogin(user);
        loginPage.shouldAppearErrorNotification();
    }

    @Test
    void shouldBlockAfterThreeLoginsWithIncorrectAccount() {
        var loginPage = new LoginPage();
        var user = DataHelper.getIncorrectUserInfo();
        loginPage.validLogin(user);
        $("[data-test-id='action-login'] span").click();
        $("[data-test-id='action-login'] span").click();
        $("[data-test-id='action-login'] span").click();
    }

}
