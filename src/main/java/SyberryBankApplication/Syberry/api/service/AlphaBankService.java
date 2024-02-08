package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.AlphaBankMapper;
import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRate;
import SyberryBankApplication.Syberry.api.request.AlphaBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlphaBankService {

    private final AlphaBankMapper mapper;

    private final AlphaBankApi api;

    public AlphaBankRateDto getRateByCurrName(String name) throws Exception {

        return getAllCurrencies().stream()
                .filter(rate -> rate.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new Exception("Incorrect currency type"));
    }

    private List<AlphaBankRateDto> getAllCurrencies(){

        AlphaBankRate[] allRates = api.getAllRates();

        return Arrays.asList(mapper.toDtoList(allRates));
    }


}
