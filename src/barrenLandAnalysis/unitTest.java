package barrenLandAnalysis;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

public class unitTest{
	LinkedList<Integer[]> barrenLandNodes;
	LinkedList<Integer[]> queue;
	final static int xMax = 100;
	final static int yMax = 100;
	int visited[][];
	HashMap<Integer, Integer> areasMap;
	BarrenLand testObj;

	@Test
	public void test1_pass() {
		//test processInput
		String str="{“48 192 351 207”";
		String expected="48 192 351 207";
		assertEquals(expected,BarrenLand.processInput(str));
		
	    str="“48 192 351 207”";
		expected="48 192 351 207";
		assertEquals(expected,BarrenLand.processInput(str));
	}
	
	@Test
	public void test1_fail() {
		//test processInput
		String str="{|“48 192 351 207”";
		String expected="48 192 351 207";
		assertFalse(expected==BarrenLand.processInput(str));
		
	    str="48 192 351 207”";
		expected="48 192 351 207";
		assertFalse(expected==BarrenLand.processInput(str));
	}
	
	@Test
	public void test2_pass(){
		//test makrBarrenLand
		testObj=new BarrenLand();
		Integer[] temp={0,292,399,307};
		testObj.barrenLandNodes.add(temp);
		testObj.markBarrenLand();
		int expected=1;
		assertEquals(expected,testObj.visited[0][292]);
	}
	
	@Test
	public void test2_fail(){
		//test makrBarrenLand
		testObj=new BarrenLand();
		Integer[] temp={0,292,399,307};
		testObj.barrenLandNodes.add(temp);
		testObj.markBarrenLand();
		int expected=1;
		assertFalse(expected==testObj.visited[0][0]);
	}
	
	@Test
	public void test3_pass() {
		//test node visited
		testObj=new BarrenLand();
		testObj.visitedNodes(4,2,3);
		assertEquals(3,testObj.visited[4][2]);
	}

	@Test
	public void test3_fail() {
		//test node visited
		testObj=new BarrenLand();
		testObj.visitedNodes(4,2,3);
		assertFalse(4==testObj.visited[4][2]);
	}
	@Test
	public void test4_pass() {
		//test node not visited
		testObj=new BarrenLand();
		testObj.visited[6][6]=0;
		testObj.unvisitedNodes(6,6);
		assertTrue(!testObj.queue.isEmpty());
	}

	@Test
	public void test4_fail() {
		//test node not visited
		testObj=new BarrenLand();
		testObj.visited[6][6]=0;
		testObj.unvisitedNodes(6,6);
		assertFalse(testObj.queue.isEmpty());
	}
	
	
	@Test
	public void test5() {
		//test get fertile land
		testObj=new BarrenLand();
		String userInput="{“0 292 399 307”}";
		String[] coordinates=userInput.split(",");
		String str;
		for(int i=0;i<coordinates.length;i++){			
			str=BarrenLand.processInput(coordinates[i]);
		    
		    if(!str.isEmpty()){
		    	String[] val = str.split(" ");
		    
		    	Integer[] temp = {Integer.parseInt(val[0]), Integer.parseInt(val[1]), 
		    					  Integer.parseInt(val[2]), Integer.parseInt(val[3])};
		    
		    	testObj.barrenLandNodes.add(temp);
		    	
		    }
		    
	     }
		 testObj.markBarrenLand();
		 String fertileAreas=testObj.getFertileLand();
		 String expected="116800 116800";
		 assertEquals(expected,fertileAreas);
		
	}
}
