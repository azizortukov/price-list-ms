package uz.aziz.price_list.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.aziz.price_list.model.dto.PriceDto;
import uz.aziz.price_list.model.dto.ResponseDto;
import uz.aziz.price_list.service.PriceService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price_list")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/{id}")
    public ResponseDto<PriceDto> getById(@PathVariable Long id) {
        return priceService.getById(id);
    }

    @GetMapping
    public ResponseDto<List<PriceDto>> getAll(
            @RequestParam(required = false, name = "service_id") Integer serviceId,
            @RequestParam(required = false, name = "mxik_code") String mxikCode,
            @RequestParam(required = false, name = "price_per_unit") Long pricePerUnit,
            @RequestParam(required = false, name = "package_code") String packageCode,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        return priceService.getAll(serviceId, mxikCode, pricePerUnit, packageCode, page, size);
    }

    @PostMapping
    public ResponseDto<PriceDto> create(@RequestBody PriceDto priceDto) {
        return priceService.save(priceDto);
    }

    @PutMapping("/{id}")
    public ResponseDto<PriceDto> update(@PathVariable Long id, @RequestBody PriceDto priceDto) {
        return priceService.updateById(id, priceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Boolean> delete(@PathVariable Long id) {
        return priceService.deleteById(id);
    }


}
