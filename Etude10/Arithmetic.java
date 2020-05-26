import java.util.Scanner;

public class Arithmetic{
    static int[] numbers;
    static int target;
    static boolean sequenceComplete = true;
    static String opperands = "";
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            target = 0; //inputted target number.
            boolean leftToRight = false; //arithmetic rule.
            String input = scan.nextLine();
            String[] splitInput = input.split("\\s+");
            numbers = new int[splitInput.length]; //Aray of inputted numbers.

            //Parsing checker below.
            for(int i = 0; i < splitInput.length; i++){
                try{
                    numbers[i] = Integer.parseInt(splitInput[i]);
                } catch(Exception e) {
                    System.out.println("Invalid input");
                    return;
                }
            }
            input = scan.nextLine();
            splitInput = input.split("\\s+");
            try{
                target = Integer.parseInt(splitInput[0]);
             } catch(Exception e) {
                System.out.println("Invalid input");
                return;
            }
            if(splitInput[1].length()==1){
                if(splitInput[1].charAt(0)=='L'){
                    leftToRight = true;
                } else if(splitInput[1].charAt(0)=='N'){
                    leftToRight = false;
                } else {
                    System.out.println("Invalid input");
                    return;
                }
            } else {
                System.out.println("Invalid input");
                return;
            }
            //Parsing checker above
            
            //Min/Max check.S
            int overMaxCheck = numbers[0];
            int underMinCheck = numbers[0];
            for(int i = 1; i < numbers.length; i++){
                if(numbers[i] == 1 || overMaxCheck == 1){
                    overMaxCheck += numbers[i];
                    if(overMaxCheck < 0){
                        overMaxCheck = Integer.MAX_VALUE;
                        break;
                    }
                } else {
                    overMaxCheck *= numbers[i];
                    if(overMaxCheck < 0){
                        overMaxCheck = Integer.MAX_VALUE;
                        break;
                    }
                }
            }
            for(int i = 1; i < numbers.length; i++){
                if(numbers[i] == 1 || underMinCheck == 1){
                    underMinCheck *= numbers[i];
                } else {
                    underMinCheck += numbers[i];
                }
            }
            if(target < underMinCheck || target > overMaxCheck){
                System.out.println(splitInput[1] + " " + target + " Impossible");
            } else {
                
                if(leftToRight){
                    String sequence = "" + numbers[0];
                    System.out.print(splitInput[1] + " " + target + " ");
                    leftToRight(numbers[0], sequence, 1);
                    if(sequenceComplete){
                        System.out.println(" Impossible.");
                    }
                    sequenceComplete = true;
                } else {
                    System.out.print(splitInput[1] + " " + target + " ");
                    bedmasMethod(numbers[0], "", 1, 0);
                    if(sequenceComplete){
                        System.out.println(" Impossible.");
                    } else {
                        for(int i = 0; i < opperands.length(); i++){
                            System.out.print("" + numbers[i] + " " + opperands.charAt(i) + " ");
                        }
                        System.out.println(numbers[numbers.length-1]);
                    }
                    sequenceComplete = true;
                }
            }
        }
    }

    public static void leftToRight(int sum, String sequence, int index){
        if(sum == target && index == numbers.length && sequenceComplete){
            sequenceComplete = false;
            System.out.println(sequence);
        }
        if(sum<=target && index<numbers.length && sequenceComplete){
            int incrementIndex = index +1;
            leftToRight(sum+numbers[index], sequence+" + "+numbers[index], incrementIndex);
            leftToRight(sum*numbers[index], sequence+" * "+numbers[index], incrementIndex);
        }
    }

    public static void bedmasMethod(int sum, String sequence, int index, int prevTimes){
        if(sum == target && index == numbers.length && sequenceComplete){
            sequenceComplete = false;
            opperands = sequence;
        }
        if(sum<=target && index<numbers.length && sequenceComplete){
            int incrementIndex = index +1;
            int previousIndex = index -1;
            bedmasMethod(sum+numbers[index], sequence + "+", incrementIndex, 0);
            if(sequence.length() == 0){
                prevTimes = numbers[previousIndex] * numbers[index];
                bedmasMethod(prevTimes, sequence+"*", incrementIndex, prevTimes);
            } else if(sequence.charAt(sequence.length()-1) == '+'){
                prevTimes = numbers[previousIndex] * numbers[index];
                bedmasMethod((sum-numbers[previousIndex]) + prevTimes, sequence+"*", incrementIndex, prevTimes);
            } else if(sequence.charAt(sequence.length()-1) == '*'){
                int prevTemp;
                if(prevTimes == 0) {
                    prevTimes = 1;
                    prevTemp = 0;
                } else {
                    prevTemp = prevTimes;
                }
                prevTimes *= numbers[index];
                bedmasMethod((sum-prevTemp) + prevTimes, sequence+"*", incrementIndex, prevTimes);
            }
        }
    }
}