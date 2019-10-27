package vectorcalculator.provider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DataLoader {

    public static byte[] load(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

}
