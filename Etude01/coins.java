public class coins{

    public static void main(String[]args){
        int n = 4;
        char[] sequence = new char[(n*2)+2];
        for(int i = 0; i<n; i++){
            sequence[i] = 'H';
            sequence[i+n] = 'T';
        }
        System.out.println(sequence);
        scramble(sequence, n);
    }

    public static void scramble(char[] sequence, int n){
        int gap = n*2, count = 0;
        int lastHead = n-1;
        int firstTail = n;
        int lastTail = (n*2)-1;
        //heads done
        while(lastHead > 1){
            sequence[gap] = sequence[lastHead-1];
            sequence[gap+1] = sequence[lastHead];
            sequence[lastHead] = ' ';
            sequence[lastHead-1] = ' ';
            gap = lastHead-1;
            lastHead = lastHead-2;
            count++;
            System.out.println(sequence);

            sequence[gap] = sequence[lastTail];
            sequence[gap+1] = sequence[lastTail+1];
            sequence[lastTail] = ' ';
            sequence[lastTail+1] = ' ';
            gap = lastTail;
            lastTail--;
            count++;
            System.out.println(sequence);
            //System.out.println(count);
        }
        //tails
        while(gap != n*2){
            sequence[gap] = sequence[firstTail];
            sequence[gap + 1] = sequence[firstTail+1];
            sequence[firstTail] = ' ';
            sequence[firstTail + 1] = ' ';
            lastTail = gap+1;
            gap = firstTail;
            firstTail = firstTail + 2;
            count++;
            System.out.println(sequence);

            sequence[gap] = sequence[lastTail];
            sequence[gap + 1] = sequence[lastTail + 1];
            sequence[lastTail] = ' ';
            sequence[lastTail + 1] = ' ';
            gap = lastTail;
            lastTail--;
            count++;
            System.out.println(sequence);
            // System.out.println(count);
        }
         System.out.println(count);
    }
}