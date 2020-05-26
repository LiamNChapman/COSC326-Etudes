Liam Chapman - COSC326 Etude 10.

Compiles with: javac Arithmetic.java
Runs with: Java Arithmetic < tests.txt

Firstly I check that the input is correct. Next I check if the target number
is less than the possible smallest number or greater than the possible largest
number, and if so then print "impossible".

For left to right I pass is the sum (which will be the first number in the 
list), a string sequence which will be adding the number and sign to with each
recursive call, and an index (which will start at 1). I check if the sum is 
equal to the target and if the index is equal to the length of the array of 
numbers, if these cases are true then that means the number is found we print 
the sequence. The other check checks if the index is less than the array length 
and that the sum is less than or equal to the target (equal to incase the rest 
of the numbers are 1s), if these cases are true I recurse. There are two 
recursive calls, one that takes the sum plus the next numbers and the sequence 
doing the same, and other other the same but with times.

The bedmas method is exactly the same as the Left to Right method, however the 
way that the number is timesed is different. How I times my numbers is by 
minusing the pevious number off the sum, and adding that previous number 
timesed by the the next number to the altered sum.

Test cases used:
1 2 3
9 L
1 1 1 1 1
1 N
1 2 3
7 N
1 2 3
100 N
1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5
472 N
1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5
1841 L