package SyberryBankApplication.Syberry.api.dto.belarusbank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BelarusBankRateDto {

    private String currencyName;

    private double rate;

    @Override
    public String toString() {
        return "Наименование валюты: " + currencyName + "\n" +
                "курс: " + rate;
    }
}
