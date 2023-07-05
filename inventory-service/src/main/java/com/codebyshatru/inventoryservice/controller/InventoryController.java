package com.codebyshatru.inventoryservice.controller;

import com.codebyshatru.inventoryservice.dto.CreateInventoryDto;
import com.codebyshatru.inventoryservice.dto.InventoryResponse;
import com.codebyshatru.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
      return  inventoryService.isInStock(skuCode);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  InventoryResponse createOneProduct(@RequestBody CreateInventoryDto createInventoryDto) throws Exception {
        return  inventoryService.save(createInventoryDto);
    }
}
