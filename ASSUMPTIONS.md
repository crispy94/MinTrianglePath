# Assumptions and decisions

1.- How to deal with the problem
   There are two main ways to take the problem: Searching from top to bottom paths, or from bottom to top paths. 
   As the top to bottom way would be in O(n²) complexity and bottom to top way would be in O(n) complexity, I decided to take the bottom to top option
   
2.- To make easier to procces the data from the files, I´ve read the file and I parsed it into a List[List[Int]] instead of a List[Int], because I thought it´s easier to manage at this case.

3.- As the file has the original data and I shouldn't modify this data, I decided to use List over Array (inmutable over mutable)

4.- If the user introduces an unexisting file, it should be controlled, as well as an empty file, or even if he runs the program without any argument, so I decided to implement a simple error control system

5.- Finally, in order to test the program, I implemented some unit test (thanks to that I saw i need to change "+" to " + " in the final string to fit with the problem's solution example)

6.- I changed the error raw strings to an object because I don't like to have unnecessary raw strings in code

NOTE: I assumed the algorithm is only used with integers, if we want to change to use for decimals, I should change the Int type to Double for example.
