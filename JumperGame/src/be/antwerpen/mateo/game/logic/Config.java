//package be.antwerpen.mateo.game.logic;
//
//import java.util.Properties;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class Config {
//    private static Properties properties = new Properties();
//
//    static {
//        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){
//            if (input == null){
//                System.out.println("Geen properties file gevonden.");
//            }
//            else {
//                properties.load(input);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static  String getProperty(String key){
//        return properties.getProperty(key);
//    }
//
//    public static int getIntProperty(String key){
//        return Integer.parseInt(properties.getProperty(key));
//    }
//
//    public static double getDoubleProperty(String key){
//        return Double.parseDouble(properties.getProperty(key));
//    }
//}

package be.antwerpen.mateo.game.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("C:\\Users\\Mateo\\Geavanceerde programmeertechnieken\\Project - chose name later\\JumperGame\\src\\be\\antwerpen\\mateo\\game\\resources\\config.properties");
            System.out.println(input);
            if (input == null) {
                System.out.println("Geen properties file gevonden.");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            throw new NumberFormatException("Key not found or value is null for: " + key);
        }
    }

    public static double getDoubleProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            throw new NumberFormatException("Key not found or value is null for: " + key);
        }
    }
}
