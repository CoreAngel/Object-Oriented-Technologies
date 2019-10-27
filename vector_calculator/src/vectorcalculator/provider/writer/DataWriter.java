package vectorcalculator.provider.writer;

import vectorcalculator.vectors.IVector;
import vectorcalculator.vectors.VectorRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataWriter implements IDataWriter {

    @Override
    public void write(VectorRepository repository, String path) throws IOException {
        ArrayList<IVector> vectors = repository.getList();

        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (IVector vector: vectors) {
            oos.writeObject(vector);
        }

        oos.close();
    }
}
