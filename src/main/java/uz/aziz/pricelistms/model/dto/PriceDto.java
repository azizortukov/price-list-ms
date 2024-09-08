package uz.aziz.pricelistms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * DTO for {@link uz.aziz.pricelistms.entity.Price}
 */
public record PriceDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        Integer serviceId,
        String mxikCode,
        Long pricePerUnit,
        String packageCode

) implements Serializable {
}