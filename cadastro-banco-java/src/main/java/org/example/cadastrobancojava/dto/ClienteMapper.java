package org.example.cadastrobancojava.dto;

import org.example.cadastrobancojava.entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static DadosClienteListagemDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        } else {
            return new DadosClienteListagemDTO(cliente);
        }
    }

    public static List<DadosClienteListagemDTO> toDto(List<Cliente> entities) {
        return entities.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Cliente toEntity(ClienteCriacaoDTO dto) {
        if (dto == null) {
            return null;
        } else {
            return new Cliente(dto);
        }
    }
}