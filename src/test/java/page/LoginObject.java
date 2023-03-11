package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginObject {

    private SelenideElement loginField = $("[type=text]");
    private SelenideElement passwordField = $("[type=password]");
    private SelenideElement loginButton = $("button[data-test-id='action-login']");
    private SelenideElement errorNotification = $("div[data-test-id='error-notification']");

    public void login(DataHelper.UserInfo user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin(DataHelper.UserInfo user) {
        login(user);
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.UserInfo user) {
        login(user);
        errorNotification.shouldBe(Condition.visible);
    }
}
