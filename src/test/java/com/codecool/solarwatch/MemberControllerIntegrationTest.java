
package com.codecool.solarwatch;

import com.codecool.solarwatch.model.dto.MemberDTO;
import com.codecool.solarwatch.model.dto.SunTimeRequestDTO;
import com.codecool.solarwatch.model.entity.Member;
import com.codecool.solarwatch.service.SunTimeService;
import com.codecool.solarwatch.service.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SunTimeService sunTimeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testRegisterMember() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("testuser");
        memberDTO.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isCreated());

        Optional<Member> savedUserOptional = memberRepository.findMemberByUserName("testuser");
        assertTrue(savedUserOptional.isPresent());
    }

    @Test
    void authenticateUserShouldReturnOkStatus() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("testuser1");
        memberDTO.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void authenticateUserWithWrongCredentialsShouldReturnUnauthorizedStatus() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("testuser2");
        memberDTO.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isUnauthorized());
    }

/*    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void testGetSunriseSunset_CityFound() throws Exception {
        String cityName = "London";
        CityDTO cityDTO = new CityDTO(111, 111, "Country", "State", cityName);
        String dateStr = LocalDate.of(2020, 5, 30).toString();
        SunTimeRequestDTO sunTimeRequestDTO = new SunTimeRequestDTO("3:47:54 AM", "8:08:21 PM", dateStr, cityName);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sun-times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sunTimeRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sun-times/{cityName}/{dateStr}", cityName, dateStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunrise").value("3:47:54 AM"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunset").value("8:08:21 PM"));
    }


    @Test
    public void testGetSunriseSunset_CityNotFound() throws Exception {
        String cityName = "NeverLand";
        LocalDate date = LocalDate.now();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sun-times/{cityName}/{dateStr}", cityName, date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }*/

}
