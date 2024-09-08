package uz.aziz.pricelistms.model.mapper;

import org.mapstruct.*;
import uz.aziz.pricelistms.entity.Price;
import uz.aziz.pricelistms.model.dto.PriceDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);

    List<PriceDto> toDtoList(List<Price> prices);

}