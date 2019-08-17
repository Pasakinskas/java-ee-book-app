package com.pasakinskas.homework.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String author;
    private String barcode;
    private BigDecimal pricePerUnit;
    private int quantity;

    public Book(String name, String author, String barcode, BigDecimal pricePerUnit, int quantity) {
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", barcode='" + barcode + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                '}';
    }

    public BigDecimal calculateTotalPrice() {
        return getPricePerUnit().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
