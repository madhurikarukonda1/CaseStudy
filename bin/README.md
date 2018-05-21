Problem: Barren Land Analysis

Solution:
I have used a Breadth first search tree traversal to visit all the nodes in each fertile area and calculate the total area.
The BFS logic is implemented using queues, where at any given time the queue has the list of nodes to be visited. 
If the queue is empty,then add nodes in the next fertile area or if all the nodes have been visited then the traversal is complete.Nodes are assigned with a non-zero value to identify that they have been visited. 
The results are then sorted and displayed.

Technology stack used:
Java
JUnit
Eclipse

How to run:
Import the java project into eclipse  and run the BarrenLand.java program. The console then prompts for input. Once the input coordinates are given, the fertile areas are printed in sorted manner.
In order to run the test cases, run the unitTest.java file which has the JUnit test cases