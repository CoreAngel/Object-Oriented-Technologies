package draw.service.provider;

import com.google.gson.Gson;
import draw.service.entity.EntityRepository;
import draw.service.entity.memento.IState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    private EntityRepository entityRepository;

    public Writer(EntityRepository repository) {
        this.entityRepository = repository;
    }

    public void writeToFile(String filename) throws IOException {
        List<IState> states = entityRepository.getStates();

        Gson gson = GsonRegister.build();
        String json = gson.toJson(states.toArray(new IState[0]));

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(json);
        writer.close();
    }

}
