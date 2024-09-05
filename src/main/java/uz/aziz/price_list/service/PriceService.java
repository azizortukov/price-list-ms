package uz.aziz.price_list.service;

import uz.aziz.price_list.model.dto.PriceDto;
import uz.aziz.price_list.model.dto.ResponseDto;

import java.util.List;

public interface PriceService {

    ResponseDto<PriceDto> getById(Long id);

    ResponseDto<List<PriceDto>> getAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Integer page, Integer size);

    ResponseDto<PriceDto> save(PriceDto priceDto);

}
