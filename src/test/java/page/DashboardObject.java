package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardObject {

    private SelenideElement myCards = $("h2[data-test-id='dashboard']");
    private SelenideElement cardButton1 = $x("//div[text()='**** **** **** 0001']//span[text()='Пополнить']");
    private SelenideElement cardButton2 = $x("//div[text()='**** **** **** 0002']//span[text()='Пополнить']");
    private SelenideElement reloadButton = $("button[data-test-id='button-reload']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardObject() {
        myCards.shouldBe(visible);
    }

    public void refresh() { // обновлем страницу
        reloadButton.click();
    }

    public ReplenishObject selectReplenish1() { // выбираем пополнение карты 1
        cardButton1.click();
        return new ReplenishObject();
    }

    public ReplenishObject selectReplenish2() { // выбираем пополнение карты 2
        cardButton2.click();
        return new ReplenishObject();
    }

    public int getFirstCardBalance() {
        String text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        String text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
