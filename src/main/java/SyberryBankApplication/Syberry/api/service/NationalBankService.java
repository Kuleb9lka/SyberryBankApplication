package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.NationalBankMapper;
import SyberryBankApplication.Syberry.api.model.NationalBankRate;
import SyberryBankApplication.Syberry.api.request.NationalBankApi;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NationalBankService {

    private final NationalBankMapper mapper;

    private final NationalBankApi api;

    public NationalBankService(NationalBankMapper mapper, NationalBankApi api) {
        this.mapper = mapper;
        this.api = api;
    }

    public NationalBankRateDto getRateByCurrName(String name){

        NationalBankRate reteByCurrName = api.getRateByCurrName(name);

        return mapper.toDto(reteByCurrName);
    }

    public NationalBankRateDto getRateByCurrNameOnDate(String name, Date date){

        NationalBankRate rateByCurrNameOnDate = api.getRateByCurrNameOnDate(name, date);

        return mapper.toDto(rateByCurrNameOnDate);
    }

}
