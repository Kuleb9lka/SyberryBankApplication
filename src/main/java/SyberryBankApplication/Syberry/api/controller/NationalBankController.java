package SyberryBankApplication.Syberry.api.controller;


import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.service.NationalBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/nbrb")
@RequiredArgsConstructor
public class NationalBankController {

    private final NationalBankService service;

    @GetMapping("/rate/{currName}")
    public NationalBankRateDto getRateByCurrName(@PathVariable String currName){

        return service.getRateByCurrName(currName);
    }

    @GetMapping("/rate-on-date/{currName}")
    public NationalBankRateDto getRateByCurrNameOnDate(@PathVariable String currName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){

        return service.getRateByCurrNameOnDate(currName, date);
    }

    @GetMapping("/rates")
    public List<NationalBankRateDto> getAllRates(){

        return service.getAllRates();
    }

    @GetMapping("/rates-on-date")
    public List<NationalBankRateDto> getAllRatesOnDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){

        return service.getAllRatesOnDate(date);
    }

    @GetMapping("/currencies")
    public List<String> getAllCurrencies(){

        return service.getMainCurrencies();
    }


}
