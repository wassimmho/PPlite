package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final TicketService ticketService;

    @Autowired
    public MatchService(MatchRepository matchRepository, TicketService ticketService) {
        this.matchRepository = matchRepository;
        this.ticketService = ticketService;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    @Transactional
    public Match saveMatch(Match match) {
        Match savedMatch = matchRepository.save(match);
        if (match.getCapacite() != null && match.getCapacite() > 0) {
            List<Ticket> ticketsToSave = new ArrayList<>();
            for (int i = 1; i <= match.getCapacite(); i++) {
                Ticket newTicket = new Ticket(savedMatch, "Place #" + i, 50.00);
                ticketsToSave.add(newTicket);
            }
            ticketsToSave.forEach(ticketService::saveTicket);
        }
        return savedMatch;
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
} 