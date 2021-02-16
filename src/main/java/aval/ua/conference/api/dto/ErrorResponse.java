package aval.ua.conference.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ErrorResponse {
    @JsonProperty("error_code")
    String code;
    @JsonProperty("error_message")
    String message;
}
