/**
 * IPR | Graphics - Exercise 02: Lines
 * 
 * This is the handout and the exercise notebook for the IPR Graphics Exercise.
 * You have to solve tasks, summing to 12 points.
 * 
 * 
 * @Author [Todo: Student name]
 * @ID [Todo: Student id]
 * 
 * 
 * 
 * @Contact: If you have any questions, please contact the tutors or the
 *           instructor (David Schedl, email: david.schedl@fh-hagenberg.at)
 * 
 * @Notes:
 * 
 *         <ul>
 *         <li>Hand in the solution via Github Classroom on time.
 *         <li>You can delete code that is not related to the tasks.
 *         <li>Ensure that you added your name and matriculation number to the
 *         top of the files and in any additional source files.
 *         </ul>
 */
public class Lines {

	// We define some color values for easier usage in our code.
	final static int WHITE = 0xffffff; // white
	final static int BLACK = 0x000000; // black
	final static int RED = 0xff0000; // red
	final static int GREEN = 0x00ff00; // green
	final static int BLUE = 0x0000ff; // blue
	final static int YELLOW = 0xffff00; // yellow
	final static int GRAY = 0x808080; // gray

	public static void main(String[] args) {

		// Todo: Task 01 and Task 02
		/**
		 * Task 01 & 02: Implement the Bresenham/Midpoint line-drawing algorithm.
		 * [4 + 8 points]
		 * 
		 * - Implement a function for the Bresenham/Midpoint line-drawing algorithm for
		 * the first octant (see lecture slides). [4 points]
		 * 
		 * - Then extend the algorithm to work for the other seven octants. Do
		 * not implement seven other versions of the algorithm but trigger the algorithm
		 * for the first octant with altered variables. [8 points]
		 */
		{
			int w = 64;
			int h = 32;
			int[][] img = IPR.makeImage(w, h, WHITE);

			int x1 = 10;
			int y1 = 15;
			int x2 = 45;
			int y2 = 25;
			// draw a line
			drawLineMP(img, x1, y1, x2, y2, RED); // first octant
			drawLineMP(img, x2, y2, x1, y1, BLACK); // 5th octant

			IPR.imageShow("Bresenham lines", img);
		}

		/**
		 * Test/Verify your line-drawing algorithm with the code below.
		 * 
		 * The code draws a circle (plus connections to its center) with the DDA
		 * algorithm first and then overwrites it with the Midpoint/Bresenham algorithm.
		 * If everything works as expected you should only see a black and grey drawing
		 * of a circle. Seeing excessive colored pixels or lines indicates an error of
		 * your implementation! A few occasional colored pixels might be okay and can be
		 * caused by precision errors, due to rounding.
		 */

		{
			int[][] img = IPR.makeImage(64, 64, WHITE);

			// define circle parameters
			int cx = 30;
			int cy = 30;
			int r = 29;
			var points = createCirclePoints(r, 16, cx, cy);

			for (int i = 0; i < points.length; i++) {
				int x1 = Math.round(points[i][0]);
				int y1 = Math.round(points[i][1]);
				int x2 = Math.round(points[(i + 1) % points.length][0]);
				int y2 = Math.round(points[(i + 1) % points.length][1]);

				// draw with DDA (wheel spokes and circle)
				drawLineDDA(img, x1, y1, cx, cy, YELLOW);
				drawLineDDA(img, x1, y1, x2, y2, BLUE);

				// draw with MP (wheel spokes and circle)
				drawLineMP(img, x1, y1, cx, cy, GRAY);
				drawLineMP(img, x1, y1, x2, y2, BLACK);

			}

			IPR.imageShow("Wheel", img);

		}

	}

	// Todo: Task 01 and Task 02
	/**
	 * Draw a line with the Bresenham/Midpoint algorithm into an image
	 * 
	 * @param img   the image to draw into
	 * @param x1    the x coordinate of the start point
	 * @param y1    the y coordinate of the start point
	 * @param x2    the x coordinate of the end point
	 * @param y2    the y coordinate of the end point
	 * @param color the color of the line
	 */
	public static void drawLineMP(int[][] img, int x1, int y1, int x2, int y2, int color) {

		IPR.setPixel(img, x1, y1, color);
		IPR.setPixel(img, x2, y2, color);

		// Todo: Task 01 and Task 02
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

	/**
	 * Create an array of points for a circle
	 * 
	 * @param r  the radius of the circle
	 * @param n  the number of points/segments
	 * @param cx the x coordinate of the center of the circle
	 * @param cy the y coordinate of the center of the circle
	 * @return an array of points of the form float[n][2]
	 */
	public static float[][] createCirclePoints(float r, int n, int cx, int cy) {
		float[][] points = new float[n][2];

		float alpha = 2.0f * (float) Math.PI / n;

		for (int i = 0; i < n; i++) {
			points[i][0] = cx + r * (float) Math.cos(alpha * i);
			points[i][1] = cy + r * (float) Math.sin(alpha * i);
			// System.out.print("[" + points[i][0] + ", " + points[i][1] + "], ");
		}

		return points;
	}

}
