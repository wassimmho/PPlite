package com.example.pplite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchFootRepository extends JpaRepository<MatchFoot, Long> {
} 