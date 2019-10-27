package vectorcalculator.provider.writer;

import vectorcalculator.vectors.VectorRepository;

import java.io.IOException;

public interface IDataWriter {
    void write(VectorRepository repository, String path) throws IOException;
}
