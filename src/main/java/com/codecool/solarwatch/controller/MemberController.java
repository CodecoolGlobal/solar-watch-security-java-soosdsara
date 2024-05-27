package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.JwtResponse;
import com.codecool.solarwatch.model.dto.MemberDTO;
import com.codecool.solarwatch.security.jwt.JwtUtils;
import com.codecool.solarwatch.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public MemberController(MemberService memberService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody MemberDTO request) {
        memberService.createMember(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody MemberDTO loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    @PutMapping("/admin")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Void> addAdminRole() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberService.updateMember(userDetails.getUsername());
        return ResponseEntity.ok(null);
    }
}
