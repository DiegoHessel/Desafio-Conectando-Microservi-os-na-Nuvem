package ibm.desafio.banco_javar.dto;

import ibm.desafio.banco_javar.entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteListagemDTO toDTO(ClienteListagemDTO cliente) {
        if (cliente == null) {
            return null;
        } else {
            return new ClienteListagemDTO(cliente.getId(), cliente.getNome(), cliente.getSaldo());
        }
    }

    public static List<ClienteListagemDTO> toDto(List<ClienteListagemDTO> entities) {
        return entities.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Cliente toEntity(ClienteCriacaoDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Cliente entity = new Cliente();
            entity.setNome(dto.getNome());
            entity.setSaldo(dto.getSaldo());
            return entity;
        }
    }
}