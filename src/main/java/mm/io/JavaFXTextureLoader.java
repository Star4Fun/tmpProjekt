package mm.io;

import java.net.URL;

import javafx.scene.image.Image;

public class JavaFXTextureLoader implements TextureLoader<Image> {

    @Override
    public Image load(String path) {
        if(!(path.endsWith(".png") || path.endsWith(".bmp") || path.endsWith(".jpeg") || path.endsWith(".jpg") || path.endsWith(".gif"))) {
            throw new IllegalArgumentException("Image type has to be: png, bmp, jp(e)g or gif");
        }
        URL url = getClass().getResource("/"+path);
        return new Image(url.toExternalForm());
    }

}
