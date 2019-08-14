package com.pasakinskas.homework.model;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ScienceJournal extends Book {

    @Size(min = 1, max = 10)
    private int scienceIndex;

    public ScienceJournal(String name, String author, String barcode,
                          BigDecimal pricePerUnit, int quantity, int releaseYear) {
        super(name, author, barcode, pricePerUnit, quantity);
        this.scienceIndex = releaseYear;
    }

    public ScienceJournal() {
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return getPricePerUnit().multiply(BigDecimal.valueOf(getQuantity() * getScienceIndex()));
    }

    public int getScienceIndex() {
        return scienceIndex;
    }

    public void setScienceIndex(int scienceIndex) {
        this.scienceIndex = scienceIndex;
    }
}
