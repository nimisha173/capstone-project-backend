package com.ust.appointment.exception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class ApiError extends ApiErrorBase {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<ApiValidationError> fieldErrors;

    private List<ApiValidationError> globalErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
        fieldErrors = new ArrayList<>();
        globalErrors = new ArrayList<>();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status,ex);
        this.debugMessage = ex.getMessage();
    }

}
