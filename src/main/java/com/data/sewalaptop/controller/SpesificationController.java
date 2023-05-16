package com.data.sewalaptop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.sewalaptop.model.Spesification;
import com.data.sewalaptop.service.Response;
import com.data.sewalaptop.service.Spesificationservice;

@RestController
@RequestMapping("spesification")
public class SpesificationController {
    @Autowired Spesificationservice spek;

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
        public ResponseEntity<Response> insert(@RequestBody Spesification body) {
            try {
                Response resp = Response.builder()
                .code("200")
                .data(spek.insert(body))
                .message("data save")
                .build();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
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
    public ResponseEntity<Response> update(@RequestBody Spesification body, @PathVariable Long id) {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(spek.update(id, body))
            .message("update data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
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
