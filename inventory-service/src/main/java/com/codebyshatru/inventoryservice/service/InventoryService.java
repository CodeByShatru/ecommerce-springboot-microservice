package com.codebyshatru.inventoryservice.service;

import com.codebyshatru.inventoryservice.dto.CreateInventoryDto;
import com.codebyshatru.inventoryservice.dto.InventoryResponse;
import com.codebyshatru.inventoryservice.entity.Inventory;
import com.codebyshatru.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Autowired
    private final InventoryRepository inventoryRepository;
    ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        try {
            return  inventoryRepository.findBySkuCodeIn(skuCode)
                    .stream().map(inventory ->
                            InventoryResponse.builder().skuCode(inventory.getSkuCode())
                                    .isInStock(inventory.getQuantity() > 0)
                                    .build()
                    ).toList();
        }catch (Exception exception){
            System.out.println("Exception in isInStock method "+exception.getMessage());
            throw  new RuntimeException(exception);
        }

    }

    public InventoryResponse save(CreateInventoryDto createInventoryDto) {
        Inventory inventory = new Inventory();
        try {
            inventory.setSkuCode(createInventoryDto.getSkuCode());
            inventory.setQuantity(createInventoryDto.getQuantity());
            inventory.setCreatedDt(LocalDateTime.now());
            Inventory inventoryData = inventoryRepository.save(inventory);
            return modelMapper.map(inventoryData, InventoryResponse.class);
        }  catch (ConstraintViolationException ex) {
        throw new RuntimeException(ex.getConstraintViolations().toString());
    }
    }
}
