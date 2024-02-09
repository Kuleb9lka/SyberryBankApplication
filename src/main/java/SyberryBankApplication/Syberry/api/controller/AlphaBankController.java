package SyberryBankApplication.Syberry.api.controller;

import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.service.AlphaBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alpha-bank")
@RequiredArgsConstructor
public class AlphaBankController {

    private final AlphaBankService service;

    @GetMapping("/{name}")
    public AlphaBankRateDto getRateByCurrName(@PathVariable String name) throws Exception {

        return service.getRateByCurrName(name);
    }
}
