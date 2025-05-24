package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getAvailableTicketsByMatchId(Long matchId) {
        return ticketRepository.findAvailableTicketsByMatchId(matchId);
    }

    @Transactional
    public boolean reserveTicket(Long ticketId, Long utilisateurId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        Optional<User> userOptional = userRepository.findById(utilisateurId);

        if (ticketOptional.isPresent() && userOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            if ("DISPONIBLE".equals(ticket.getStatut())) {
                ticket.setStatut("RESERVE");
                ticket.setUser(userOptional.get());
                ticketRepository.save(ticket);
                return true;
            }
        }
        return false;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
     public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public List<Ticket> getTicketsByMatchId(Long matchId) {
        return ticketRepository.findByMatchId(matchId);
    }
} 