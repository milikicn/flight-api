package info.milikic.nikola.flightapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ApiModel(description = "Comment create model")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CommentResponse {

    @ApiModelProperty(value = "Comment id", example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(value = "Comment description", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.", position = 1)
    private String description;

    @ApiModelProperty(value = "Time created", example = "2020-04-04T07:11:50.509", position = 2)
    private LocalDateTime timeCreated;

    @ApiModelProperty(value = "Time modified", example = "2020-04-05T08:34:10.509", position = 3)
    private LocalDateTime timeModified;
}
