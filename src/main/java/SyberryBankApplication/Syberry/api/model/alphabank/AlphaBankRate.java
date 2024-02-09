package SyberryBankApplication.Syberry.api.model.alphabank;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlphaBankRate {

    @JsonProperty("sellRate")
    private double sellRate;

    @JsonProperty("sellIso")
    private String sellIso;

    @JsonProperty("sellCode")
    private int sellCode;

    @JsonProperty("buyRate")
    private double buyRate;

    @JsonProperty("buyIso")
    private String buyIso;

    @JsonProperty("buyCode")
    private int buyCode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private String date;

}
