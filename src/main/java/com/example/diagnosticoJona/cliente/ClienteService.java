package com.example.diagnosticoJona.cliente;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Utilizo modelMapper para hacer el mapeo de DTO a entity de cliente y viceversa
    private final ModelMapper modelMapper=new ModelMapper();

    @Transactional(readOnly = true)
    public List<ClienteDTO> todosClientes(){
        List<Cliente> clientes=clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteDTO crearCliente(ClienteDTO cliente){
        Cliente clienteNuevo= new Cliente();
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setApellidos(cliente.getApellidos());
        clienteNuevo.setCurp(cliente.getCurp());
        clienteNuevo.setFechaNacimiento(cliente.getFechaNacimiento());
        return modelMapper.map(clienteRepository.save(clienteNuevo), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO modificarCliente(Long idCliente,ClienteDTO clienteDTO){
        Cliente clienteB=clienteRepository.findById(idCliente)
                .orElseThrow(()-> new EntityNotFoundException("Cliente no encontrado"));
        clienteB.setNombre(clienteDTO.getNombre());
        clienteB.setApellidos(clienteDTO.getApellidos());
        clienteB.setCurp(clienteDTO.getCurp());
        clienteB.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        Cliente clienteGuardado=clienteRepository.save(clienteB);
        return modelMapper.map(clienteGuardado, ClienteDTO.class);
    }

    @Transactional
    public String eliminarCliente(Long id){
        Cliente cliente=clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cliente no encontrado"));
        clienteRepository.deleteById(id);
        return "Cliente eliminado correctamente";
    }
}
