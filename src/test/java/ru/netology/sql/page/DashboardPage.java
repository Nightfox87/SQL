package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");


    public void shouldAppearPersonalAccountHeading() {
        heading.shouldBe(visible);
    }


}
