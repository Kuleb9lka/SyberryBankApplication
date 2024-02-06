package SyberryBankApplication.Syberry.api.controller;


import SyberryBankApplication.Syberry.api.model.NationalBankRate;
import SyberryBankApplication.Syberry.api.request.NationalBankApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/nbrb")
public class NationalBankController {

    private final NationalBankApi nationalBankApi;

    public NationalBankController(NationalBankApi nationalBankApi) {
        this.nationalBankApi = nationalBankApi;
    }

    @GetMapping("/rate/{currName}")
    public NationalBankRate getRateByCurrName(@PathVariable String currName){

        return nationalBankApi.getReteByCurrName(currName);
    }

    @GetMapping("/rate-on-date/{currName}")
    public NationalBankRate getRateByCurrNameOnDate(@PathVariable String currName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){

        return nationalBankApi.getRateByCurrNameOnDate(currName, date);
    }


}
