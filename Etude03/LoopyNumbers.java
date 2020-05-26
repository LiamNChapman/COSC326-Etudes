import java.util.TreeSet;

public class LoopyNumbers{
    static int loopCount = 0, biggestLoop = 0, biggestLoopNumber = 0;
    private static TreeSet<Integer> successfulLoops = new TreeSet<Integer>();
	private static TreeSet<Integer> badLoops = new TreeSet<Integer>();
    
    public static void main(String[] args){
        int currentLoop = 1;
        for(int i = 1; i <= 900000; i++){
            if(isPrime(i)){
                currentLoop = 1;
            } else {
                if(!successfulLoops.contains(i)){
                    int iteration = sumOfDivisors(i);
                    while(true){
                        //System.out.println("i : " + i + " iteration :  " + iteration);
                        if(iteration == 0 || iteration == sumOfDivisors(iteration) || iteration == 1 || isPrime(iteration) || successfulLoops.contains(iteration)){
                            badLoops = new TreeSet<Integer>();
							currentLoop = 1;
							break;
                        }

						badLoops.add(iteration);
                        iteration = sumOfDivisors(iteration);
                        currentLoop++;
                        if(iteration == i){
                            successfulLoops.add(i);
                            loopCount++;
                            if(currentLoop > biggestLoop){
                                biggestLoop = currentLoop;
								biggestLoopNumber = i;
                            }
                            System.out.println(i);
							badLoops = new TreeSet<Integer>();
							currentLoop = 1;
                            break;
                        } else {
							if(badLoops.contains(iteration)){
								badLoops = new TreeSet<Integer>();
								currentLoop = 1;
								break;
							}
						}
						
                    }
                }
            }
        }
        System.out.println("Loops: " + loopCount);
        System.out.println("Biggest loop: " + biggestLoop);
        System.out.println("Biggest loop number: " + biggestLoopNumber);
    }

    public static int sumOfDivisors(int num){
        int sum = 0;

        for(int i = 1; i <= Math.sqrt(num); i++){
            if(num%i==0){
				if(num/i==i){
                sum += i;
				} else {
					sum += i;
					sum += (num/i);
				}
            }
        }
        return sum-num;
    }

    public static boolean isPrime(int num){
        if(num > 2 && num%2==0){
            return false;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
