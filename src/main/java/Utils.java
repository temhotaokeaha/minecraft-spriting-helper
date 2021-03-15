
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Utils {

    public static void writeToFile(String content, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    public static void showExceptionPane(Throwable throwable, String title) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        throwable.printStackTrace();
        JOptionPane.showMessageDialog(null, sw.toString(), title, JOptionPane.ERROR_MESSAGE);
        pw.close();
        try {
            sw.close();
        } catch (Exception ignored) {
        }
        System.exit(1);
    }

    public static void showExceptionPaneAndExit(Throwable throwable, String title) {
        showExceptionPane(throwable, title);
        System.exit(1);
    }

    public static void showErrorPaneAndExit(String title, String content) {
        showErrorPane(title, content);
        System.exit(1);
    }

    public static void showErrorPane(String title, String content) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);

    }


    public static void separateTextureAtlas(File file, File outputDir, int offsetX, int offsetY, int width, int height, String[] names, int max) throws Exception {
        BufferedImage image = ImageIO.read(file);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int countX = imageWidth / width;
        int countY = imageHeight / height;

        int count = 0;
        int totalCount = countX * countY;

        for (int y = 0; y < countY; y++) {
            for (int x = 0; x < countX; x++) {
                try {
                    int x1 = offsetX + x * width;
                    int y1 = offsetY + y * height;
                    BufferedImage subimage = image.getSubimage(x1, y1, width, height);
                    String fileName;
                    if(names.length - 1 >= count && !names[count].isEmpty())
                        fileName = names[count] + ".png";
                    else
                        fileName = "texture_" + y + "_" + x + ".png";
                    File newFile = new File(outputDir, fileName);
                    ImageIO.write(subimage, "png", newFile);

                    count++;

                    if(max > 0 && count >= max) {
                        JOptionPane.showMessageDialog(null, "Limit " + max + " reached, Stopping!", "Limit reached", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                } catch (Exception ex) {
                    showExceptionPane(ex, "Error splitting, x: " + x + ", y: " + y);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Done splitting, total: " + totalCount, "Finished", JOptionPane.INFORMATION_MESSAGE);
    }

}
