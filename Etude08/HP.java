import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class HP{
    private static ArrayList<Double[]> placedPatches = new ArrayList<Double[]>();
    private static ArrayList<Double[]> holes = new ArrayList<Double[]>();
    private static ArrayList<Integer> coveredHoles = new ArrayList<Integer>();
    private static int[] sheetSize = new int[2];
    private static Double patchSize;
    private static Random randomHole = new Random();
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        scan.next();
        scan.next();
        try {
            sheetSize[0] = scan.nextInt();
            sheetSize[1] = scan.nextInt();
            scan.next();
            scan.next();
            patchSize = scan.nextDouble();
            scan.nextLine();
            scan.nextLine();
            while(scan.hasNextLine()) {
                String hole[] = scan.nextLine().split("s\\+");
                if(hole.length == 1) {
                    if(hole[0].equals("END")) {
                        // find
                        //reset
                        continue;
                    } else if(hole[0].equals("ENDFILE")) {
                        return;
                    }
                }
                Double x = Double.parseDouble(hole[0]);
                Double y = Double.parseDouble(hole[1]);
                Double diameter = Double.parseDouble(hole[2]);
                holes.add(new Double[]{x, y, diameter});
            }
        } catch (Exception e) {
        }
    }

    
    public static boolean throwPatch(){
        int nextHole = randomHole.nexInt(holes.size());
        Double[] holeToCover = holes.get(nextHole);
        Double[] boundingCircle = {holeToCover[0], holeToCover[1], (holeToCover[2] + patchSize - 0.1)};
        Double[] patchPlacingCircle = {holeToCover[0], holeToCover[1], (holeToCover[2] + patchSize/2 - 0.1)}; //circle where the patches center can be place in
        boolean patchPlaced =  false;
        // Finds a random hole that has not already been covered. (this will probably be moved to the recursive method and be passed in to this method)
        while(!coveredHoles.contains(nextHole)){
            nextHole = randomHole.nexInt(holes.size());
        }
        // Check if there are any other uncovered holes in the current holes bounding circle.
        // If there is then move the bounding circles center to be in the center of all those holes,
        // remembering that there must be a 1mm gap between the edge of the circle and the holes.
        while(!patchPlaced){
            // Generate a random point within patchPlacingCircle which will be the center of the patch we will place (can be optimised to cancel out areas already covered)
            // Check if the patch is covering a hole.
            // Check if the patch is colliding with any other patches/holes
            // If it is not, place the patch, patchPlaced = true/return true, otherwise keep looping
            // Add this placed patch to the list of placed patches, add the indexs of any holes covered to coveredHoles.
            // If you can't place the hole(patchPlaced stays false or after a counter) then leave the while loop and return false
        }
        // If returned false, a patch cant be placed of this hole at this current state
        // So recurse back (unpatch some holes) to try different variations.
        return false;
    }
}