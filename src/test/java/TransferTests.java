import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardObject;
import page.LoginObject;
import page.ReplenishObject;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferTests {

    @Test
    void shouldGetErrorLogin() {
        LoginObject loginPage = open("http://localhost:7777/", LoginObject.class);
        loginPage.invalidLogin(DataHelper.invalidUser());
    }

    @Test
    void shouldGetErrorVerify() {
        LoginObject loginPage = open("http://localhost:7777/", LoginObject.class);
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.validUser());
        verificationPage.verification("12346");
        verificationPage.findErrorNotificationMessage();
    }

    @Test
    void shouldGetMoneySuccess1() {
        LoginObject loginPage = open("http://localhost:7777/", LoginObject.class);
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.validUser());
        DashboardObject dashboardPage = verificationPage.validVerify(DataHelper.getCorrectCode());
        int toBalance = dashboardPage.getFirstCardBalance();
        int fromBalance = dashboardPage.getSecondCardBalance();
        ReplenishObject replenishPage = dashboardPage.selectReplenish1();
        replenishPage.inputSum("100");
        replenishPage.inputCard(DataHelper.getCard2());
        replenishPage.replenishButton();
        Assertions.assertEquals(toBalance + 100, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(fromBalance - 100, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldGetMoneySuccess2() {
        LoginObject loginPage = open("http://localhost:7777/", LoginObject.class);
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.validUser());
        DashboardObject dashboardPage = verificationPage.validVerify(DataHelper.getCorrectCode());
        int toBalance = dashboardPage.getFirstCardBalance();
        int fromBalance = dashboardPage.getSecondCardBalance();
        ReplenishObject replenishPage = dashboardPage.selectReplenish2();
        replenishPage.inputSum("100");
        replenishPage.inputCard(DataHelper.getCard1());
        replenishPage.replenishButton();
        Assertions.assertEquals(toBalance + 100, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(fromBalance - 100, dashboardPage.getFirstCardBalance());
    }
}
