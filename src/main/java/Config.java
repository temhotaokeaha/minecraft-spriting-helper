import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {
    
    private static String NAME = "config.txt";

    private static File FILE;

    private static HashMap<String, Value<?>> CONFIGS = new HashMap<>();


    public static <T> T getProperty(String key){
        if(FILE == null || CONFIGS.isEmpty())
            loadConfig();
        try {
            return (T) CONFIGS.get(key).value;
        }catch (Exception e){
            Utils.showErrorPaneAndExit("Could not find config key", "Could not find config key: " + key + ", please check your config!");
            return null;
        }
    }

    public static void loadConfig(){
        FILE = new File(System.getProperty("user.dir"), NAME);
        if(!FILE.exists())
            saveDefaultConfig();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            br.lines().forEach(string -> {
                String[] split = string.split("=", 2);
                if(split.length == 2) {
                    try {
                        new Value<>(split[0], Integer.parseInt(split[1]));
                    }catch (NumberFormatException e){
                        new Value<>(split[0], split[1]  );
                    }
                }
            });
        } catch (Exception e) {
            Utils.showExceptionPaneAndExit(e, "Failed to read config");

        }
    }

    public static void saveDefaultConfig(){
        new Value<>("texture_size", 16);
        new Value<>("max_sprites", -1);
        new Value<>("input_file", System.getProperty("user.dir") + File.separator + "input.png");
        new Value<>("output_path", System.getProperty("user.dir") + File.separator + "output");
        new Value<>("names_file", "names.txt");

        StringBuilder builder = new StringBuilder();

        CONFIGS.entrySet().forEach(entry -> {
            builder.append(getString(entry));
            builder.append("\n");
        });

        String content = builder.toString();
        try {
            Utils.writeToFile(content, FILE);
        }catch (Exception e){
            Utils.showExceptionPaneAndExit(e, "Failed to save default config");
        }
        JOptionPane.showMessageDialog(null, "Default config saved! Edit your config now!");
        System.exit(0);
    }

    public static String getString(Map.Entry<String, Value<?>> entry){
        return entry.getKey() + "=" + entry.getValue().value;
    }


    public static class Value<T> {

        public Value(String key){
            this.key = key;
            CONFIGS.put(key, this);
        }

        public Value(String key, T def){
            this.key = key;
            this.value = def;
            CONFIGS.put(key, this);
        }

        public String key;

        public T value;

    }


}
