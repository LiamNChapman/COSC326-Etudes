Compiles with javac emailCheck.java
runs with Java emailCheck < testCases

Checks the email 1 character at a time, section by section.

Test cases used:

CEO@InsuroCorp.com
gerry_at_research.techies_dot_co.uk
maffu@cs.otago.ac.nz
bob.gmail.com
bob_at_bob_at_john.com
bob--s@john.com
bob..s@john.com
bob-@john.com
bob@jo_hn.com
bob@john..bob.com
bob@hi_at_john.com
bob_at_hi@john.com
bob@hi@john.com
bob@.john.com
-bob@john.com
bob-_s@john.com
a$b@cs.com
ab@c&s.com

cath@[139.80.91.50]
cath@[139.80.91]
cath@[139.80]
cath@[.91]
cath@[91]
cath@[]
cath@[139..80.91.50]
cath@[139.80.91.50.]
cath@[.139.80.91.50]
cath@[1391.80.91.50]
cath@[139.80.91.50.0]
cath@[139.80.91.50].co.nz
cath@[13^9.80.91.50]
cath@[139.80.91.#50]
cath@[139.80.91.50!]
cath@[&139.80.91.50]
cath@[139*.80.91.50]