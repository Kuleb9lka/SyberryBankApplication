package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.dto.belarusbank.BelarusBankRateDto;
import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.model.belarusbank.BelarusBankRates;
import SyberryBankApplication.Syberry.api.request.BelarusBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BelarusBankService {

    private final BelarusBankApi api;

    public BelarusBankRateDto getRateByCurrName(String name) throws Exception {

        return api.getRateByCurrName(name);
    }

    public List<BelarusBankRateDto> getAllRates(){

        BelarusBankRates[] allRates = api.getAllRates(Constant.DEFAULT_BELARUS_BANK_CITY);

        return List.of(new BelarusBankRateDto(Constant.USD, allRates[0].getUsdSell()),
                new BelarusBankRateDto(Constant.RUB, allRates[0].getRubSell()),
                new BelarusBankRateDto(Constant.EUR, allRates[0].getEurSell()));

    }

    public List<String> getMainCurrencies(){

        return getAllRates().stream()
                .map(BelarusBankRateDto::getCurrencyName)
                .filter(curr -> curr.equals(Constant.EUR)
                        || curr.equals(Constant.USD)
                        || curr.equals(Constant.RUB))
                .collect(Collectors.toList());
    }
}
