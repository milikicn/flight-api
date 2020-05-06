package info.milikic.nikola.flightapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@ApiModel(description = "City response model")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CityResponse extends CityRequest {

    @ApiModelProperty(value = "City id", example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(value = "City commenrts", position = 1)
    private List<CommentResponse> comments;

}
