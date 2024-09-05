package uz.aziz.price_list.model.mapper;

import org.mapstruct.*;
import uz.aziz.price_list.entity.Price;
import uz.aziz.price_list.model.dto.PriceDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);

    List<PriceDto> toDtoList(List<Price> prices);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Price partialUpdate(PriceDto priceDto, @MappingTarget Price price);
}