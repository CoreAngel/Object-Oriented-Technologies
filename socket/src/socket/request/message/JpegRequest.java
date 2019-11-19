package socket.request.message;

import socket.request.EncodedRequest;
import socket.request.Request;
import socket.request.RequestType;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;

public class JpegRequest extends Request {
    private File file = new File("./ss.jpg");
    private BufferedImage image;


    public JpegRequest() {
        super(RequestType.JPEG);
    }

    public JpegRequest(BufferedImage image) {
        super(RequestType.JPEG);
        this.image = image;
    }

    @Override
    public Object handle(EncodedRequest request) {
        byte[] payload = request.getPayload();

        InputStream input = new ByteArrayInputStream(payload);
        try {
            BufferedImage bufferedImage = ImageIO.read(input);
            input.close();

            JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
            jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(1f);

            final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
            writer.setOutput(new FileImageOutputStream(this.file));
            writer.write(null, new IIOImage(bufferedImage, null, null), jpegParams);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<EncodedRequest> encode() {
        if (this.image != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(this.image, "jpg", baos);
                baos.flush();
                byte[] payload = baos.toByteArray();
                baos.close();

                return Optional.of(new EncodedRequest(this.getType(), payload));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public void setData(BufferedImage data) {
        this.image = data;
    }

    public Optional<BufferedImage> getDate() {
        return Optional.ofNullable(this.image);
    }
}
