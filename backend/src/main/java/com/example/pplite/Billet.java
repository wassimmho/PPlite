package com.example.pplite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BILLET", uniqueConstraints = @UniqueConstraint(columnNames = {"match_id", "siege_id"}))
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billet_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private User utilisateur;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchFoot match;

    @ManyToOne
    @JoinColumn(name = "siege_id", nullable = false)
    private Siege siege;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "statut")
    private String statut; // 'disponible', 'vendu', 'annulé'

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUtilisateur() { return utilisateur; }
    public void setUtilisateur(User utilisateur) { this.utilisateur = utilisateur; }

    public MatchFoot getMatch() { return match; }
    public void setMatch(MatchFoot match) { this.match = match; }

    public Siege getSiege() { return siege; }
    public void setSiege(Siege siege) { this.siege = siege; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
} 