package com.example.pplite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*") // Allow all origins for now
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/available/{matchId}")
    public ResponseEntity<List<Ticket>> getAvailableTickets(@PathVariable Long matchId) {
        List<Ticket> tickets = ticketService.getAvailableTicketsByMatchId(matchId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveTicket(@RequestParam Long ticketId, @RequestParam Long utilisateurId) {
        boolean success = ticketService.reserveTicket(ticketId, utilisateurId);
        if (success) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    // Endpoint to create a ticket (for testing or admin purposes)
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.saveTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @GetMapping("/byMatch/{matchId}")
    public ResponseEntity<List<Ticket>> getTicketsByMatchId(@PathVariable Long matchId) {
        List<Ticket> tickets = ticketService.getTicketsByMatchId(matchId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
} 