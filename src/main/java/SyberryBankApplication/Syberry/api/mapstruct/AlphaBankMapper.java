package SyberryBankApplication.Syberry.api.mapstruct;


import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.model.alphabank.AlphaBankRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlphaBankMapper {

    AlphaBankMapper INSTANCE = Mappers.getMapper(AlphaBankMapper.class);

    AlphaBankRateDto[] toDtoList(AlphaBankRate[] rate);
}
