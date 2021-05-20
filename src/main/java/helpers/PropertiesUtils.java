package helpers;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

    private String filePath;
    private Map<String, String> propMap;

    public static PropertiesUtils getInstance(String filePath) {
        return new PropertiesUtils(filePath);
    }

    public PropertiesUtils(String filePath) {
        this.filePath = filePath;
        propMap = getPropertiesFileElements();
    }

    private Map<String, String> getPropertiesFileElements() {
       Map var1 = getProperties();
       Map<String, String> var2 = (Map<String, String>) var1;
       return new HashMap<>(var2);
    }

    /*
    if key is present change value
    if key is not present in map add it and value

     */
    public void setProperty(String key, String value) {
        HashMap<String, String> modifiedHashMap = new HashMap<>();
//        int mapSize = propMap.size();
        for (Map.Entry<String, String> ent : propMap.entrySet()) {
            if (ent.getKey().equals(key)) {
//                propMap.remove(key);
                modifiedHashMap.put(key, value);
            } else if (!ent.getKey().equals(key)) {
                modifiedHashMap.put(ent.getKey(), ent.getValue());
            }

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                Properties properties = new Properties();
                for (Map.Entry<String, String> map : modifiedHashMap.entrySet()) {
                    properties.setProperty(map.getKey(), map.getValue());
                }
                properties.store(fileOutputStream, "null");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private Properties getProperties() {
        Properties properties = null;
        try {
            InputStream input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public String getPropertyByName(String propertyName) {
        return getProperties().getProperty(propertyName);
    }

    public void writeProperty(String key, String value) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            Properties properties = getProperties();
            properties.setProperty(key, value);
            properties.store(fileOutputStream, "null");
            fileOutputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
