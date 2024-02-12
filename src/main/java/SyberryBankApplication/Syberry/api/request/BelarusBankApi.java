package SyberryBankApplication.Syberry.api.request;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BelarusBankApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public void getAllRates(){

        String url = "https://belarusbank.by/api/kursExchange?city=Гомель";

        Object[] forObject = restTemplate.getForObject(url, Object[].class);


    }
}
