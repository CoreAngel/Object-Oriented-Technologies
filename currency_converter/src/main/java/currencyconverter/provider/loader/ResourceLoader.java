package currencyconverter.provider.loader;

import java.io.*;
import java.net.*;

public abstract class ResourceLoader {

    public static byte[] getResource(String url) throws IOException {

        BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, bufferSize)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }

        return outputStream.toByteArray();
    }
}
