package org.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHandler {

    private Charset charset = StandardCharsets.UTF_8;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
    public boolean mergeFiles (List<Path> fileList, String outputName) {
        boolean ok = true;
        Path outputPath = Paths.get(outputName);

        for (Path file: fileList) {
            try {
                List<String> lines = Files.readAllLines(file, charset);
                Files.write(outputPath, lines, charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error reading file " + file);
                e.printStackTrace();
                ok = false;
            }
        }
        return ok;
    }
}
