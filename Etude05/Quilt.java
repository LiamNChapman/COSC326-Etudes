import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Quilt extends JPanel{
	private static float totalScale = 0;
	private static final int frameSize = 750;
	private static ArrayList<Float> scales = new ArrayList<Float>();
	private static ArrayList<Color> colours = new ArrayList<Color>();

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			String[] inputs = line.split(" ");
			int r = Integer.parseInt(inputs[1]), g = Integer.parseInt(inputs[2]), b = Integer.parseInt(inputs[3]);
			float scale = Float.parseFloat(inputs[0]);
			if((r > 255 || r < 0) || (g > 255 || g < 0) || (b > 255 || b < 0)){
				System.out.println("Invalid RGB value");
				r = 0; g = 0; b = 0;
			} else {
				scales.add(scale);
				colours.add(new Color(r, g, b));
			}
		}
		for(float scale : scales){
			totalScale += scale;
		}
		JFrame frame = new JFrame("Quilt");
		Quilt panel = new Quilt();
		panel.setPreferredSize(new Dimension(frameSize, frameSize));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	public void drawSquares(Graphics g, float scale, Color colour, int x, int y, int depth){
		int newSize = (int)((float)(frameSize)*scale);
		g.setColor(colour);
		g.fillRect(x-newSize/2,y-newSize/2,newSize, newSize);
		if(depth < scales.size()){
			drawSquares(g, scales.get(depth)/totalScale, colours.get(depth), x-newSize/2, y-newSize/2, depth+1); //bottom left
			drawSquares(g, scales.get(depth)/totalScale, colours.get(depth), x+(newSize/2), y-newSize/2, depth+1); //bottom right
			drawSquares(g, scales.get(depth)/totalScale, colours.get(depth), x-newSize/2, y+(newSize/2), depth+1); //top left
			drawSquares(g, scales.get(depth)/totalScale, colours.get(depth), x+(newSize/2), y+(newSize/2), depth+1); //top right
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawSquares(g, scales.get(0)/totalScale, colours.get(0), frameSize/2, frameSize/2, 1);
	}
}