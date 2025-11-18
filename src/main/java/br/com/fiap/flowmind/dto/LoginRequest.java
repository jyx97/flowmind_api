package br.com.fiap.flowmind.dto;

public record LoginRequest(
        String email,
        String senha
) {}
