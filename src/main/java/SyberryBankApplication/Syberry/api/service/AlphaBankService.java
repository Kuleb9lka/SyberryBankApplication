package SyberryBankApplication.Syberry.api.service;

import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.mapstruct.AlphaBankMapper;
import SyberryBankApplication.Syberry.api.request.AlphaBankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlphaBankService {

    private final AlphaBankMapper mapper;

    private final AlphaBankApi api;

    public AlphaBankRateDto getRateByCurrName(String name) throws Exception {

        List<AlphaBankRateDto> alphaBankRateDtoList = mapper.toDtoList(api.getAllRates().getRates());

        return alphaBankRateDtoList.stream()
                .filter(rate -> rate.getName() != null)
                .filter(rate -> rate.getSellIso().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new Exception("Incorrect currency type"));
    }


}