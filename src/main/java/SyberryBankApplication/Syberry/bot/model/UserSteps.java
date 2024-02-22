package SyberryBankApplication.Syberry.bot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSteps {

    private String bankName;

    private String currencyName;

    private boolean isOnDateChosen;

    public UserSteps(String bankName) {
        this.bankName = bankName;
    }

    public UserSteps(String bankName, String currencyName) {
        this.bankName = bankName;
        this.currencyName = currencyName;
    }

    public UserSteps(String bankName, String currencyName, boolean isOnDateChosen) {
        this.bankName = bankName;
        this.currencyName = currencyName;
        this.isOnDateChosen = isOnDateChosen;
    }
}
