package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[name='code']");
    private SelenideElement verifyButton = $("button[data-test-id='action-verify']");
    private SelenideElement verifyError = $("div[data-test-id='error-notification']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void verification(String code) {
        codeField.setValue(code);
        verifyButton.click();
    }

    public DashboardObject validVerify(String code) {
        verification(code);
        return new DashboardObject();
    }

    public void findErrorNotificationMessage() {
        verifyError.shouldBe(visible);
    }
}
