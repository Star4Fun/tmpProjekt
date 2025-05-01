package mm.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SpriteLoader {

    private static final Logger log = LoggerFactory.getLogger(SpriteLoader.class);

    public static SpriteData loadSprite(String name) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = SpriteData.class.getResource("/assets/sprites/"+name+".json");
            SpriteData data = mapper.readValue(new File(url.toURI()), SpriteData.class);
            return data;
        } catch (IOException | URISyntaxException e) {
            log.error("Failed to load sprite: " + name, e);
            return null;
        } 
    }

}
