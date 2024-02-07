package SyberryBankApplication.Syberry.api.controller;


import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.model.NationalBankRate;
import SyberryBankApplication.Syberry.api.request.NationalBankApi;
import SyberryBankApplication.Syberry.api.service.NationalBankService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/nbrb")
public class NationalBankController {

    private final NationalBankService service;

    public NationalBankController(NationalBankService service) {
        this.service = service;
    }

    @GetMapping("/rate/{currName}")
    public NationalBankRateDto getRateByCurrName(@PathVariable String currName){

        return service.getRateByCurrName(currName);
    }

    @GetMapping("/rate-on-date/{currName}")
    public NationalBankRateDto getRateByCurrNameOnDate(@PathVariable String currName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){

        return service.getRateByCurrNameOnDate(currName, date);
    }


}
