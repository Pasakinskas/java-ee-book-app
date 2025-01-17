package com.pasakinskas.homework.storage;

import com.pasakinskas.homework.model.AntiqueBook;
import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.model.ScienceJournal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements Serializable {

    private static final long serialVersionUID = 2L;
    private List<Book> books;
    private List<AntiqueBook> antiqueBooks;
    private List<ScienceJournal> scienceJournals;

    public DataModel(List<Book> books,
                     List<AntiqueBook> antiqueBooks, List<ScienceJournal> scienceJournals) {
        this.books = books;
        this.antiqueBooks = antiqueBooks;
        this.scienceJournals = scienceJournals;
    }

    public DataModel() {
        books = new ArrayList<>();
        antiqueBooks = new ArrayList<>();
        scienceJournals = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<AntiqueBook> getAntiqueBooks() {
        return antiqueBooks;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addScienceJournal(ScienceJournal journal) {
        scienceJournals.add(journal);
    }

    public void addAntiqueBook(AntiqueBook book) {
        antiqueBooks.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeScienceJournal(ScienceJournal journal) {
        scienceJournals.remove(journal);
    }

    public void removeAntiqueBook(AntiqueBook book) {
        antiqueBooks.remove(book);
    }

    public void setAntiqueBooks(List<AntiqueBook> antiqueBooks) {
        this.antiqueBooks = antiqueBooks;
    }

    public List<ScienceJournal> getScienceJournals() {
        return scienceJournals;
    }

    public void setScienceJournals(List<ScienceJournal> scienceJournals) {
        this.scienceJournals = scienceJournals;
    }
}
