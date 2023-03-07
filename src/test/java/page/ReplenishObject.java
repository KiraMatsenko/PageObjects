package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishObject {
    private SelenideElement whereFrom = $("input[placeholder='0000 0000 0000 0000']");

    private SelenideElement amount = $("[type=text]");
    private SelenideElement replenishButton = $("button[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("button[data-test-id='action-cancel']");
    private SelenideElement replenishError = $("div[data-test-id='error-notification']");

    public ReplenishObject() {
        amount.shouldBe(visible);
    }

    public DashboardObject replenishCancellation() {  //возвращаемся на страницу с картами
        cancelButton.click();
        return new DashboardObject();
    }

    public DashboardObject replenishButton() { //подтверждаем пополнение
        replenishButton.click();
        return new DashboardObject();
    }

    public void inputSum(String sum) { // вводим сумму пополнения
        amount.setValue(sum);
    }

    public void inputCard(String cardNumber) { // вводим номер карты с которой переводим средства
        whereFrom.setValue(cardNumber);
    }
}
