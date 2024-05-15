package com.codecool.solarwatch.service.repository;

import com.codecool.solarwatch.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
