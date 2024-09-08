package uz.aziz.pricelistms.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@EqualsAndHashCode
@Getter
public class ResponseDto<T> {

    private T data;
    private String errorMessage;
    private LocalDateTime time;

    public ResponseDto() {
        this.time = LocalDateTime.now(ZoneId.of("Asia/Tashkent"));
    }

    public ResponseDto(T data) {
        this.data = data;
        this.time = LocalDateTime.now(ZoneId.of("Asia/Tashkent"));
    }

    public ResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
        this.time = LocalDateTime.now(ZoneId.of("Asia/Tashkent"));
    }
}
