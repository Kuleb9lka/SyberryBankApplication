package SyberryBankApplication.Syberry.api.dto.nbrb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NationalBankRateDto {

    private long currId;

    private Date date;

    private String curAbbreviation;

    private double curOfficialRate;
}
