package com.pasakinskas.homework.Utilities;

import com.pasakinskas.homework.model.AntiqueBook;
import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.model.ScienceJournal;

/**
 * This static class is used to judge if the Book type
 * object is a base type or one of its extended classes -
 * an AntiqueBook or a ScienceJournal
 * <p>
 * This is required so that one controller could accept a
 * generic json and build a correct Book class.
 */

public class BookClassifier {

    public static boolean isBaseBook(Book book) {
        return book.getClass() == Book.class;
    }

    public static boolean isAntique(Book book) {
        return book.getClass() == AntiqueBook.class;
    }

    public static boolean isScienceJournal(Book book) {
        return book.getClass() == ScienceJournal.class;
    }

    public static Class<? extends Book> getBookSubclassByClassName(String className) throws NullPointerException {
        if (className.equals("book")) {
            return Book.class;
        } else if (className.equals("scienceJournal")) {
            return ScienceJournal.class;
        } else if (className.equals("antiqueBook")) {
            return AntiqueBook.class;
        } else {
            throw new NullPointerException();
        }
    }
}
