package com.noq.api.controller;

import com.noq.api.model.request.CartAddRequest;
import com.noq.api.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(value = "/carts", description = "Manages carts" )
@RequestMapping(value = "/carts")
@Controller
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartService cartService;

    @ApiOperation(value = "get cart details")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCart(@PathVariable("id") Long id) {

        LOGGER.info("Received request for get cart; id:"+id);
        return cartService.getCartDetails(id);
    }

    @ApiOperation(value = "add new cart")
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public String addCart(@RequestBody CartAddRequest request){
        // return the cart after adding
        LOGGER.info("Received request for adding a cart:"+request);
        return cartService.addCart(request);
    }

    @ApiOperation(value = "update cart details")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCart(@RequestBody CartAddRequest request){
        // return the cart after adding
        LOGGER.info("Received request for adding a cart:"+request);
        return cartService.updateCart(request);
    }
}
