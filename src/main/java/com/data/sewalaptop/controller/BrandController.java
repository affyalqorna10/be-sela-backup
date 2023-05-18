package com.data.sewalaptop.controller;

import java.util.Optional;

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

import com.data.sewalaptop.service.Response;
import com.data.sewalaptop.dto.Branddto;
import com.data.sewalaptop.model.Brand;
import com.data.sewalaptop.repository.Brandrepository;
import com.data.sewalaptop.service.Brandservice;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired Brandservice brand;
    @Autowired Brandrepository brandrepository;
    
    @GetMapping("/index")
    public ResponseEntity<Response> index() {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(brand.index())
            .message("data index")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.index())
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
            }
        }

    @PostMapping("/save")
    public ResponseEntity<Response> insert(@RequestBody Branddto body) {
        try {
            if (body.getBrand_name() == null || body.getBrand_name().isEmpty()) {
                Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (body.getIdmodel() == null || body.getIdmodel().isEmpty()) {
                Response rest = Response.builder().code("400").data(null).message("Product not Available").build();
                return new ResponseEntity<Response>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
            
            Response resp = Response.builder()
            .code("200")
            .data(brand.insert(body))
            .message("data save")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
            }
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.insert(body))
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
            .data(brand.show(id))
            .message("get data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.show(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> update(@RequestBody Branddto body, @PathVariable Long id) {
        try {
            Optional<Brand> brandentity = brandrepository.findById(id);
            if (brandentity.isPresent()) {
                Response resp = Response.builder()
                .code("200")
                .data(brand.update(id, body))
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
            .data(brand.update(id, body))
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
            .data(brand.delete(id))
            .message("delete data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.delete(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }    
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(brand.getById(id))
            .message("get data")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.getById(id))
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/All")
    public ResponseEntity<Response> getAll() {
        try {
            Response resp = Response.builder()
            .code("200")
            .data(brand.getAll())
            .message("data index")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            Response resp = Response.builder()
            .code("400")
            .data(brand.getAll())
            .message("Error")
            .build();
            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
            }
        }
}
