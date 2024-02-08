package SyberryBankApplication.Syberry.api.model.nbrb;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NationalBankRate {

    @JsonProperty("Cur_ID")
    private long curId;

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

    private int curScale;

    private String curName;

    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;
}
