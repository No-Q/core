package com.noq.api.controller;

import com.noq.api.model.request.ItemAddRequest;
import com.noq.api.service.ItemService;
import com.noq.api.validator.item.ItemFilterValidator;
import com.noq.dependencies.db.dao.ItemDao;
import com.noq.dependencies.db.model.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@Api(value = "/items", description = "Manage Items" )
@RequestMapping(value = "/items")
@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;
    @Autowired
    ItemFilterValidator itemFilterValidator;
    @Autowired
    ItemDao itemDao;

    @ApiOperation(value = "get all active items")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllItems() {
        LOGGER.info("Received get all item request");
        return itemService.getAll();
    }

    @ApiOperation(value = "add item")
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addItem(@RequestBody ItemAddRequest request){
        LOGGER.info("Received item create request:"+request);
        try {
            itemService.save(request);
        } catch (Exception e) {
           LOGGER.error("Exception while saving item:",e);
        }
    }

    @ApiOperation(value="get items by filters")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getItem(
            @RequestParam(required = false, name = "restaurantId") Long restaurantId,
            @RequestParam(required = false, name = "mealType") String mealType,
            @RequestParam(required = false, name = "itemName") String itemName,
            @RequestParam(required = false, name = "itemType") String itemType) throws ValidationException {
        LOGGER.info("Received filter item request with filters::"+
        " restaurantId: "+restaurantId+" mealType"+mealType+" itemName:"+itemName+
        " itemType:"+itemType);
        if(itemFilterValidator.isValid(restaurantId,mealType,itemType)){
            return itemService.getItems(restaurantId,mealType,itemName,itemType);
        }else {
            throw new ValidationException("Invalid filter value");
        }
    }
/*
    @RequestMapping(value = "/items/", params = { "itemName", "itemType" })
    public Iterable<Item> find(
            @And({
                    @Spec(path = "itemName", spec = Like.class),
                    @Spec(path = "itemType", spec = Like.class)}) Specification<Item> spec) {

        return itemDao.findAll(spec);
    }
    */
}
