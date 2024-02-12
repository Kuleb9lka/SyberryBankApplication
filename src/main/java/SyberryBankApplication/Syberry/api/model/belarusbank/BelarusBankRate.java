package SyberryBankApplication.Syberry.api.model.belarusbank;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BelarusBankRate {

    @JsonProperty("USD_in")
    private double usdBuy;

    @JsonProperty("USD_out")
    private double usdSell;

    @JsonProperty("EUR_in")
    private double eurBuy;

    @JsonProperty("EUR_out")
    private double eurSell;

    @JsonProperty("RUB_in")
    private double rubBuy;

    @JsonProperty("RUB_out")
    private double rubSell;


}
