package dictionary.loader;

import dictionary.collection.Collection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
    private String path;

    public Loader(String path) {
        this.path = path;
    }

    public void loadTo(Collection collection) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.path));
            String line = "";
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    line = line.trim();
                    collection.addWord(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Dictionary available for download on https://sjp.pl/slownik/growy/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
