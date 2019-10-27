package vectorcalculator.provider.parser;

import vectorcalculator.vectors.IVector;
import vectorcalculator.vectors.VectorRepository;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataParser implements IDataParser {

    @Override
    public VectorRepository parse(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);

        VectorRepository repository = new VectorRepository();

        try {
            while (true) {
                IVector vector = (IVector) ois.readObject();
                if (vector != null)
                    repository.add(vector);
                else
                    break;
            }
        } catch (EOFException e) {
            // end of file
        }

        ois.close();
        return repository;
    }
}
