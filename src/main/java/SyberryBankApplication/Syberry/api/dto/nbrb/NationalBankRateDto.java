package SyberryBankApplication.Syberry.api.dto.nbrb;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationalBankRateDto {

    private long curId;

    private Date date;

    private String curAbbreviation;

    private double curOfficialRate;


    @Override
    public String toString() {
        return "Наименование валюты: " + curAbbreviation + "\n" +
                "курс: " + curOfficialRate + "\n" +
                "дата: " + date;
    }
}
