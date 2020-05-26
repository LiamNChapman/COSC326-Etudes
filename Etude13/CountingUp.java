import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class CountingUp{
    private static ArrayList<Long> map1 = new ArrayList<Long>();
    private static ArrayList<Long> map2 = new ArrayList<Long>();
    private static ArrayList<Integer> factors1 = new ArrayList<Integer>();
    private static ArrayList<Integer> factors2 = new ArrayList<Integer>();
    public static void main(String[] args){
        Long n = 0L; 
        Long k = 0L;
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            try{
                String input = scan.nextLine();
                String[] splitInput = input.split("\\s+");
                if(splitInput.length == 2){
                    n = Long.valueOf(splitInput[0]);
                    k = Long.valueOf(splitInput[1]);
                    if(k>n){
                        System.out.println("K cannot be larger than N");
                        continue;
                    }
                    nChooseK(n, k);
                    map1 = new ArrayList<Long>();
                    map2 = new ArrayList<Long>();
                    factors1 = new ArrayList<Integer>();
                    factors2 = new ArrayList<Integer>();
                } else {
                System.out.println("Invalid input format");
                }
            } catch(NumberFormatException e){
                System.out.println("Inputted Value too big");
            }
        }
    }

    public static void nChooseK(Long n, Long k){
        if(k < n/2){
            k = n-k;
        }
        for(Long iterate = n; iterate > k; iterate--){
            map1.add(iterate);
            map2.add(iterate-k);
        }

        if(map1.size() < map2.size()){
            for(int iterate = 0; iterate < map1.size(); iterate++){
                if(map2.contains(map1.get(iterate))){
                    map2.remove(map1.get(iterate));
                    map1.remove(map1.get(iterate));
                }
            }
        } else {
            for(int iterate = 0; iterate < map2.size(); iterate++){
                if(map1.contains(map2.get(iterate))){
                    map1.remove(map2.get(iterate));
                    map2.remove(map2.get(iterate));
                }
            }
        }
        for(int iterate = 0; iterate < map1.size(); iterate++){
            primeFactors(map1.get(iterate), factors1);
        }
        for(int iterate = 0; iterate < map2.size(); iterate++){
            primeFactors(map2.get(iterate), factors2);
        }     

        if(factors1.size() < factors2.size()){
            for(int iterate = 0; iterate < factors1.size(); iterate++){
                if(factors2.contains(factors1.get(iterate))){
                    factors2.remove(factors1.get(iterate));
                    factors1.remove(factors1.get(iterate));
                    iterate--;
                }
            }
        } else {
            for(int iterate = 0; iterate < factors2.size(); iterate++){
                if(factors1.contains(factors2.get(iterate))){
                    factors1.remove(factors2.get(iterate));
                    factors2.remove(factors2.get(iterate));
                    iterate--;
                }
            }
        }
        Long sum1 = 1L;
        Long sum2 = 1L;
        while(factors1.size() > 0 || factors2.size() > 0){
            if(factors1.size() > 0){
                sum1 *= factors1.get(0);
                factors1.remove(0);
            }
            if(factors2.size() > 0){
                sum2 *= factors2.get(0);
                factors2.remove(0);
            }
        }
        Long result = sum1/sum2;
        System.out.println(result);
    }

    public static void primeFactors(Long n, ArrayList<Integer> factors){ 
        while(n%2 == 0){ 
            factors.add(2); 
            n /= 2; 
        } 
        for(int i = 3; i <= Math.sqrt(n); i+= 2){
            while (n%i == 0){ 
                factors.add(i ); 
                n /= i; 
            } 
        } 
        if (n > 2){
            factors.add(Integer.parseInt(n+""));    
        }
    } 
 }