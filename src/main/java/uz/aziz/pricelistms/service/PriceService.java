package uz.aziz.pricelistms.service;

import uz.aziz.pricelistms.model.dto.PriceDto;
import uz.aziz.pricelistms.model.dto.ResponseDto;

import java.util.List;

public interface PriceService {

    ResponseDto<PriceDto> getById(Long id);

    ResponseDto<List<PriceDto>> getAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Integer page, Integer size);

    ResponseDto<PriceDto> save(PriceDto priceDto);

    ResponseDto<PriceDto> updateById(Long id, PriceDto priceDto);

    ResponseDto<Boolean> deleteById(Long id);
}
