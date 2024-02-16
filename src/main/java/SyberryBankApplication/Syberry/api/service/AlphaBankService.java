package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.ApiMapper;
import SyberryBankApplication.Syberry.api.request.AlphaBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlphaBankService {

    private final ApiMapper mapper;

    private final AlphaBankApi api;

    public AlphaBankRateDto getRateByCurrName(String name) throws Exception {

        List<AlphaBankRateDto> alphaBankRateDtoList = mapper.toAlphaBankRateDto(api.getAllRates().getRates());

        return alphaBankRateDtoList.stream()
                .filter(rate -> rate.getName() != null)
                .filter(rate -> rate.getSellIso().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new Exception("Incorrect currency type"));
    }

    public List<AlphaBankRateDto> getAllRates(){

        return mapper.toAlphaBankRateDto(api.getAllRates().getRates()).stream()
                .filter(rate -> rate.getName() != null)
                .collect(Collectors.toList());
    }

    public List<String> getMainCurrencies(){

        return getAllRates().stream()
                .map(AlphaBankRateDto::getSellIso)
                .filter(curr -> curr.equals(Constant.EUR)
                        || curr.equals(Constant.USD)
                        || curr.equals(Constant.RUB))
                .collect(Collectors.toList());
    }


}
