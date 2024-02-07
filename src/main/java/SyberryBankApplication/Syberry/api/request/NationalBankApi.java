package SyberryBankApplication.Syberry.api.request;


import SyberryBankApplication.Syberry.api.model.NationalBankRate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class NationalBankApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public NationalBankRate getRateByCurrName(String currName){

        String url = "https://api.nbrb.by/exrates/rates/" + currName + "?parammode=2";

        return restTemplate.getForObject(url, NationalBankRate.class);
    }


    public NationalBankRate getRateByCurrNameOnDate(String currName, Date date){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String url = "https://api.nbrb.by/exrates/rates/" + currName + "?ondate="
                + dateFormat.format(date) + "&parammode=2";

        return restTemplate.getForObject(url, NationalBankRate.class);
    }



}
