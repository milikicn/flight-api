package info.milikic.nikola.flightapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic response model for different types of responses with single value, boolean flags etc.
 * 
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Generic response model")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class GenericResponse<T> {

	@ApiModelProperty(value = "Generic response value", required = true)
	private T value;

}
