package SyberryBankApplication.Syberry.api.controller;


import SyberryBankApplication.Syberry.api.dto.belarusbank.BelarusBankRateDto;
import SyberryBankApplication.Syberry.api.service.BelarusBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/belarus-bank")
@RequiredArgsConstructor
public class BelarusBankController {

    private final BelarusBankService service;

    @GetMapping("/rate/{name}")
    public BelarusBankRateDto getRateByCurrName(@PathVariable String name){

        return service.getRateByCurrName(name);
    }
}
