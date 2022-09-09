package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login'] span");
    private SelenideElement errorNotification = $(".notification__content");

    private SelenideElement closeErrorNotificationButton = $(".icon-button__text");

    public void validLogin(User info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public void shouldAppearErrorNotification() {
        errorNotification.shouldBe(visible);
    }

   public void shouldClickButton() {
       loginButton.click();
   }

   public void shouldCloseErrorNotification() {
        closeErrorNotificationButton.click();
   }
}
