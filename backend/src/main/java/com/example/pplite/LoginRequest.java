package com.example.pplite;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String email;
    @JsonProperty("mot_de_passe")
    private String motDePasse;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
} 