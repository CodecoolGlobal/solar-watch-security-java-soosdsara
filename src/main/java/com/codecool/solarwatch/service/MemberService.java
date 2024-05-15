package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.entity.Member;
import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.newMemberDTO;
import com.codecool.solarwatch.service.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    public synchronized void createMember(newMemberDTO request) {
        Member member = new Member();

        member.setUserName(request.getUsername());
        member.setPassword(encoder.encode(request.getPassword()));
        member.setRoles(Set.of(Role.ROLE_MEMBER));

        memberRepository.save(member);
    }
}
