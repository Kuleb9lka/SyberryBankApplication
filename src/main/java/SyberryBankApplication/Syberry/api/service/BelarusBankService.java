package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.dto.belarusbank.BelarusBankRateDto;
import SyberryBankApplication.Syberry.api.request.BelarusBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BelarusBankService {

    private final BelarusBankApi api;

    public BelarusBankRateDto getRateByCurrName(String name){

        return api.getRateByCurrName(name);
    }
}
