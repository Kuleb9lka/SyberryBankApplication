package SyberryBankApplication.Syberry.api.request;

import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRates;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlphaBankApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public AlphaBankRates getAllRates(){

        String url = "https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates";

        return restTemplate.getForObject(url, AlphaBankRates.class);
    }
}
