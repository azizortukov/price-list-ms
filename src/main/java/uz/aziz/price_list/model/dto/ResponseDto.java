package uz.aziz.price_list.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {

    private T data;
    private String errorMessage;
    private LocalDateTime time;

    public ResponseDto() {
        this.time = LocalDateTime.now();
    }

    public ResponseDto(T data) {
        this.data = data;
        this.time = LocalDateTime.now();
    }

    public ResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
        this.time = LocalDateTime.now();
    }
}
