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

    private String sellIso;

    private int sellCode;

    private double buyRate;

    private String buyIso;

    private int buyCode;

    @JsonProperty("quantity")
    private long quantity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private String date;

}
