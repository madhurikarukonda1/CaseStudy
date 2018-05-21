package barrenLandAnalysis;


import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class BarrenLand {
	LinkedList<Integer[]> barrenLandNodes;
	LinkedList<Integer[]> queue;
	final static int xMax = 400;
	final static int yMax = 600;
	int visited[][];
	HashMap<Integer, Integer> areasMap;
	protected BarrenLand(){
		this.barrenLandNodes=new LinkedList<Integer []>();
		this.queue = new LinkedList<Integer []>();
		this.visited=new int[xMax][yMax];
		for(int i = 0; i < xMax; i++)
			  for(int j = 0; j < yMax; j++)
				  this.visited[i][j] = 0;
		this.areasMap = new HashMap<Integer, Integer>();
	}
	
	protected void visitedNodes(int i,int j,int val){
		this.visited[i][j] = val;
	}
	protected void unvisitedNodes(int i, int j){
		if(this.visited[i][j] == 0){	
			this.queue.add(new Integer[] {i, j});
		}
	}
	
	protected void visitAllNodes(){
		int area,i,j;
		area= 1;
		i = 0;
		j = 0;		
		while(i < xMax && j < yMax){
			// BFS traversal i.e.., we pass through all the x-coordinate nodes before going to next y-coordinate node
			if(this.queue.isEmpty()) {
				// we are in a new fertile land
				Integer node[] = {i, j};
				if(this.visited[i][j] == 0) {	
					// If node[i][j] has not been visited add to queue
					area++;
					this.areasMap.put(area, 0);
					this.queue.add(node);
				}
				if(i == (xMax-1)){
					i = 0;
					j++;
				}
				else 
					i++;
			}
			
			if(!this.queue.isEmpty()) {
				//get the next node from the queue and check if it is visited or not
				//if not visited, then visit and add adjacent nodes to queue
				Integer node[] = this.queue.pop(); 
			
				int x = node[0];
				int y = node[1];
						
				if(this.visited[x][y] == 0){
					if(x > 0)
						this.unvisitedNodes(x-1, y);
					if(x < (xMax - 1))
						this.unvisitedNodes(x+1, y);
					if(y > 0) 
						this.unvisitedNodes(x, y-1);
					if(y < (yMax - 1))
						this.unvisitedNodes(x, y+1);
					
					//mark the node as visited by assigning the area covered while visiting this node
					this.visitedNodes(x,y,area); 
					
					//update the map with the new area
					this.areasMap.put(area, (this.areasMap.get(area) + 1));
				}
			}
		}
	}
	
	protected String getFertileLand(){
		int[] result;
	    int i = 0;	
		
	    //visit all the nodes to calculate the area of fertile land
	    this.visitAllNodes();
	    
	    //sort the results and print them
	    result= new int[this.areasMap.values().size()];
		for (Map.Entry<Integer, Integer> entry : this.areasMap.entrySet()){
		     result[i] = entry.getValue();
		     i++;
		}
		Arrays.sort(result);		
		return (Arrays.toString(result)).replaceAll("\\[|\\]|,", ""); 
		
	}
	protected void markBarrenLand(){
		ListIterator<Integer[]> iterator = this.barrenLandNodes.listIterator();		
		while(iterator.hasNext()){
			Integer[] coordinates = iterator.next();
			for(int i = coordinates[0]; i <= coordinates[2]; i++)
				for(int j = coordinates[1]; j <= coordinates[3]; j++)
					this.visited[i][j] = 1;							
		}
		
	}
	protected static String processInput(String str){
		str = str.replaceAll("“|”", ""); //for “ ”
	    str = str.replaceAll("\\{|\\}", ""); //for {}
	    str = str.replaceAll("^ ", ""); //for space
		return str; 
	}
	
	public static void main(String[] args){
		int i;
		String str;
		BarrenLand obj=new BarrenLand();
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the input coordinates: \nNote: coordinates of the field are from (0, 0) to (399, 599) \ninput should be in the following format: {“1 2 3 4”}. \nFor multiple coordinates use ',' \n ");		
		String[] coordinates = (input.nextLine()).split(",");
		for(i=0;i<coordinates.length;i++){			
			str=processInput(coordinates[i]);
		    
		    if(!str.isEmpty()){
		    	String[] val = str.split(" ");
		    
		    	Integer[] temp = {Integer.parseInt(val[0]), Integer.parseInt(val[1]), 
		    					  Integer.parseInt(val[2]), Integer.parseInt(val[3])};
		    
		    	obj.barrenLandNodes.add(temp);
		    	
		    }
		    
	     }
		 obj.markBarrenLand();
		 String fertileAreas=obj.getFertileLand();
		 System.out.println(fertileAreas);
         input.close();
	}
}

