package SyberryBankApplication.Syberry.api.mapstruct;


import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRate;
import SyberryBankApplication.Syberry.api.model.nbrb.NationalBankRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);

    List<AlphaBankRateDto> toAlphaBankRateDto(List<AlphaBankRate> rateList);

    NationalBankRateDto toNationalBankRateDto(NationalBankRate rate);
}
