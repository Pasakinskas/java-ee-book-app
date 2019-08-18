package com.pasakinskas.homework.Utilities;

import com.pasakinskas.homework.model.AntiqueBook;
import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.model.ScienceJournal;
public class BookClassifier {

    /**
     *  TODO: write a short explanation about this
     */

    public static boolean isBaseBook(Book book) {
        return book.getClass() == Book.class;
    }

    public static boolean isAntique(Book book) {
        return book.getClass() == AntiqueBook.class;
    }

    public static boolean isScienceJournal(Book book) {
        return book.getClass() == ScienceJournal.class;
    }

    public static Class<? extends Book> getBookSubclassByClassName(String className) {
        if (className == null || className.equals("book")) {
            return Book.class;
        } else if (className.equals("scienceJournal")) {
            return ScienceJournal.class;
        } else if (className.equals("antiqueBook")) {
            return AntiqueBook.class;
        }

        return null;
    }
}
