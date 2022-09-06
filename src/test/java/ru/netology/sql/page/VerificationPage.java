package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify'] span");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String info) {
        codeField.setValue(info);
        verifyButton.click();
        return new DashboardPage();
    }

}
