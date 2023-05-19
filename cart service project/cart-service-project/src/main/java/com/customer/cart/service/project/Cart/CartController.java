package com.customer.cart.service.project.Cart;

import com.customer.cart.service.project.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //get all the carts
    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCarts(){
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }
    //create cart
    @PostMapping("/create-cart")
    public ResponseEntity<Object> createCart(@RequestBody CartDto cartDto){
        var cart = cartService.createCart(cartDto);
        if(Boolean.TRUE.equals(cart.get("isSuccess"))){
            return ResponseEntity.ok(cart.get("message"));
        }else
            return ResponseEntity.badRequest().body(cart.get("message"));
    }
    @GetMapping("/get-cart-items/{id}")
    public ResponseEntity<Object> getCartItemsWithCartId(@PathVariable Integer id){
        var cart =cartService.getAllCartItemsWithCartId(id);
       return  new ResponseEntity<>(cart,HttpStatus.OK);
    }
    //create cart items
    @PostMapping("/create-cart-items")
    public ResponseEntity<Object> createCartItem(@RequestBody CartItemDto cartItemDto){
        var cartItems=cartService.createCartItems(cartItemDto);
        if(Boolean.TRUE.equals(cartItems.get("isSuccess"))){
            return ResponseEntity.ok(cartItems.get("message"));
        }else
            return ResponseEntity.badRequest().body(cartItems.get("message"));

    }

    @PostMapping ("/get-all-cart-items")
    public ResponseEntity<Object> getAllCartItems(@RequestBody GetCartDto getCartDto){
        var cartItem =cartService.getAllCartItemsWithID(getCartDto);
        if(Boolean.TRUE.equals(cartItem.get("isSuccess"))){
            return ResponseEntity.ok(cartItem.get("message"));
        }else
            return ResponseEntity.badRequest().body(cartItem.get("message"));
    }
    @PostMapping("/update-cart-items")
    public ResponseEntity<Object> updateCartItems(@RequestBody CartUpdateItemDto cartUpdateItemDto){
        var cartItem=cartService.updateCartItems(cartUpdateItemDto);
        if(Boolean.TRUE.equals(cartItem.get("isSuccess"))){
            return ResponseEntity.ok(cartItem.get("message"));
        }else
            return ResponseEntity.badRequest().body(cartItem.get("message"));
    }
    @PostMapping("/delete-cart-id")
    public  ResponseEntity<Object> deleteCart(@RequestBody CartDeleteDto cartDeleteDto){
        return new ResponseEntity<>(cartService.deleteCartId(cartDeleteDto),HttpStatus.OK);
    }


}
