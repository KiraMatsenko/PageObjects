import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.DashboardObject;
import page.LoginObject;
import page.ReplenishObject;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferTests {

    @Test
    void shouldGetErrorLogin() {
        open("http://localhost:7777/");
        Configuration.holdBrowserOpen = true;
        LoginObject loginPage = new LoginObject();
        LoginObject login = loginPage.invalidLogin(DataHelper.invalidUser());
    }

    @Test
    void shouldGetErrorVerify() {
        open("http://localhost:7777/");
        Configuration.holdBrowserOpen = true;
        VerificationPage verify = new LoginObject().validLogin(DataHelper.validUser());
        verify.invalidVerify();
    }

    @Test
    void shouldGetMoneySuccess1() {
        open("http://localhost:7777/");
        Configuration.holdBrowserOpen = true;
        VerificationPage login = new LoginObject().validLogin(DataHelper.validUser());
        DashboardObject verify = new VerificationPage().validVerify();
        DashboardObject replenishInfo = new DashboardObject().selectReplenish1().inputSum("100").inputCard(DataHelper.getCard2());
        int toBalance = replenishInfo.getFirstCardBalance();
        int fromBalance = replenishInfo.getSecondCardBalance();
        DashboardObject replenish = new ReplenishObject().replenishButton();
        Assertions.assertEquals(toBalance + 100, replenishInfo.getFirstCardBalance());
        Assertions.assertEquals(fromBalance - 100, replenishInfo.getSecondCardBalance());
    }

    @Test
    void shouldGetMoneySuccess2() {
        open("http://localhost:7777/");
        Configuration.holdBrowserOpen = true;
        VerificationPage login = new LoginObject().validLogin(DataHelper.validUser());
        DashboardObject verify = new VerificationPage().validVerify();
        DashboardObject replenishInfo = new DashboardObject().selectReplenish2().inputSum("100").inputCard(DataHelper.getCard1());
        int toBalance = replenishInfo.getSecondCardBalance();
        int fromBalance = replenishInfo.getFirstCardBalance();
        DashboardObject replenish = new ReplenishObject().replenishButton();
        Assertions.assertEquals(toBalance + 100, replenishInfo.getSecondCardBalance());
        Assertions.assertEquals(fromBalance - 100, replenishInfo.getFirstCardBalance());
    }
}
