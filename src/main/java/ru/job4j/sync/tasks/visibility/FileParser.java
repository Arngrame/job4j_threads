package ru.job4j.sync.tasks.visibility;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class FileParser {

    private final File file;

    public FileParser(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Integer> predicate) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                if (predicate.test(data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public String getContent() throws IOException {
        return getContent((data) -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent((data) -> data < 0x80);
    }
}