package SyberryBankApplication.Syberry.api.controller;


import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.service.NationalBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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


}
