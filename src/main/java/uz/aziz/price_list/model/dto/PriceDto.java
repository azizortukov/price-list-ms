package uz.aziz.price_list.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * DTO for {@link uz.aziz.price_list.entity.Price}
 */
public record PriceDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        long id,
        int serviceId,
        String mxikCode,
        long pricePerUnit,
        String packageCode

) implements Serializable {
}