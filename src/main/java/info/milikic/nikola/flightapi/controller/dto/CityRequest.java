package info.milikic.nikola.flightapi.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "City create model")
public class CityRequest {

    @ApiModelProperty(value = "City name", required = true, example = "Mecheria", position = 0)
    @NotEmpty(message = "Name must be provided")
    private String name;

    @ApiModelProperty(value = "Country in which the city resides", required = true, example = "Algeria", position = 1)
    @NotEmpty(message = "Country must be provided")
    private String country;

    @ApiModelProperty(value = "City description", required = true,  example = "Mécheria is a small city situated in Naâma Province, Algeria in the Atlas mountains, capital of Mécheria District.", position = 2)
    @NotEmpty(message = "Description must be provided")
    private String description;

}
