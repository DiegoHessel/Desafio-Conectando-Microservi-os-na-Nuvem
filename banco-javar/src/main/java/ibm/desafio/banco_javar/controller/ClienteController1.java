//package ibm.desafio.banco_javar.controller;
//
//import ibm.desafio.banco_javar.entity.Cliente;
//import ibm.desafio.banco_javar.exception.EntidadeNaoEncontradaException;
//import ibm.desafio.banco_javar.service.ClienteService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/clientes")
//@RequiredArgsConstructor
//public class ClienteController1 {
//
//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
////    private RestTemplate restTemplate;
//
//  //  private final String URL_SEGUNDA_APLICACAO = "http://localhost:8082/clientes/";
//
//    @PostMapping
//    public Cliente criarCliente(@RequestBody Cliente cliente) {
//        return clienteService.criarCliente(cliente);
//    }
//
//    @GetMapping("/{id}")
//    public Cliente buscarClientePorId(@PathVariable Long id) {
//        return clienteService.buscarClientePorId(id);
//    }
//
//    @PutMapping("/{id}")
//    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
//        return clienteService.atualizarCliente(id, cliente);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletarCliente(@PathVariable Long id) {
//        clienteService.deletarCliente(id);
//    }
//
//    @GetMapping("/{id}/calcularScoreCredito")
//    public Float calcularScoreCredito(@PathVariable Long id) {
//        Cliente cliente = restTemplate.getForObject(URL_SEGUNDA_APLICACAO + id, Cliente.class);
//        if (cliente != null) {
//            return cliente.getSaldo() * 0.1f;
//        } else {
//            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
//        }
//    }
//}