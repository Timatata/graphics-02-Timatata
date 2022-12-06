import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.image.DataBufferInt;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class IPR {

    /**
     * Displays the image in a window and keeps it open till the user closes it.
     * 
     * @param title  the title of the image
     * @param pixels the image to be displayed as a 2D array of pixels
     */
    public static void imageShow(String title, int[][] pixels) {

        var h = pixels.length;
        var w = pixels[0].length;

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                img.setRGB(x, y, pixels[y][x]); // flip u,v here!
            }
        }

        JFrame frame = new JFrame();
        frame.setSize(512, 512 * h / w);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title + " (" + w + "x" + h + ")");
        var label = new JLabel();
        var icon = new ImageIcon(img.getScaledInstance(512, 512 * h / w, java.awt.Image.SCALE_FAST));
        label.setIcon(icon);
        label.setSize(512, 512);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        frame.repaint();
    }

    // ----- definitions for Lines exercises (ue03)

    /**
     * Create a new image of the given width and height. The image is filled with a
     * uniform color.
     * 
     * @param w   width of the image
     * @param h   height of the image
     * @param rgb red, green, blue components of the uniform color
     * @return a new image with the given width and height and filled color
     */
    public static int[][] makeImage(int w, int h, int rgb) {
        int[][] pixels = new int[h][w];
        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                setPixel(pixels, x, y, rgb);
            }
        }
        return pixels;
    }

    /**
     * Create a new image of the given width and height. The image is filled with a
     * uniform color.
     * 
     * @param w   width of the image
     * @param h   height of the image
     * @param rgb red, green, blue components of the uniform color
     * @return a new image with the given width and height and filled color
     */
    public static int[][] makeImage(int w, int h) {
        return makeImage(w, h, 0);
    }

    /**
     * Sets the color of a pixel at coordinate u,v in an image
     * 
     * @param pixels the image
     * @param u      pixel u/x coordinate
     * @param v      pixel v/y coordinate
     * @param rgb    the color component
     * 
     */
    public static void setPixel(int[][] image, int u, int v, int rgb) {
        // boundary check
        if (u < 0 || u >= image[0].length || v < 0 || v >= image.length) {
            return; // do nothing
        }
        image[v][u] = rgb & 0xFFFFFF;
    }

}
