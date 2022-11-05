package priv.klochkov.island.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private String nameFile;
    private Properties properties;

    public PropertiesReader(String nameFile) {
        this.nameFile = nameFile;
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getNameFile() {
        return nameFile;
    }

    public Properties getProperties() {
        return properties;
    }
}
