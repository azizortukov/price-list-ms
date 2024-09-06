package uz.aziz.price_list.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.aziz.price_list.model.dto.PriceDto;
import uz.aziz.price_list.model.dto.ResponseDto;
import uz.aziz.price_list.service.PriceService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price_list")
@RequiredArgsConstructor
@Tag(name = "Price API", description = "CRUD operations of prices")
public class PriceController {

    private final PriceService priceService;

    @Operation(summary = "Getting price by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = """
                    If price is found, response will be provided in data field. Otherwise,
                    error message is provided.""",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseDto<PriceDto> getById(@PathVariable Long id) {
        return priceService.getById(id);
    }

    @Operation(summary = "Getting list of prices by custom params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = """
                    List of prices will be provided in data field.""",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
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

    @Operation(summary = "Saving price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = """
                    If price is successfully saved, it will be provided in data field. Otherwise,
                    error message is provided.""",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseDto<PriceDto> create(@RequestBody PriceDto priceDto) {
        return priceService.save(priceDto);
    }

    @Operation(summary = "Updating price by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = """
                    If price is successfully updated, it will be provided in data field. Otherwise,
                     error message is provided.""",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/{id}")
    public ResponseDto<PriceDto> update(@PathVariable Long id, @RequestBody PriceDto priceDto) {
        return priceService.updateById(id, priceDto);
    }

    @Operation(summary = "Deleting price by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = """
                    Price with matching id successfully removed. Even the price doesn't exists by
                    that id, it won't return error message.""",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseDto<Boolean> delete(@PathVariable Long id) {
        return priceService.deleteById(id);
    }

}
