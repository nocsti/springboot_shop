package com.tfotu.shop.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BuyGoodsDto {
    private Integer id;
    private String name;
    private String thumb;
    private BigDecimal price;
    private BigDecimal total;
    private Integer stock;
    private Integer number;
    private Boolean isCartItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        if( number == null )
            number = 0;
        this.number = number;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getCartItem() {
        return isCartItem;
    }

    public void setCartItem(Boolean cartItem) {
        isCartItem = cartItem;
    }

    @Override
    public String toString() {
        return "BuyGoodsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumb='" + thumb + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", stock=" + stock +
                ", number=" + number +
                ", isCartItem=" + isCartItem +
                '}';
    }
}
