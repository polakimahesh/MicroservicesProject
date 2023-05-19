package com.customer.order.service.project.Order;

import com.customer.order.service.project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${customerService.base.url}")
    private  String customerBaseUrl;

    @Value("${productService.base.url}")
    private String productBaseUrl;
    @Value("${cartService.base.url}")
    private String cartBaseUrl;

    public List<Orders> getAllOrders(){
        return  orderRepository.findAll();
    }
    @Transactional
    public HashMap<String,Object> createOrder(OrderDto orderDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        CustomerDto customer = restTemplate.getForObject(customerBaseUrl+orderDto.getCustomerId(),CustomerDto.class);
        Orders orders = new Orders();
        ResponseEntity<CartItemDto[]> responseEntity = restTemplate.getForEntity(cartBaseUrl+"get-cart-items/"+orderDto.getCartId(), CartItemDto[].class);
        List<CartItemDto> object = Arrays.asList(responseEntity.getBody());

        for(CartItemDto cartItem:object){
            GetAvailabilityDto getAvailabilityDto = new GetAvailabilityDto();
            getAvailabilityDto.setProductId(cartItem.getProductId());
            getAvailabilityDto.setQuantity(cartItem.getItemQuantity());
            Boolean isProductAvailable = restTemplate.postForObject(productBaseUrl+"get-product-check",getAvailabilityDto, Boolean.class);
            if(Boolean.FALSE.equals(isProductAvailable)){
                response1.put("message",cartItem.getItemName()+" is not available, please wait for some time");
                response.put("isSuccess",false);
                response.put("message",response1);
                return  response;
            }
        }

        double grantTotal=0.0;
        if(customer == null){
            response1.put("message","Customer not found with id "+orderDto.getCustomerId());
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {

            for (CartItemDto cartItem : object) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(orders);
                orderItem.setItemName(cartItem.getItemName());
                orderItem.setItemPrice(cartItem.getItemPrice());
                orderItem.setItemQuantity(cartItem.getItemQuantity());
                orderItem.setDescription(cartItem.getDescription());
                orderItem.setTotalPrice(cartItem.getTotalPrice());
                orderItemRepository.save(orderItem);
                ProductDto product =restTemplate.getForObject(productBaseUrl+cartItem.getProductId(), ProductDto.class);

                GetAvailabilityDto getAvailabilityDto = new GetAvailabilityDto();
                getAvailabilityDto.setProductId(product.getId());
                getAvailabilityDto.setQuantity(product.getQuantity()-orderItem.getItemQuantity());
                restTemplate.postForObject(productBaseUrl+"update-product-quantity",getAvailabilityDto,GetAvailabilityDto.class);
                grantTotal += orderItem.getTotalPrice();
            }
            orders.setAddress(orderDto.getAddress());
            orders.setCreatedAt(orders.getCreatedAt());
            orders.setCustomerId(customer.getId());
            orders.setGrandTotal(grantTotal);
            orderRepository.save(orders);
            CartDeleteDto cartDeleteDto = new CartDeleteDto();
            cartDeleteDto.setCartId(orderDto.getCartId());
            restTemplate.postForEntity(cartBaseUrl+"delete-cart-id",cartDeleteDto,CartDeleteDto.class);
            response1.put("message","Created with id "+orders.getId());
            response.put("isSuccess",true);
            response.put("message",orders);
            return  response;
        }

    }

    @Transactional
    public HashMap<String,Object> getAllOrderItemsWithCustomerId(GetOrderDto getOrderDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        Orders orders =orderRepository.findById(getOrderDto.getOrderId()).orElse(null);
        if(orders==null){
            response1.put("message","order not found with id "+getOrderDto.getOrderId());
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            List<OrderItem> orderItems=orderItemRepository.findByOrders_Id(orders.getId());
            List<OrderItemResponseDto> orderItemRList =new ArrayList<>();
            double grandTotal=0.0;
            for(OrderItem orderItem:orderItems){
                OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
//                orderItemResponseDto.setOrderItemId(orderItem.getId());
                orderItemResponseDto.setItemName(orderItem.getItemName());
                orderItemResponseDto.setItemPrice(orderItem.getItemPrice());
                orderItemResponseDto.setItemQuantity(orderItem.getItemQuantity());
                orderItemResponseDto.setDescription(orderItem.getDescription());
                orderItemResponseDto.setTotalPrice(orderItem.getTotalPrice());
                orderItemRList.add(orderItemResponseDto);
                grandTotal+=orderItemResponseDto.getTotalPrice();
            }
            getOrderDto.setOrderId(orders.getId());
            getOrderDto.setOrderItems(orderItemRList);
            getOrderDto.setGrantTotal(grandTotal);
            response.put("isSuccess",true);
            response.put("message",getOrderDto);
            return response;
        }
    }
//    @Transactional
//    public HashMap<String,Object> getSingleOrderItems(GetSingleItemDto getSingleItemDto){
//        HashMap<String,Object> response = new HashMap<>();
//        HashMap<String,Object> response1= new HashMap<>();
////        Customer customer=customerRepository.findByCustomerIdAndOrdersIdAndOderItemId(getSingleItemDto.getCustomerId());
////        Orders orders =orderRepository.findById(getSingleItemDto.getOrderId()).orElse(null);
//        var orderItem=orderItemRepository.findByOrders_IdAndOrders_Customer_IdAndId(getSingleItemDto.getOrderId(),getSingleItemDto.getCustomerId(),getSingleItemDto.getOrderItemId());
//
//        if(orderItem==null){
//            response1.put("message", "order item not found with id " + getSingleItemDto.getOrderItemId());
//            response.put("isSuccess", false);
//            response.put("message", response1);
//            return response;
//        }else {
//            getSingleItemDto.setOrderItemId(orderItem.getId());
//            getSingleItemDto.setCustomerId(orderItem.getOrders().getCustomer().getId());
//            getSingleItemDto.setItemName(orderItem.getItemName());
//            getSingleItemDto.setItemPrice(orderItem.getItemPrice());
//            getSingleItemDto.setItemQuantity(orderItem.getItemQuantity());
//            getSingleItemDto.setDescription(orderItem.getDescription());
//            getSingleItemDto.setTotalPrice(orderItem.getTotalPrice());
//            response.put("isSuccess", true);
//            response.put("message", getSingleItemDto);
//            return response;
//        }
//    }
//
//    public  String itemQuantityCheck(GetQuantityDto getQuantityDto) {
//        Product product = productRepository.findById(getQuantityDto.getProductId()).orElse(null);
//        if(product==null){
//           return "product not found with id "+getQuantityDto.getProductId();
//        }
//        else{
//            if (product.getQuantity() == 0 || product.getQuantity() < getQuantityDto.getQuantity()) {
//                return "product quantity is low, we have only " + product.getQuantity() + ", products available";
//            }
//            else {
//                product.setQuantity(product.getQuantity()-getQuantityDto.getQuantity());
//                productRepository.save(product);
//                return  "Remaining quantity " +product.getQuantity();
//            }
//        }
//
//    }
}
