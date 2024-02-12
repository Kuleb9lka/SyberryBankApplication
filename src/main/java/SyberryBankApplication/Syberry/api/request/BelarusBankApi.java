package SyberryBankApplication.Syberry.api.request;


import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.dto.belarusbank.BelarusBankRateDto;
import SyberryBankApplication.Syberry.api.model.belarusbank.BelarusBankRates;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BelarusBankApi {

    private final RestTemplate restTemplate = new RestTemplate();

    private BelarusBankRates getAllRates(String city){

        String url = "https://belarusbank.by/api/kursExchange?city=" + city;

        return restTemplate.getForObject(url, BelarusBankRates.class);
    }

    @SneakyThrows
    public BelarusBankRateDto getRateByCurrName(String name){

        BelarusBankRates allRates = getAllRates(Constant.DEFAULT_BELARUS_BANK_CITY);

        if (name.equalsIgnoreCase(Constant.USD)){

            return new BelarusBankRateDto(Constant.USD, allRates.getUsdSell());
        } else if(name.equalsIgnoreCase(Constant.RUB)){

            return new BelarusBankRateDto(Constant.RUB, allRates.getRubSell());
        } else if(name.equalsIgnoreCase(Constant.EUR)){

            return new BelarusBankRateDto(Constant.EUR, allRates.getEurSell());
        } else {

            throw new Exception("No such currency.");
        }
    }
}
