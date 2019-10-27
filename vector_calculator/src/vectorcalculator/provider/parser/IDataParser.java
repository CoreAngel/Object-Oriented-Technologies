package vectorcalculator.provider.parser;

import vectorcalculator.vectors.VectorRepository;

import java.io.IOException;

public interface IDataParser {
    VectorRepository parse(byte[] data) throws IOException, ClassNotFoundException;
}
