package uz.aziz.price_list.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * DTO for {@link uz.aziz.price_list.entity.Price}
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