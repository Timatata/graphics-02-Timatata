/**
 * IPR | Graphics - Exercise 02: Lines
 * 
 * @Contact: If you have any questions, please contact the tutors or the
 *           instructor (David Schedl, email: david.schedl@fh-hagenberg.at)
 */
public class LectureLines {

	// We define some color values for easier usage in our code.
	final static int WHITE = 0xffffff; // white
	final static int BLACK = 0x000000; // black
	final static int RED = 0xff0000; // red
	final static int GREEN = 0x00ff00; // green
	final static int BLUE = 0x0000ff; // blue
	final static int YELLOW = 0xffff00; // yellow
	final static int GRAY = 0x808080; // gray

	public static void main(String[] args) {

		/*
		 * Line drawing with Implicit Form (k<1)
		 */
		if (true) {
			int w = 64;
			int h = 32;

			int[][] pixels = IPR.makeImage(w, h, WHITE);

			// line parameters:
			float k = 0.3f;
			float o = 10.0f;

			// draw a line
			for (int x = 0; x < w; x++) {
				float y = k * x + o;
				IPR.setPixel(pixels, x, Math.round(y), BLACK);
			}

			IPR.imageShow("Line Drawing (k<1) Image", pixels);
		}

		/*
		 * Line drawing with Implicit Form (k<1)
		 */
		if (false) {
			int w = 64;
			int h = 32;

			int[][] pixels = IPR.makeImage(w, h, WHITE);

			// line parameters:
			float k = 1.5f;
			float o = 10.0f;

			// draw a line
			for (int y = 0; y < w; y++) {
				float x = (y - o) / k;
				IPR.setPixel(pixels, Math.round(x), Math.round(y), BLACK);
			}

			IPR.imageShow("Line Drawing (k>1) Image", pixels);
		}

		/*
		 * DDA Algorithm
		 * 
		 */
		if (false) {
			// define a line with two points
			int x1 = 50;
			int y1 = 15;
			int x2 = 15;
			int y2 = 30;

			int w = 64;
			int h = 32;
			int[][] img = IPR.makeImage(w, h, WHITE);

			IPR.setPixel(img, x1, y1, RED); // set the start point RED (should be hidden by line)
			IPR.setPixel(img, x2, y2, RED); // set the end point RED (should be hidden by line)
			drawLineDDA(img, x1, y1, x2, y2, BLACK); // draw the line

			IPR.imageShow("DDA Image", img);

		}

	}

	/**
	 * Draw a line with the DDA algorithm into an image
	 * 
	 * @param img   the image to draw into
	 * @param x1    the x coordinate of the start point
	 * @param y1    the y coordinate of the start point
	 * @param x2    the x coordinate of the end point
	 * @param y2    the y coordinate of the end point
	 * @param color the color of the line
	 */
	public static void drawLineDDA(int[][] img, int x1, int y1, int x2, int y2, int color) {
		int steps = Math.max(Math.abs(y2 - y1), Math.abs(x2 - x1));

		float incX = (x2 - x1) / (float) steps;
		float incY = (y2 - y1) / (float) steps;
		float x = x1;
		float y = y1;

		for (int i = 0; i <= steps; i++) {
			IPR.setPixel(img, Math.round(x), Math.round(y), color);
			x += incX;
			y += incY;
		}

	}

}
