package com.pasakinskas.homework.storage;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 *  TODO: write a short explanation about this
 */

@Component
public class BookStorage {
    private static final String PATH = "./storage/books.txt";
    private DataModel model;

    public DataModel readData() {
        if (model != null) {
            return model;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            DataModel model = (DataModel) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            this.model = model;
            return model;

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return new DataModel();
        } catch (IOException e) {
            System.err.println("Error initializing stream");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Corrupt storage file");
            throw new RuntimeException(e);
        }
    }

    public void updateData(DataModel updatedModel) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(updatedModel);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error writing data to file");
            throw new RuntimeException(e);
        }

        this.model = updatedModel;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
}
