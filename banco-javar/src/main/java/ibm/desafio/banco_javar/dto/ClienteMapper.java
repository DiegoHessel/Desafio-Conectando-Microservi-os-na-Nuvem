package ibm.desafio.banco_javar.dto;

import ibm.desafio.banco_javar.entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteListagemDTO toDTO(ClienteListagemDTO cliente) {
        if (cliente == null) {
            return null;
        } else {
            ClienteListagemDTO dto = new ClienteListagemDTO();
            dto.setId(cliente.getId());
            dto.setNome(cliente.getNome());
            dto.setTelefone(cliente.getTelefone());
            dto.setCorrentista(cliente.getCorrentista());
            dto.setSaldo(cliente.getSaldo());
            return dto;
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
            entity.setTelefone(dto.getTelefone());
            entity.setCorrentista(dto.getCorrentista());
            entity.setSaldo(dto.getSaldo());
            return entity;
        }
    }
}