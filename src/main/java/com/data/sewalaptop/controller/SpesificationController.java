package com.data.sewalaptop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.data.sewalaptop.dto.Spesificationdto;
import com.data.sewalaptop.model.Spesification;
import com.data.sewalaptop.repository.Spesificationrepository;
import com.data.sewalaptop.service.Response;
import com.data.sewalaptop.service.Spesificationservice;

@RestController
@RequestMapping("spesification")
public class SpesificationController {
    @Autowired Spesificationservice spek;
    @Autowired Spesificationrepository spesificationrepository;

    @GetMapping("/index")
    public ResponseEntity<Response> index() {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(spek.index())
            .message("data index")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(spek.index())
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
            }
        }
    
        @PostMapping("/save")
        public ResponseEntity<Response> insert(@RequestBody Spesificationdto body) {
            try {
                if (body.getBrand_id() == null || body.getBrand_id().equals(body)) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (body.getProcessor() == null || body.getProcessor().isEmpty()) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (body.getRam() == null || body.getRam().isEmpty()) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (body.getStorage() == null || body.getStorage().isEmpty()) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (body.getGraphic_card() == null || body.getGraphic_card().isEmpty()) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    
                Response resp = Response.builder()
                .code("200")
                .data(spek.insert(body))
                .message("data save")
                .build();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
                }
            } catch (Exception e) {
                Response resp = Response.builder()
                .code("400")
                .data(spek.insert(body))
                .message("Error")
                .build();
                return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
            }
        }
        
        @GetMapping("{id}")
        public ResponseEntity<Response> show(@PathVariable Long id) {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(spek.show(id))
            .message("get data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(spek.show(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> update(@RequestBody Spesificationdto body, @PathVariable Long id) {
        try {
            Optional<Spesification> spesificationentity = spesificationrepository.findById(id);
            if (spesificationentity.isPresent()) {
            Response resp = Response.builder()
            .code("200")
            .data(spek.update(id, body))
            .message("update data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } else {
            //jika data dari table tidak ditemukan maka error
            Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
            return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } catch (Exception e) {
        Response resp = Response.builder()
        .code("400")
        .data(spek.update(id, body))
        .message("Error")
        .build();
        return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(spek.delete(id))
            .message("delete data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(spek.delete(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }
}
