package com.pasakinskas.homework.model;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ScienceJournal extends Book {

    @Size(min = 1, max = 10)
    private int scienceIndex;

    public ScienceJournal(String name, String author, String barcode,
                          BigDecimal pricePerUnit, int quantity, int scienceIndex) {
        super(name, author, barcode, pricePerUnit, quantity);
        this.scienceIndex = scienceIndex;
    }

    public ScienceJournal() {
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return getPricePerUnit().multiply(BigDecimal.valueOf(getQuantity() * getScienceIndex()));
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", barcode='" + getBarcode() + '\'' +
                ", pricePerUnit=" + getPricePerUnit() +
                ", quantity=" + getQuantity() +
                "scienceIndex=" + scienceIndex +
                '}';
    }

    public int getScienceIndex() {
        return scienceIndex;
    }

    public void setScienceIndex(int scienceIndex) {
        this.scienceIndex = scienceIndex;
    }
}
