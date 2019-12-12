package draw.service.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import draw.service.entity.EntityRepository;
import draw.service.entity.memento.IState;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Loader {
    private EntityRepository repository;

    public Loader(EntityRepository repository) {
        this.repository = repository;
    }

    public void load(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = contentBuilder.toString();

        Gson gson = GsonRegister.build();
        Type collectionType = new TypeToken<List<IState>>(){}.getType();
        List<IState> states = gson.fromJson(jsonString, collectionType);

        this.repository.loadFromStates(states);
    }

}
