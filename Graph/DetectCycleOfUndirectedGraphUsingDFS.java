/*
	Detect Cycle in an Undirected Graph using DFS traversal
	
	Concepts:
		1. 	In an Undirected Graph, we do not traverse same path twice, but still if we can reach to a node which is already visited, that means some other
			path is already there, which mark that node as visited. So, there can be a cycle.
		2. 	But in undirected graph, through one edge, we can go back and forth, so if there is an edge between u and v, then from u, we can reach to v, and from v as well
			we can reach to u. That means, if a node is visited and we reached to that node again might happen for two cases
				a. from node u, we can reach to node v as adjacent node, and while we are at node v then, we can again reach to node u as adjacent node and 
					since node u has been visited already, we can get node u as visited from node v. But this can not be considered as cycle, since node u
					is the parent node of node v in that path
				b. if we visit some already visited node and that node is not the parent node of the current processing node, then it is obvious that there is a 
					cycle present.
		3.	Through Step 2.b., if we found a cycle, then there is no need to traverse further, and we can directly return True from that position.

	Time Complexity: O(V+E), where V is the number of vertices and E is the number of Edges [Since this is DFS traversal]
	Space Complexity: O(V) for visited array, where V is the number of vertices
	Auxiliary Space: O(V) for maintaining the Recursion Stack, where V is the number of vertices
*/


// Utility Class for maintaining the previous node of a node, or we could say it as parent node of a node in a specific path
// We might not create this unity class and directly pass current node and parent node as parameter in the isCycleUtil() method
class Node {
    
    private int val;
    private int parentVal;
    
    public Node(int val, int parentVal) {
        
        this.val = val;
        this.parentVal = parentVal;
    }
    
    public int getVal() {
        
        return this.val;
    }
    
    public int getParentVal() {
        
        return this.parentVal;
    }
}

class Solution {
    
    private boolean isCycleUtil(Node node, ArrayList<ArrayList<Integer>> adj,
                                    boolean[] visited) {
                                        
        int val = node.getVal();
        int parentVal = node.getParentVal();
        
        visited[val] = true;
        
        for(Integer adjNodeVal : adj.get(val)) {
            
            if(!visited[adjNodeVal]){
                
                if(isCycleUtil(new Node(adjNodeVal, val), adj, visited))
                    return true;
                    
            }
                
            else {
                
                if(adjNodeVal != parentVal)
                    return true;
            }
        }
        
        return false;            
    }
    
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        //Since to create a cycle, atleast 3 nodes are required
        if(V <= 2)
            return false;
            
        boolean[] visited = new boolean[V];
        
        // Since a graph may contain multiple components
        for(int i=0;i<V;i++) {
            
            if(!visited[i] && isCycleUtil(new Node(i, -1), adj, visited)) 
                return true;
        }
        
        return false;
    }
}