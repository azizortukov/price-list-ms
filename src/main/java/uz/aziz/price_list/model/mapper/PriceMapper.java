package uz.aziz.price_list.model.mapper;

import org.mapstruct.*;
import uz.aziz.price_list.entity.Price;
import uz.aziz.price_list.model.dto.PriceDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {
    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Price partialUpdate(PriceDto priceDto, @MappingTarget Price price);
}