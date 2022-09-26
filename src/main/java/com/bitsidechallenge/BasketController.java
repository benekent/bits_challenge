package com.bitsidechallenge;

import com.bitsidechallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class BasketController {

    ProductService service;
    HashMap<String, Integer> basket;

    @Autowired
    public BasketController(ProductService service) {
        this.service = service;

        basket = new HashMap<>();
    }

    @ShellMethod(value = "Returns price of item.", group = "Basket")
    public BigDecimal price (String code){
        return service.findPriceByCode(code);
    }

    @ShellMethod(value = "Adds product to basket.", group = "Basket")
    public void scan (String code){
        basket.merge(code, 1, Integer::sum);
    }

    @ShellMethod(value = "Adds product to basket.", group = "Basket")
    public BigDecimal total (){
        return calculateTotal().setScale(2, RoundingMode.HALF_DOWN);
    }
    @ShellMethod(value = "Adds product to basket.", group = "Basket")
    public Map<String, Integer> list (){
        return basket;
    }


    private BigDecimal calculateTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<String, Integer> set :basket.entrySet()){
            total = total.add(calculateDiscountedPrice(set.getKey(), set.getValue()));
        }
        return total;
    }

    private BigDecimal calculateDiscountedPrice(String code, Integer amount){
        final BigDecimal pricePerItem = price(code);
        BigDecimal price = pricePerItem.multiply(getPayableAmount(amount));
        if (code.equals("A0001")){
            price = price.multiply(BigDecimal.valueOf(0.9));
        }

        return price;
    }

    private BigDecimal getPayableAmount(Integer totalAmount){
        return BigDecimal.valueOf(totalAmount-Math.floor(totalAmount/3.0));
    }

}
