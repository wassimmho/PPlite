package com.example.pplite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.match.id = :matchId AND t.statut = 'DISPONIBLE'")
    List<Ticket> findAvailableTicketsByMatchId(@Param("matchId") Long matchId);

    List<Ticket> findByMatchId(Long matchId);
} 