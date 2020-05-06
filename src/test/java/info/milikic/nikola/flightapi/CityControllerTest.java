package info.milikic.nikola.flightapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.milikic.nikola.flightapi.controller.dto.CityRequest;
import info.milikic.nikola.flightapi.controller.dto.CityResponse;
import info.milikic.nikola.flightapi.vo.ServiceConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:data-initialize.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:data-truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CityControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(this.applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails("admin")
    @DisplayName("POST city success")
    public void givenNoCities_whenCreateNewCity_success() throws Exception {
        CityRequest request = new CityRequest()
                .setName("Geraldton")
                .setCountry("Canada")
                .setDescription("Geraldton, ON, population centre, population 1,828 (2016 census), 1,810 (2011 census). Incorporated as a town in 1937, in 2001 Geraldton was amalgamated with several other communities to create the Municipality of Greenstone.");

        ResultActions resultActions =
                mockMvc
                    .perform(
                            post(ServiceConst.CITY_API)
                                    .content(objectMapper.writeValueAsString(request))
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        CityResponse cityResponse = objectMapper.readValue(contentAsString, CityResponse.class);

        Assertions.assertEquals(request.getName(), cityResponse.getName());
        Assertions.assertEquals(request.getCountry(), cityResponse.getCountry());
        Assertions.assertEquals(request.getDescription(), cityResponse.getDescription());
    }

    @Test
    @WithUserDetails("admin")
    @DisplayName("POST city missing required fields")
    public void givenNoCities_whenPostNewCityWithoutRequiredFields_thenBadRequest() throws Exception {
        CityRequest request = new CityRequest();

        mockMvc
                .perform(
                        post(ServiceConst.CITY_API)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
        ;
    }
}