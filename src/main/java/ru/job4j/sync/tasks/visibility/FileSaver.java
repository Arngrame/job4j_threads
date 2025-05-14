package ru.job4j.sync.tasks.visibility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSaver {

    private final File file;

    public FileSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        if (content == null) {
            return;
        }

        try (OutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        }
    }
}
