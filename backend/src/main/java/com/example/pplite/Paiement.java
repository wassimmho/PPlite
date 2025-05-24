package com.example.pplite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAIEMENT")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "billet_id", nullable = false)
    private Billet billet;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private User utilisateur;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "methode_paiement")
    private String methodePaiement;

    @Column(name = "statut")
    private String statut; // 'valide', 'rejeté', 'en_attente'

    @Column(name = "date_paiement")
    private LocalDateTime datePaiement;

    @Column(name = "reference_transaction", unique = true)
    private String referenceTransaction;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Billet getBillet() { return billet; }
    public void setBillet(Billet billet) { this.billet = billet; }

    public User getUtilisateur() { return utilisateur; }
    public void setUtilisateur(User utilisateur) { this.utilisateur = utilisateur; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public String getMethodePaiement() { return methodePaiement; }
    public void setMethodePaiement(String methodePaiement) { this.methodePaiement = methodePaiement; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDateTime datePaiement) { this.datePaiement = datePaiement; }

    public String getReferenceTransaction() { return referenceTransaction; }
    public void setReferenceTransaction(String referenceTransaction) { this.referenceTransaction = referenceTransaction; }
} 