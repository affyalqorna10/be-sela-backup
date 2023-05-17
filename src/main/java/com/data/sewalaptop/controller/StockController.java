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

import com.data.sewalaptop.model.Stock;
import com.data.sewalaptop.service.Response;
import com.data.sewalaptop.service.Stockservice;

@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired Stockservice stk;

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
        public ResponseEntity<Response> insert(@RequestBody Stock body) {
            try {
                Response resp = Response.builder()
                .code("200")
                .data(stk.insert(body))
                .message("data save")
                .build();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
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
    public ResponseEntity<Response> update(@RequestBody Stock body, @PathVariable Long id) {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(stk.update(id, body))
            .message("update data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
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
