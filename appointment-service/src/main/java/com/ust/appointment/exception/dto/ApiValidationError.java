package com.ust.appointment.exception.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public final class ApiValidationError extends ApiErrorBase {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
