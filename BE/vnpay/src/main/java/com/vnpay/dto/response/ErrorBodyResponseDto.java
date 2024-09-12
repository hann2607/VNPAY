package com.vnpay.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.Violation;

import java.net.URI;
import java.util.List;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(value = { "detail", "instance", "status", "parameters" })
public class ErrorBodyResponseDto implements Problem {

    private URI type;
    private String title;
    private String messageCode;
    private List<FieldError> fieldErrors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String messageCode;

        public FieldError(Violation violation) {
            this.field = violation.getField();
            this.messageCode = violation.getMessage();
        }
    }
}
