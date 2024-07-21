package ibm.desafio.banco_javar.dto;

import ibm.desafio.banco_javar.entity.Cliente;

import java.util.List;

public class ClienteMapper {

    public static ClienteConsultaDTO toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        } else {
            ClienteConsultaDTO dto = new ClienteConsultaDTO(cliente);
            dto.setId(cliente.getId());
            dto.setNome(cliente.getNome());
            dto.setTelefone(cliente.getTelefone());
            dto.setCorrentista(cliente.getCorrentista());
            dto.setScoreCredito(cliente.getScoreCredito());
            dto.setSaldo(cliente.getSaldo());
            return dto;
        }
    }

    public static List<ClienteConsultaDTO> toDto(List<Cliente> clientes) {
        if (clientes == null) {
            return null;
        } else {
            return clientes.stream().map(ClienteMapper::toDto).toList();
        }
    }
    public static Cliente toEntity(ClienteCriacaoDto dto) {
        if (dto == null) {
            return null;
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(dto.getNome());
            cliente.setTelefone(dto.getTelefone());
            cliente.setCorrentista(dto.getCorrentista());
            cliente.setSaldo(dto.getSaldo());
            return cliente;
        }
    }
}
