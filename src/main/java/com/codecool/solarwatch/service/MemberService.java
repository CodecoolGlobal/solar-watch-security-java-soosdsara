package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.entity.Member;
import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.MemberDTO;
import com.codecool.solarwatch.service.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void createMember(MemberDTO request) {
        Member member = new Member();

        member.setUserName(request.getUsername());
        member.setPassword(encoder.encode(request.getPassword()));
        member.setRoles(Set.of(Role.ROLE_MEMBER));

        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(String userName) {
        Member member = memberRepository.findMemberByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException(format("user %s not found", userName)));

        Set<Role> newRoles = new HashSet<>(member.getRoles());
        newRoles.add(Role.ROLE_ADMIN);
        member.setRoles(newRoles);

        memberRepository.save(member);
    }
}
