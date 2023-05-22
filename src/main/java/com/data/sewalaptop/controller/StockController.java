package com.data.sewalaptop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.data.sewalaptop.dto.Stockdto;
import com.data.sewalaptop.model.Stock;
import com.data.sewalaptop.repository.Stockrepository;
import com.data.sewalaptop.service.Response;
import com.data.sewalaptop.service.Stockservice;

@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired Stockservice stk;
    @Autowired Stockrepository stockrepository;

    @GetMapping("/index")
    public ResponseEntity<Response> index() {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(stk.index())
            .message("data index")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(stk.index())
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
            }
        }
        @PostMapping("/save")
        public ResponseEntity<Response> insert(@RequestBody Stockdto body) {
            try {
                if (body.getBrand_id() == null || body.getBrand_id().equals(body)) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (body.getStock() == null || body.getStock().equals(body)) {
                    Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                Response resp = Response.builder()
                .code("200")
                .data(stk.insert(body))
                .message("data save")
                .build();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
                }
            } catch (Exception e) {
                Response resp = Response.builder()
                .code("400")
                .data(stk.insert(body))
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
            .data(stk.show(id))
            .message("get data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(stk.show(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Response> update(@RequestBody Stockdto body, @PathVariable Long id) {
        try {
            Optional<Stock> stockentity = stockrepository.findById(id);
            if (stockentity.isPresent()) {
            Response resp = Response.builder()
            .code("200")
            .data(stk.update(id, body))
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
            .data(stk.update(id, body))
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
            .data(stk.delete(id))
            .message("delete data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(stk.delete(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }
}
