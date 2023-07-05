package com.codebyshatru.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.codebyshatru.orderservice.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebyshatru.orderservice.dto.OrderLineItemsDto;
import com.codebyshatru.orderservice.dto.OrderRequest;
import com.codebyshatru.orderservice.model.Order;
import com.codebyshatru.orderservice.model.OrderLineItems;
import com.codebyshatru.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	@Autowired
	private final OrderRepository orderRepository;

	private  final WebClient webClient;
	public void placeOrders(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();

		order.setOrderLineItemsList(orderLineItems);
		List<String> skuCode =  order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode)
				.toList();
		//call inventory service to check the stock and if available place the order
        InventoryResponse[] inventoryResponses = webClient.get()
				.uri("http://localhost/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCode).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		assert inventoryResponses != null;
		boolean allProductIsInStock =  Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
		if (allProductIsInStock) {
			orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("Product with sku code "+ Arrays.stream(inventoryResponses).filter(inventoryResponse -> !inventoryResponse.isInStock()).map(InventoryResponse::getSkuCode).toList()+" are not in stock, please try again later!");
		}

	}

	public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

		OrderLineItems orderLineItems = new OrderLineItems();

		orderLineItems.setId(orderLineItemsDto.getId());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		return orderLineItems;
	}
}
