package SyberryBankApplication.Syberry.api.mapstruct;


import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlphaBankMapper {

    AlphaBankMapper INSTANCE = Mappers.getMapper(AlphaBankMapper.class);

    List<AlphaBankRateDto> toDtoList(List<AlphaBankRate> rateList);
}
