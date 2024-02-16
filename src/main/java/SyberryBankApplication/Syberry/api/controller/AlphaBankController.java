package SyberryBankApplication.Syberry.api.controller;

import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.service.AlphaBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/alpha-bank")
@RequiredArgsConstructor
public class AlphaBankController {

    private final AlphaBankService service;

    @GetMapping("/rate/{name}")
    public AlphaBankRateDto getRateByCurrName(@PathVariable String name) throws Exception {

        return service.getRateByCurrName(name);
    }

    @GetMapping("/rates")
    public List<AlphaBankRateDto> getAllRates(){

        return service.getAllRates();
    }

    @GetMapping("/currency-names")
    public List<String> getAllCurrencies(){

        for (String str : service.getMainCurrencies()) {

            System.out.println(str);
        }

        return service
                .getMainCurrencies();
    }
}
