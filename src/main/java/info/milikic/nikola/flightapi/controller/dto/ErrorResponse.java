package info.milikic.nikola.flightapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import info.milikic.nikola.flightapi.vo.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Rest error model used to map exceptions into REST HTTP status
 *
 */
@Data
@AllArgsConstructor
@ApiModel(description = "Model that represents error response from REST server")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = Include.NON_EMPTY)
public class ErrorResponse {

    @ApiModelProperty(value = "Unique error code", required = true, example = "BAD_REQUEST", position = 0)
    private ErrorCode code;

    @ApiModelProperty(value = "Developer message that explains what happened", required = true, example = "Null Pointer Exception", position = 1)
    private String developerMessage;

    @ApiModelProperty(value = "User friendly error message", required = true, example = "Invalid input parameters", position = 2)
    private String userMessage;

}
