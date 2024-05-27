package com.codecool.solarwatch;

import com.codecool.solarwatch.exception.InvalidCityException;
import com.codecool.solarwatch.model.dto.MemberDTO;
import com.codecool.solarwatch.model.dto.SunTimeReportDTO;
import com.codecool.solarwatch.service.SunTimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SunTimeService sunTimeService;

    @Test
    public void testRegisterMember() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("testuser");
        memberDTO.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isCreated());
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

    @Test
    @WithMockUser(username = "test-user", roles = {"MEMBER"})
    public void testGetSunriseSunset() throws Exception {
        String cityName = "London";
        String dateStr = LocalDate.of(2020, 5, 30).toString();

        Mockito.when(sunTimeService.getSunTime("London", LocalDate.of(2020,5,30)))
                .thenReturn(new SunTimeReportDTO("3:47:54 AM", "8:08:21 PM"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sun-times/{cityName}/{dateStr}", cityName, dateStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunrise").value("3:47:54 AM"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunset").value("8:08:21 PM"));
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"MEMBER"})
    public void testGetSunriseSunset_CityNotFound() throws Exception {
        String cityName = "NeverLand";
        LocalDate date = LocalDate.now();

        Mockito.when(sunTimeService.getSunTime(cityName, date))
                .thenThrow(new InvalidCityException("NeverLand"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sun-times/{cityName}/{dateStr}",  cityName, date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

