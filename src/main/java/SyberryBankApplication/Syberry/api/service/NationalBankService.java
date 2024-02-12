package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.ApiMapper;
import SyberryBankApplication.Syberry.api.model.nbrb.NationalBankRate;
import SyberryBankApplication.Syberry.api.request.NationalBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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

}
