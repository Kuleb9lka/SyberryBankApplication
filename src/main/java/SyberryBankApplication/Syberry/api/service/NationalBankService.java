package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.ApiMapper;
import SyberryBankApplication.Syberry.api.model.nbrb.NationalBankRate;
import SyberryBankApplication.Syberry.api.request.NationalBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NationalBankService {

    private final ApiMapper mapper;

    private final NationalBankApi api;

    public NationalBankRateDto getRateByCurrName(String name){

        NationalBankRate reteByCurrName = api.getRateByCurrName(name);

        return mapper.toNationalBankRateDto(reteByCurrName);
    }

    public NationalBankRateDto getRateByCurrNameOnDate(String name, Date date){

        NationalBankRate rateByCurrNameOnDate = api.getRateByCurrNameOnDate(name, date);

        return mapper.toNationalBankRateDto(rateByCurrNameOnDate);
    }

    public List<NationalBankRateDto> getAllRates(){

        List<NationalBankRate> allRates = List.of(api.getAllRates());

        return mapper.toNationalBankRateDtoSet(allRates);
    }

    public List<NationalBankRateDto> getAllRatesOnDate(Date date){

        List<NationalBankRate> allRatesOnDate = List.of(api.getAllRatesOnDate(date));

        return mapper.toNationalBankRateDtoSet(allRatesOnDate);
    }

    public List<String> getMainCurrencies(){

        return getAllRates().stream()
                .map(NationalBankRateDto::getCurAbbreviation)
                .filter(curr -> curr.equals(Constant.EUR)
                        || curr.equals(Constant.USD)
                        || curr.equals(Constant.RUB))
                .collect(Collectors.toList());
    }

}
