package SyberryBankApplication.Syberry.api.request;

import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlphaBankApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public AlphaBankRate[] getAllRates(){

        String url = "https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates";

        return restTemplate.getForObject(url, AlphaBankRate[].class);
    }
}
