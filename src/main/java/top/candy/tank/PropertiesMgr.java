package top.candy.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(props.containsKey(key)) {
            return props.get(key);
        }
        return null;
    }
}
