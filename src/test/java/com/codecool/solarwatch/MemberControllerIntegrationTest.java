package com.codecool.solarwatch;

import com.codecool.solarwatch.model.dto.MemberDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
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
    @WithMockUser
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
}

