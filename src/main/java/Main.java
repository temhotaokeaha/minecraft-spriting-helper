import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {


    public static void main(String[] args){
        Config.loadConfig();

        int textureSize = Config.getProperty("texture_size");
        int maxSprites = Config.getProperty("max_sprites");
        File inputFile = new File((String) Config.getProperty("input_file"));
        if(!inputFile.exists())
            Utils.showErrorPaneAndExit("File not found", "Input File not found!");
        File namesFile = new File((String) Config.getProperty("names_file"));
        if(!namesFile.exists())
            Utils.showErrorPaneAndExit("File not found", "Names File not found!");
        File outputDir = new File((String) Config.getProperty("output_path"));
        if(!outputDir.exists())
            outputDir.mkdirs();
        else if(!outputDir.isDirectory())
            Utils.showErrorPaneAndExit("Not directory", "Output is not a directory!");



        try {
            BufferedReader br = new BufferedReader(new FileReader(namesFile));
            String[] names = br.lines().toArray(String[]::new);
            Utils.separateTextureAtlas(inputFile, outputDir, 0, 0, textureSize, textureSize, names, maxSprites);
        }catch (Throwable t){
            Utils.showExceptionPaneAndExit(t, "Failed to split");
        }


    }

}