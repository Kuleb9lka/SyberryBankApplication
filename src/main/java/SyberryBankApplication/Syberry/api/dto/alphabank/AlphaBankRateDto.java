package SyberryBankApplication.Syberry.api.dto.alphabank;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlphaBankRateDto {

    private String sellIso;

    private double sellRate;

    private double buyRate;

    private long quantity;

    private String date;

    private String name;

    @Override
    public String toString() {
        return "Наименование валюты: " + sellIso + "\n" +
                "курс продажи:" + sellRate + "\n" +
                "курс покупки: "+ buyRate + "\n" +
                "дата: " + date;
    }
}
