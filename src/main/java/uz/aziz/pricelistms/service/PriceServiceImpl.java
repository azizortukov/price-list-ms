package uz.aziz.pricelistms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.aziz.pricelistms.entity.Price;
import uz.aziz.pricelistms.model.dto.PriceDto;
import uz.aziz.pricelistms.model.dto.ResponseDto;
import uz.aziz.pricelistms.model.mapper.PriceMapper;
import uz.aziz.pricelistms.repository.PriceRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public ResponseDto<PriceDto> getById(Long id) {
        try {
            Optional<Price> priceOptional = priceRepository.findById(id);
            if (priceOptional.isPresent()) {
                PriceDto priceDto = priceMapper.toDto(priceOptional.get());
                log.info("Price with id = {} found", id);
                return new ResponseDto<>(priceDto);
            } else {
                log.info("Price with id = {} not found", id);
                return new ResponseDto<>("Price with id = %d not found".formatted(id));
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException in getById: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Couldn't access to the database, please contact admin");

        } catch (Exception e) {
            log.error("Exception in getAll: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Internal server error, please contact admin");

        }
    }

    @Override
    public ResponseDto<List<PriceDto>> getAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Integer page, Integer size) {
        try {
            page = page < 0 ? 0 : page;
            size = size < 1 ? 10 : size;
            Pageable pageable = PageRequest.of(page, size);
            List<Price> all = priceRepository.findAll(serviceId, mxikCode, pricePerUnit, packageCode, pageable);

            log.info("Retrieved {} prices", all.size());
            return new ResponseDto<>(priceMapper.toDtoList(all));

        } catch (DataAccessException e) {
            log.error("DataAccessException in getAll: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Couldn't access the database, please contact admin");

        } catch (Exception e) {
            log.error("Exception in getAll: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Internal server error, please contact admin");
        }
    }

    @Override
    public ResponseDto<PriceDto> save(PriceDto priceDto) {
        try {
            Price price = priceMapper.toEntity(priceDto);
            price.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")));
            if (priceRepository.existsByServiceIdAndMxikCode(price.getServiceId(), price.getMxikCode())) {
                return new ResponseDto<>("Price with serviceId = %d and mxikCode = '%s' already exist"
                        .formatted(price.getServiceId(), price.getMxikCode()));
            }

            priceRepository.save(price);

            log.info("Price with id = {} saved", price.getId());
            return new ResponseDto<>(priceMapper.toDto(price));

        } catch (DataAccessException e) {
            log.error("DataAccessException in save: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Couldn't access to the database, please contact admin");

        } catch (Exception e) {
            log.error("Exception in save: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Internal server error, please contact admin");

        }
    }

    @Override
    public ResponseDto<PriceDto> updateById(Long id, PriceDto priceDto) {
        try {
            if (!priceRepository.existsById(id)) {
                return new ResponseDto<>("Price with id = %s not found".formatted(id));
            }
            if (priceRepository.existsByServiceIdAndMxikCode(priceDto.serviceId(), priceDto.mxikCode())) {
                return new ResponseDto<>("Price with serviceId = %d and mxikCode = '%s' already exist"
                        .formatted(priceDto.serviceId(), priceDto.mxikCode()));
            }

            Price price = priceMapper.toEntity(priceDto);
            price.setId(id);
            Price updatedPrice = priceRepository.save(price);
            return new ResponseDto<>(priceMapper.toDto(updatedPrice));

        } catch (DataAccessException e) {
            log.error("DataAccessException in updateById: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Couldn't access to the database, please contact admin");

        } catch (Exception e) {
            log.error("Exception in updateById: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Internal server error, please contact admin");

        }
    }

    @Override
    public ResponseDto<Boolean> deleteById(Long id) {
        try {
            priceRepository.deleteById(id);
            log.info("Price with id = {} has been deleted", id);
            return new ResponseDto<>();

        } catch (DataAccessException e) {
            log.error("DataAccessException in deleteById: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Couldn't access to the database, please contact admin");

        } catch (Exception e) {
            log.error("Exception in deleteById: {}", e.getLocalizedMessage());
            return new ResponseDto<>("Internal server error, please contact admin");

        }
    }

}