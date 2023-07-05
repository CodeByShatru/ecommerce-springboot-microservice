package com.codebyshatru.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            try {
//                Inventory inventory = new Inventory();
//                inventory.setSkuCode("iPhone_13");
//                inventory.setQuantity(100);
//                inventory.setCreatedDt(LocalDateTime.now());
//
//                Inventory inventory1 = new Inventory();
//                inventory1.setSkuCode("iPhone_13_red");
//                inventory1.setQuantity(0);
//                inventory1.setCreatedDt(LocalDateTime.now());
//                inventoryRepository.save(inventory);
//                inventoryRepository.save(inventory1);
//
//            } catch (ConstraintViolationException exception) {
//                throw new Exception("Field 'skuCode' must be unique.");
//            }
//        };
//    }

}
