import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;


public class HilbertCurve extends JPanel{
	private static final int frameSize = 900;
	private static BufferedImage bi;
	private static boolean drawn = false;
    static int curveN = 0;
	static double r = 1;
	static int darkLine = 0;
	static ArrayList<Point> currentOrderCoords = new ArrayList<Point>();
	static ArrayList<Point> nextOrderCoords = new ArrayList<Point>();
	static int[] scales;
	static int normaliseScale = 0;
	static int edgeLength = frameSize;
	static double center = frameSize/2;
	static double newScale = 1;
	static Point previousPoint = new Point(0,0);
	static int extraRLen = 0;
	static int extraRLenCon = 0;
	static double r1 = 1;

	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("Enter values as an argument in the program call.");
			return;
		}
		try{
			curveN = Integer.parseInt(args[0]);
			if(args.length==2){
				r = Double.parseDouble(args[1]);
				r1=r;
			}
		} catch(NumberFormatException e){
			System.out.println("Invalid inputs");
			return;
		}
        curveN = Integer.parseInt(args[0]);
        if(args.length==2){
            r = Double.parseDouble(args[1]);
		}
		scales = new int[curveN];
		scales[0] = 1;
		for(int i = 1; i < curveN; i++){
			scales[i] = (scales[i-1]*2)+1;
			darkLine = (darkLine*4)+3;
		}
		currentOrderCoords.add(new Point((int)(0)/scales[0],(int)(0)/scales[0]));
		currentOrderCoords.add(new Point((int)(0)/scales[0],(int)(frameSize)/scales[0]));
		currentOrderCoords.add(new Point((int)(frameSize)/scales[0],(int)(frameSize)/scales[0]));
		currentOrderCoords.add(new Point((int)(frameSize)/scales[0],(int)(0)/scales[0]));
		if(curveN==1){
			nextOrderCoords = currentOrderCoords;
		} else {
			getCoords(1);
		}

		r = r1;
		extraRLen = (int)Math.round(Math.round((double)(nextOrderCoords.get(nextOrderCoords.size()-1).x - frameSize )/ scales[curveN-1]) * newScale) * -1;
		extraRLenCon = (int)Math.round((Math.round((double)(nextOrderCoords.get(nextOrderCoords.size()-1).x - frameSize )/ scales[curveN-1]) * newScale) * r) * -1;
		int extraRLenConHolder = extraRLenCon;
		currentOrderCoords = new ArrayList<Point>();
		for(Point p : nextOrderCoords){
			currentOrderCoords.add(new Point(p.x, p.y));
		}
		int ydepth = 0; 
		int xdepth = 0;
		Point current = currentOrderCoords.get(0);

		for(int i = 1; i < currentOrderCoords.size(); i++){
			Point previous = current;
			current = currentOrderCoords.get(i);
			if(current.x == previous.x){
				if(current.y > previous.y){
					ydepth++;
				} else {
					ydepth--;
				}
				
				//extraRLenCon = (int)Math.round((Math.round((double)(nextOrderCoords.get(nextOrderCoords.size()-1).x - frameSize )/ scales[curveN-1]) * newScale) * (r*(r1*(ydepth/2)))) * -1;
				if(ydepth==0){
				 	nextOrderCoords.get(i).x += (extraRLen*(int)Math.round(xdepth/2.0)) + extraRLenCon*(xdepth - (int)Math.round(xdepth/2.0));
				} else {
					nextOrderCoords.get(i).y += (extraRLen*(int)Math.round(ydepth/2.0)) + extraRLenCon*(ydepth - (int)Math.round(ydepth/2.0));
					nextOrderCoords.get(i).x += (extraRLen*(int)Math.round(xdepth/2.0)) + extraRLenCon*(xdepth - (int)Math.round(xdepth/2.0));
				}
			} else if(current.y == previous.y){
				if(current.x > previous.x){
					xdepth++;
				} else {
					xdepth--;
				}
				if(xdepth==0){
					nextOrderCoords.get(i).y += (extraRLen*(int)Math.round(ydepth/2.0)) + extraRLenCon*(ydepth - (int)Math.round(ydepth/2.0));
				} else {
					nextOrderCoords.get(i).y += (extraRLen*(int)Math.round(ydepth/2.0)) + extraRLenCon*(ydepth - (int)Math.round(ydepth/2.0));
					nextOrderCoords.get(i).x += (extraRLen*(int)Math.round(xdepth/2.0)) + extraRLenCon*(xdepth - (int)Math.round(xdepth/2.0));
				}
			}
		}

		double scale2 = (double)frameSize/nextOrderCoords.get(nextOrderCoords.size()-1).x;
		for(Point p : nextOrderCoords){
			p.x = (int)Math.round(p.x*scale2) + 10;
			p.y = (int)Math.round(p.y*scale2) + 10;
		}

		JFrame frame = new JFrame("Hilbert Curve");
		HilbertCurve panel = new HilbertCurve();
		frame.getContentPane().setPreferredSize(new Dimension(frameSize+20, frameSize+20));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		bi = new BufferedImage(frameSize + 20, frameSize + 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        frame.setVisible(true);
        g.dispose();

        try {
            ImageIO.write(bi, "jpg", new File("Curve.jpg"));
            drawn = true;
        } catch (Exception e) {
        }
		frame.setVisible(true);
	}

	public static void getCoords(int order){
		if(order == 1){
			normaliseScale = 1;
		} else {
			normaliseScale = scales[order-1];
		}
		order++;
		
		double connectors = (double)(frameSize/scales[order-1])*scales[order-2]*r;
		double nonconnectors = (double)(frameSize/scales[order-1])*(scales[order-2]+1);
		try{
		DecimalFormat df = new DecimalFormat("#.##");
		String nf = df.format((double)(frameSize)/(connectors+nonconnectors));
		newScale = Double.parseDouble(nf);
		} catch(Exception e){
			//System.out.println("Order to big for frame size");
		}
		edgeLength = (int)Math.round((((double)edgeLength*normaliseScale)/((double)scales[order-1])));
		extraRLen = (int)(edgeLength*newScale) - edgeLength;
		center = Math.round((Math.round(center/(double)scales[order-1])*scales[order-2]));

		upperLeft(order, normaliseScale);
		lowerLeft(order, normaliseScale);
		lowerRight(order, normaliseScale);
		upperRight(order, normaliseScale);
		r = r*r1;
		currentOrderCoords = nextOrderCoords;
		if(order < curveN){
			nextOrderCoords = new ArrayList<Point>();
			getCoords(order);
		}
	}

	public static void upperLeft(int order, int normaliseScale){
		int rotatingErrorX = 0;
		int rotatingErrorY = 0;
		for(int i = currentOrderCoords.size()-1; i >= 0; i--){
			int x = (int)Math.round((double)((double)currentOrderCoords.get(i).x*normaliseScale)/scales[order-1]);
			int y = (int)Math.round((double)((double)currentOrderCoords.get(i).y*normaliseScale)/scales[order-1]);
			Point rotatePoint = rotate(new Point(x, y), center, center, 270);

			if(i==currentOrderCoords.size()-1){
				rotatingErrorX = 0-rotatePoint.x;
				rotatingErrorY = 0-rotatePoint.y;
			}

			rotatePoint.x += rotatingErrorX;
			rotatePoint.y += rotatingErrorY;
			if(i==currentOrderCoords.size()-1){
				previousPoint = rotatePoint;
			}

			nextOrderCoords.add(rotatePoint);
		}
	}

	public static void lowerLeft(int order, int normaliseScale){
		int positionErrorX = 0;
		int positionErrorY = 0;
		for(int i = 0; i < currentOrderCoords.size(); i++){
			int x = (int)Math.round((double)((double)currentOrderCoords.get(i).x*normaliseScale)/scales[order-1]);
			int y = (int)Math.round((double)((double)currentOrderCoords.get(i).y*normaliseScale)/scales[order-1]) + (edgeLength*(scales[order-2]+1));
			Point newPoint = new Point(x,y);

			if(i==0){
				positionErrorX = nextOrderCoords.get(nextOrderCoords.size()-1).x-newPoint.x;
				positionErrorY = (nextOrderCoords.get(nextOrderCoords.size()-1).y+(int)(edgeLength*r))-newPoint.y;
			}

			newPoint.x += positionErrorX;
			newPoint.y += positionErrorY;

			nextOrderCoords.add(newPoint);
		}
	}

	public static void lowerRight(int order, int normaliseScale){
		int positionErrorX = 0;
		int positionErrorY = 0;
		for(int i = 0; i < currentOrderCoords.size(); i++){
			int x = (int)Math.round((double)((double)currentOrderCoords.get(i).x*normaliseScale)/scales[order-1]) + (edgeLength*(scales[order-2]+1));
			int y = (int)Math.round((double)((double)currentOrderCoords.get(i).y*normaliseScale)/scales[order-1]) + (edgeLength*(scales[order-2]+1));
			Point newPoint = new Point(x,y);
			if(i==0){
				positionErrorX = (nextOrderCoords.get(nextOrderCoords.size()-1).x)-newPoint.x;
				positionErrorY = (nextOrderCoords.get(nextOrderCoords.size()-1).y)-newPoint.y;
			}
			newPoint.x += (int)(edgeLength*r);

			newPoint.x += positionErrorX;
			newPoint.y += positionErrorY;

			nextOrderCoords.add(newPoint);
		}
	}

	public static void upperRight(int order, int normaliseScale){
		int rotatingErrorX = 0;
		int rotatingErrorY = 0;
		for(int i = currentOrderCoords.size()-1; i >= 0; i--){
			int x = (int)Math.round((double)((double)currentOrderCoords.get(i).x*normaliseScale)/scales[order-1]);// + (edgeLength*(scales[order-2]+1));
			int y = (int)Math.round((double)((double)currentOrderCoords.get(i).y*normaliseScale)/scales[order-1]);
			Point rotatePoint = rotate(new Point(x, y), center, center, 90);

			rotatePoint.x += (edgeLength*(scales[order-2]) + (int)(edgeLength*r));

			if(i==currentOrderCoords.size()-1){
				int d = nextOrderCoords.get(nextOrderCoords.size()-1).x-rotatePoint.x;
				rotatingErrorX = d;
			}
			rotatePoint.x += rotatingErrorX;
			rotatePoint.y += rotatingErrorY;

			nextOrderCoords.add(rotatePoint);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int count = 0;
		int sides = 1;
		if(!drawn){
			drawn = true;
			for(int i = 1; i < nextOrderCoords.size(); i++){
				if(count == darkLine){
					if(sides==1){
						g.drawLine(nextOrderCoords.get(i-1).x+1, nextOrderCoords.get(i-1).y, nextOrderCoords.get(i).x+1, nextOrderCoords.get(i).y);
						g.drawLine(nextOrderCoords.get(i-1).x+2, nextOrderCoords.get(i-1).y, nextOrderCoords.get(i).x+2, nextOrderCoords.get(i).y);
						sides++;
					} else if(sides==2){
						g.drawLine(nextOrderCoords.get(i-1).x, nextOrderCoords.get(i-1).y+1, nextOrderCoords.get(i).x, nextOrderCoords.get(i).y+1);
						sides++;
					} else {
						g.drawLine(nextOrderCoords.get(i-1).x-1, nextOrderCoords.get(i-1).y, nextOrderCoords.get(i).x-1, nextOrderCoords.get(i).y);
						g.drawLine(nextOrderCoords.get(i-1).x-2, nextOrderCoords.get(i-1).y, nextOrderCoords.get(i).x-2, nextOrderCoords.get(i).y);
					}
					count = 0;
				} else {
					count++;
				}
				g.drawLine(nextOrderCoords.get(i-1).x, nextOrderCoords.get(i-1).y, nextOrderCoords.get(i).x, nextOrderCoords.get(i).y);
			}
		} else {
			g.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
	public static Point rotate(Point coord, double centerx, double centery, int angle){
		double newX;
		double newY;
		
		double radian = (angle*Math.PI)/180;
		
		coord.x -= centerx;
		coord.y -= centery;
		
		newX = (coord.x * Math.cos(radian)) - (coord.y * Math.sin(radian));
		newY = (coord.x * Math.sin(radian)) + (coord.y * Math.cos(radian));
		  
		coord.x = (int)Math.round(newX + centerx);
		coord.y = (int)Math.round(newY + centery);
		return coord;
	  }
}