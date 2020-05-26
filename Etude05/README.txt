Takes in input, splits it up by " "'s and adds them to an arraylist.
paintComponent method calls paints the original square then calls
the recursive method, which will go up painting squares in a corner
until reached the depth limit.

Compiles with javac Quilt.java
runs with Java Quilt < testCases

Test cases used: 

1)

1.0 255 0 0
0.8 0 255 0
0.1 0 0 255

2)

0.8 255 0 0
0.6 0 255 0
0.2 0 0 255
0.1 0 255 255
0.05 0 0 0