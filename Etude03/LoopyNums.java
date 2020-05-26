import java.util.*; 

public class LoopyNums {
	static TreeSet<Integer> bad = new TreeSet<>();
	static TreeSet<Integer> good = new TreeSet<>();
	static int biggestLoop = 0;
	static int loopCount = 0;
	
	public static void main(String[] args) {
		//double startTime = System.currentTimeMillis();
		for(int i = 3; i < 9000000; i++) {
			if(!good.contains(i)) {
				getLoops(i);
			}
		}
		for(int i: good) {
			if(i < 9000000) {
				System.out.println(i); //Prints all numbers in loops that are below 9,000,000
			} else {
				break;
			}
		}
		//double endTime = System.currentTimeMillis();
		//System.out.println("Number of Loops: " + loopCount);
		//System.out.println("Biggest Loop: " + biggestLoop);
		//System.out.println("Duration: " + ((endTime - startTime)/1000.0));
    } 
    static void getLoops(int n) {
        int temp = n; 
        TreeSet<Integer> s = new TreeSet<>();
        s.add(n);
        int i = 1;
		while (n > 0) { 
			n = sumDiv(n);
			if(n > 9000000 && sumDiv(n) != temp) {
				break;
			}
			if(temp == n) {
				if (i == 1) {
					break;
				}
				good.add(temp);
				if(i > biggestLoop) {
					biggestLoop = i;
				}
				loopCount++;
				good.addAll(s);
				break;
			}
			if(bad.contains(n)) {
				break;
			}
			if(s.contains(n) && n != temp) {
				if(!good.contains(n) && n < 9000000) {
					getLoops(n);
				}
				return;
			}
			i++;
			s.add(n);
			
		}
		bad.addAll(s);
	}   
    static int sumDiv(int n)  {
		int sum = 0; 
        for (int i = 1; i <= Math.sqrt(n); i += n % 2 == 0 ? 1 : 2) { 
            if (n % i == 0)  { 
                if (n/i == i)  { 
                    sum = sum+i; 
                } else { 
                    sum = sum+i; 
                    sum = sum+(n/i); 
                } 
            } 
        }
		return sum - n;
    }
}











































