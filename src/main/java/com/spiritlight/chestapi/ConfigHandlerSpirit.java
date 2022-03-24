package com.spiritlight.chestapi;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;
// import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigHandlerSpirit  {
    public config load() throws FileNotFoundException {
        // InputStream inputStream = new FileInputStream(new File("config.yaml"));
        InputStream inputStream = new FileInputStream("config.yaml");
        Yaml yaml = new Yaml(new Constructor(config.class));
        // config data = yaml.load(inputStream);
        return yaml.load(inputStream);
    }
}
