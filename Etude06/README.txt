First checks if all the inputs are correct, the right base, and the 
numbers matching the base.
Second we add the first 2 numbers together, add on the carry and mod
it by the base to get the first added number. We then calculate the
carry with this formula we came up with 
:(currentNum + carry) - ((currentNum + carry) % base))/base
and repeated until we had added all the numbers together.
Lasted to divide the number by 2 we times the remainder by the base,
added it the first digit of the number and divided it by 2, and with
this number we added it to out result.
to work our the remaineder it was the exact same process expect we
mod it by 2 instead of dividing by 2.
Then repeat for every digit in the number. When dividing by 2 the 
remainder will always be 0 or 1 no matter the bass.

Compiles with javac Addition.java
runs with Java Addition

Test cases used:

1)

6
423501
42350112

2)

10
41395712013941
809689754132

3)

4
31201310231
2102

4) 

2
1110111
11