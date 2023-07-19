package com.transaccion.transaccion.controller;

import com.transaccion.transaccion.dto.Mensaje;
import com.transaccion.transaccion.dto.RegistroDto;
import com.transaccion.transaccion.entity.Registro;
import com.transaccion.transaccion.service.RegistroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {
    @Autowired
    RegistroService registroService;
    //lista de registros de transaccion
    @GetMapping("/lista")
    public ResponseEntity<List<Registro>> list(){
        List<Registro> list = registroService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Registro> getById(@PathVariable("id") int id){
        if(!registroService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        Registro registro = registroService.getOne(id).get();
        return new ResponseEntity(registro, HttpStatus.OK);
    }

    @GetMapping("/detalle/{descripcion}")
    public ResponseEntity<Registro> getBydescripcion(@PathVariable("descripcion") String descripcion){
        if(!registroService.existsByDescripcion(descripcion))
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        Registro registro = registroService.getByDescripcion(descripcion).get();
        return new ResponseEntity(registro, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody RegistroDto registroDto){
        if(StringUtils.isBlank(registroDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es requerida"), HttpStatus.BAD_REQUEST);
        if(registroDto.getMonto()<0)
            return new ResponseEntity(new Mensaje("El monto es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(registroDto.getFecha()))
            return new ResponseEntity(new Mensaje("La fecha es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(registroDto.getEstado()))
            return new ResponseEntity(new Mensaje("La descripcion es requerida"), HttpStatus.BAD_REQUEST);
        Registro registro = new Registro(registroDto.getDescripcion(),registroDto.getMonto(),registroDto.getFecha(),registroDto.getEstado());
        registroService.save(registro);
        return new ResponseEntity(new Mensaje("El registro fue ingresado"), HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody RegistroDto registroDto){
        if(!registroService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(registroDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es requerida"), HttpStatus.BAD_REQUEST);
        if(registroDto.getMonto()<0)
            return new ResponseEntity(new Mensaje("El monto es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(registroDto.getFecha()))
            return new ResponseEntity(new Mensaje("La fecha es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(registroDto.getEstado()))
            return new ResponseEntity(new Mensaje("La descripcion es requerida"), HttpStatus.BAD_REQUEST);

        Registro registro = registroService.getOne(id).get();
        registro.setDescripcion(registroDto.getDescripcion());
        registro.setMonto(registroDto.getMonto());
        registro.setFecha(registroDto.getFecha());
        registro.setEstado(registroDto.getEstado());
        registroService.save(registro);
        return new ResponseEntity(new Mensaje("El registro fue actualizado"), HttpStatus.OK);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!registroService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        registroService.delete(id);
        return new ResponseEntity(new Mensaje("El registro fue eliminado"), HttpStatus.OK);
    }

}
