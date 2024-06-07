package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.dto.MemberDTO;
import com.codecool.solarwatch.model.entity.Member;
import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.service.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMember() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("testUser");
        memberDTO.setPassword("testPassword");

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        memberService.createMember(memberDTO);

        verify(passwordEncoder, times(1)).encode("testPassword");
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void testUpdateMember() {
        Member existingMember = new Member();
        existingMember.setUserName("existingUser");
        existingMember.setRoles(Set.of(Role.ROLE_MEMBER));

        when(memberRepository.findMemberByUserName("existingUser")).thenReturn(Optional.of(existingMember));

        memberService.updateMember("existingUser");

        verify(memberRepository, times(1)).findMemberByUserName("existingUser");
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void testUpdateMember_UserNotFound() {
        when(memberRepository.findMemberByUserName("nonExistingUser")).thenReturn(Optional.empty());

        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            memberService.updateMember("nonExistingUser");
        });

        org.junit.jupiter.api.Assertions.assertEquals("user nonExistingUser not found", exception.getMessage());

        verify(memberRepository, never()).save(any(Member.class));
    }
}
