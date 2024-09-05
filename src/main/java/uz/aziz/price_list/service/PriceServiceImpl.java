package uz.aziz.price_list.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.aziz.price_list.entity.Price;
import uz.aziz.price_list.model.dto.PriceDto;
import uz.aziz.price_list.model.dto.ResponseDto;
import uz.aziz.price_list.model.mapper.PriceMapper;
import uz.aziz.price_list.repo.PriceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public ResponseDto<PriceDto> getById(Long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()) {
            PriceDto priceDto = priceMapper.toDto(priceOptional.get());
            return new ResponseDto<>(priceDto);
        } else {
            return new ResponseDto<>("Price not found");
        }
    }

    @Override
    public ResponseDto<List<PriceDto>> getAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Price> all = priceRepository.findAll(serviceId, mxikCode, pricePerUnit, packageCode, pageable);
        return new ResponseDto<>(priceMapper.toDtoList(all));
    }

    @Override
    public ResponseDto<PriceDto> save(PriceDto priceDto) {
        try {
            Price savedPrice = priceRepository.save(priceMapper.toEntity(priceDto));
            return new ResponseDto<>(priceMapper.toDto(savedPrice));
        } catch (DataIntegrityViolationException e) {
            String msg = "Price with (mxik_code = '%s') and (price_per_unit = %d) already exists!"
                    .formatted(priceDto.mxikCode(), priceDto.pricePerUnit());
            return new ResponseDto<>(msg);
        }
    }

}
