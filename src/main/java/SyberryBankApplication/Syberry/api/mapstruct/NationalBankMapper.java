package SyberryBankApplication.Syberry.api.mapstruct;


import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.model.nbrb.NationalBankRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NationalBankMapper {

    NationalBankMapper INSTANCE = Mappers.getMapper(NationalBankMapper.class);

    NationalBankRateDto toDto(NationalBankRate rate);
}
