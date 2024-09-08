package uz.aziz.pricelistms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
            log.error("Exception in class {} and method {}: DataAccessException occurred: {}",
                    this.getClass().getName(), "getById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Database error occurred: " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error("Exception in class {} and method {}: NullPointerException occurred: {}",
                    this.getClass().getName(), "getById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Internal error: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            log.error("Exception in class {} and method {}: RuntimeException occurred: {}",
                    this.getClass().getName(), "getById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseDto<List<PriceDto>> getAll(Integer serviceId, String mxikCode, Long pricePerUnit, String packageCode, Integer page, Integer size) {
        try {
            page = page < 0 ? 0 : page;
            size = size < 1 ? 10 : size;
            Pageable pageable = PageRequest.of(page, size);
            List<Price> all = priceRepository.findAll(serviceId, mxikCode, pricePerUnit, packageCode, pageable);
            return new ResponseDto<>(priceMapper.toDtoList(all));
        } catch (DataAccessException e) {
            log.error("Exception in class {} and method {}: DataAccessException occurred: {}",
                    this.getClass().getName(), "getAll", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Database error occurred: " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error("Exception in class {} and method {}: NullPointerException occurred: {}",
                    this.getClass().getName(), "getAll", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Internal error: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            log.error("Exception in class {} and method {}: RuntimeException occurred: {}",
                    this.getClass().getName(), "getAll", e.getLocalizedMessage(), e);
            return new ResponseDto<>("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseDto<PriceDto> save(PriceDto priceDto) {
        try {
            Price savedPrice = priceRepository.save(priceMapper.toEntity(priceDto));
            savedPrice.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")));

            log.info("Price with id = {} saved", savedPrice.getId());

            return new ResponseDto<>(priceMapper.toDto(savedPrice));
        } catch (DataIntegrityViolationException e) {
            log.error("Exception in class {} and method {}: DataIntegrityViolationException occurred: {}",
                    this.getClass().getName(), "save", e.getLocalizedMessage(), e);

            String msg = "Couldn't be saved. Price with (service_id = '%s') and (mxik_code = %d) already exists!"
                    .formatted(priceDto.mxikCode(), priceDto.pricePerUnit());
            return new ResponseDto<>(msg);
        } catch (DataAccessException e) {
            log.error("Exception in class {} and method {}: DataAccessException occurred: {}",
                    this.getClass().getName(), "save", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Database error occurred: " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error("Exception in class {} and method {}: NullPointerException occurred: {}",
                    this.getClass().getName(), "save", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Internal error: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            log.error("Exception in class {} and method {}: RuntimeException occurred: {}",
                    this.getClass().getName(), "save", e.getLocalizedMessage(), e);
            return new ResponseDto<>("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseDto<PriceDto> updateById(Long id, PriceDto priceDto) {
        try {
            if (!priceRepository.existsById(id)) {
                return new ResponseDto<>("Price with id = %s not found".formatted(id));
            }
            Price price = priceMapper.toEntity(priceDto);
            price.setId(id);
            Price updatedPrice = priceRepository.save(price);
            return new ResponseDto<>(priceMapper.toDto(updatedPrice));
        } catch (DataIntegrityViolationException e) {
            log.error("Exception in class {} and method {}: DataIntegrityViolationException occurred: {}",
                    this.getClass().getName(), "updateById", e.getLocalizedMessage(), e);

            String msg = "Couldn't be updated. Price with (service_id = '%s') and (mxik_code = %d) already exists!"
                    .formatted(priceDto.mxikCode(), priceDto.pricePerUnit());
            return new ResponseDto<>(msg);
        } catch (DataAccessException e) {
            log.error("Exception in class {} and method {}: DataAccessException occurred: {}",
                    this.getClass().getName(), "updateById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Database error occurred: " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error("Exception in class {} and method {}: NullPointerException occurred: {}",
                    this.getClass().getName(), "updateById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Internal error: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            log.error("Exception in class {} and method {}: RuntimeException occurred: {}",
                    this.getClass().getName(), "updateById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseDto<Boolean> deleteById(Long id) {
        try {
            priceRepository.deleteById(id);
            log.info("Price with id = {} has been deleted", id);
            return new ResponseDto<>();
        } catch (DataAccessException e) {
            log.error("Exception in class {} and method {}: DataAccessException occurred: {}",
                    this.getClass().getName(), "deleteById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Database error occurred: " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            log.error("Exception in class {} and method {}: NullPointerException occurred: {}",
                    this.getClass().getName(), "deleteById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("Internal error: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            log.error("Exception in class {} and method {}: RuntimeException occurred: {}", this.getClass().getName(), "deleteById", e.getLocalizedMessage(), e);
            return new ResponseDto<>("An unexpected error occurred: " + e.getLocalizedMessage());
        }
    }

}