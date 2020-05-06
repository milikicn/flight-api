package info.milikic.nikola.flightapi.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ApiModel(description = "Comment create/update model")
public class CommentRequest {

    @ApiModelProperty(value = "Comment description", required = true, example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.", position = 0)
    @NotEmpty(message = "Description must be provided")
    private String description;

}
