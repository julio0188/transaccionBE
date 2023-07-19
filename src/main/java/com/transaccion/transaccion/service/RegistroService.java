package com.transaccion.transaccion.service;

import com.transaccion.transaccion.entity.Registro;
import com.transaccion.transaccion.repository.RegistroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;
    //obtener la lista de registros en transacciones
    public List<Registro> list(){
        return registroRepository.findAll();
    }
    //obtener un registro por ID
    public Optional<Registro> getOne(int id){

        return registroRepository.findById(id);
    }
    //obtener un registro por descripcion
    public Optional<Registro> getByDescripcion(String descripcion){

        return registroRepository.findByDescripcion(descripcion);
    }
    //guardar un registro en transacciones
    public void save(Registro registro){
        registroRepository.save(registro);
    }

    //Borrar un registro por ID
    public void delete(int id){
        registroRepository.deleteById(id);
    }

    //validar que exista por ID
    public boolean existsById(int id){
        return registroRepository.existsById(id);
    }
    //validar que exista por descripcion
    public boolean existsByDescripcion(String descripcion){
        return registroRepository.existsByDescripcion(descripcion);
    }
}
