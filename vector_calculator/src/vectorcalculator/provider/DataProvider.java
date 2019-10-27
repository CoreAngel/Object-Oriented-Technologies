package vectorcalculator.provider;

import vectorcalculator.provider.parser.IDataParser;
import vectorcalculator.provider.writer.IDataWriter;
import vectorcalculator.vectors.VectorRepository;

import java.io.IOException;

public abstract class DataProvider {
    private static String path = "./vectorsData";

    public static VectorRepository load(IDataParser parser) throws IOException, ClassNotFoundException {
        byte[] resource = DataLoader.load(DataProvider.path);
        return parser.parse(resource);
    }
    public static void save(VectorRepository repository, IDataWriter writer) throws IOException {
        writer.write(repository, DataProvider.path);
    }
}
