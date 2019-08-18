package com.pasakinskas.homework.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasakinskas.homework.Utilities.BookClassifier;
import com.pasakinskas.homework.storage.BookStorage;
import com.pasakinskas.homework.storage.DataModel;
import com.pasakinskas.homework.model.AntiqueBook;
import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.model.ScienceJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BookService {

    private final BookStorage storage;

    @Autowired
    public BookService(BookStorage storage) {
        this.storage = storage;
    }

    public List<Book> getAll() {
        DataModel model = storage.readData();
        List<Book> allBooks = new ArrayList<>();

        allBooks.addAll(model.getBooks());
        allBooks.addAll(model.getAntiqueBooks());
        allBooks.addAll(model.getScienceJournals());

        return allBooks;
    }

    public List<AntiqueBook> getAllAntiqueBooks() {
        return storage.readData().getAntiqueBooks();
    }

    public List<ScienceJournal> getAllScienceJournals() {
        return storage.readData().getScienceJournals();
    }

    public Book getBookByBarcode(String barcode) {
        return getAll()
                .stream()
                .filter(book -> book.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }

    public void deleteBookByBarcode(String barcode) throws NullPointerException {
        DataModel model = storage.readData();
        Book book = getBookByBarcode(barcode);

        if (book == null) {
            throw new NullPointerException();
        }
        else if (BookClassifier.isBaseBook(book)) {
            model.removeBook(book);
        } else if (BookClassifier.isAntique(book)) {
            AntiqueBook antiqueBook = (AntiqueBook) book;
            model.removeAntiqueBook(antiqueBook);
        } else if (BookClassifier.isScienceJournal(book)) {
            ScienceJournal journal = (ScienceJournal) book;
            model.removeScienceJournal(journal);
        }

        storage.updateData(model);
    }

    public void addNew(Book book) {
        DataModel model = storage.readData();
        Class type = book.getClass();

        if (type == ScienceJournal.class) {
            ScienceJournal journal = (ScienceJournal) book;
            model.addScienceJournal(journal);
        } else if (type == AntiqueBook.class) {
            AntiqueBook antiqueBook = (AntiqueBook) book;
            model.addAntiqueBook(antiqueBook);
        } else {
            model.addBook(book);
        }

        storage.updateData(model);
    }

    public boolean barcodeNotTaken(String barcode) {
        return getBookByBarcode(barcode) == null;
    }

    public void replaceByBarcode(Book book) {
        deleteBookByBarcode(book.getBarcode());
        addNew(book);
    }

    public Book buildFromJson(JsonNode json, String className) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.treeToValue(json, BookClassifier.getBookSubclassByClassName(className));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createMockData() {
        List<Book> books = new ArrayList<>(Arrays.asList(
                new Book("firstie", "Ptolemy", "15155", BigDecimal.valueOf(45.45), 18),
                new Book("Tom Sawyer", "Mark Twain", "52", BigDecimal.valueOf(45.45), 455),
                new Book("20000 Leagues", "Jules Werne", "5", BigDecimal.valueOf(45.45), 10),
                new Book("Cooking with Food", "Berry White", "545", BigDecimal.valueOf(20.45), 15)
        ));

        List<ScienceJournal> journals = new ArrayList<>(Arrays.asList(
                new ScienceJournal("Nature Inc", "Biologist", "82", BigDecimal.valueOf(44.15), 10, 5),
                new ScienceJournal("National Geo", "Scientist", "8", BigDecimal.valueOf(14.12), 11, 5),
                new ScienceJournal("Survival Magazine", "Bear Grills", "42", BigDecimal.valueOf(18.12), 20, 1)
        ));

        List<AntiqueBook> antiqueBooks = new ArrayList<>(Arrays.asList(
                new AntiqueBook("The Bible", "Various", "11", BigDecimal.valueOf(451.12), 2, 1785),
                new AntiqueBook("The Prince", "Machiavelli", "12", BigDecimal.valueOf(315.12), 1, 1885),
                new AntiqueBook("Las Miserables", "Victor Hugo", "13", BigDecimal.valueOf(281.12), 1, 1862)
        ));

        DataModel data = new DataModel(books, antiqueBooks, journals);
        storage.updateData(data);
    }
}
