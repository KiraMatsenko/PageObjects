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

    public VerificationPage validLogin(DataHelper.UserInfo user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginObject invalidLogin(DataHelper.UserInfo user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
        return new LoginObject();
    }
}
