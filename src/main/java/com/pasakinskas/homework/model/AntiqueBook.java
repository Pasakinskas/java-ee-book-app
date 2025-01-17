package com.pasakinskas.homework.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Calendar;

public class AntiqueBook extends Book {
    @Min(1454)
    @Max(1900)
    private int releaseYear;

    public AntiqueBook(String name, String author, String barcode,
                       BigDecimal pricePerUnit, int quantity, int releaseYear) {
        super(name, author, barcode, pricePerUnit, quantity);
        this.releaseYear = releaseYear;
    }

    public AntiqueBook() {
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        int yearsSinceRelease = Calendar.getInstance().get(Calendar.YEAR) - getReleaseYear();
        return getPricePerUnit().multiply(BigDecimal.valueOf(getQuantity() * yearsSinceRelease / 10));
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", barcode='" + getBarcode() + '\'' +
                ", pricePerUnit=" + getPricePerUnit() +
                ", quantity=" + getQuantity() +
                "releaseYear=" + releaseYear +
                '}';
    }
}
