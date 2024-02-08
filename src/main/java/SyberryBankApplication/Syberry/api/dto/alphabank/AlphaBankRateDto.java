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

    private double sellRate;

    private long quantity;

    private String name;

    private String date;
}
