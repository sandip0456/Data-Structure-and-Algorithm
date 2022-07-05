/*

	Topological Sorting using DFS traversal
	
	Concepts:
		1. 	The ordering of vertices in a directed Acyclic Graph is told to be in Topological ordering if and only if the below condition satisfies
				for every edge u -> v, u must appear before v in the ordering
				
		2. The graph must be Directed Acyclic Graph. 
			a. In case of Undirected graph, if there is an edge u -- v, it is not possible to put u before v and v before u in the same ordering.
			b. In case of Cyclic graph, if the edge sequences are u -> v, v -> w and w -> u, we can put u before v, v before w, but in the same 
				time w can not appear before u.

		3. One Directed Acyclic Graph may contain multiple Topological ordering.
		
		4. The intuition is
			a. Do DFS traversal from the starting vertex, and call DFS again for each of the adjacent vertices.
			b. Once the DFS traversal of all the adjacent vertices of a vertex will be over, we will put the current vertex into stack.
				It means, all adjacent vertices will come after the parent vertex, if we take out the vertices from the stack. And that is what Topological 
				ordering or Topological Sorting is.
				
	Time Complexity: O(V+E), where V is the number of vertices and E is the number of Edges [Since we are utilizing DFS traversal]
	Space Complexity: O(V+E) for maintaining Adjacency List + O(V) for the visited array + O(V) for the Stack data structure
	Auxiliary Space Complexity: O(V) for maintaining Recursion stack.

*/

class TopologicalSortingUsingDFS
{
    private static void topoSortUtil(int node, ArrayList<ArrayList<Integer>> adj, 
												boolean[] visited, Stack<Integer> topoStack) {
                                
        visited[node] = true;
        
        for(Integer adjNode : adj.get(node)) {
            
            if(!visited[adjNode])
                topoSortUtil(adjNode, adj, visited, topoStack);
        }
        
        topoStack.push(node);
        
        return;
    }
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        if(V==0)
            return new int[0];
        
        if(V==1)
            return new int[]{0};
            
        boolean[] visited = new boolean[V];
        Stack<Integer> topoStack = new Stack<>();
        
        // Since Graph may contain multiple components 
        for(int i=0;i<V;i++) {
            
            if(!visited[i]) {
                
                topoSortUtil(i, adj, visited, topoStack);
            }
        }
        
        int[] topoArr = new int[V];
        int idx = 0;
        while(!topoStack.isEmpty())
            topoArr[idx++] = topoStack.pop();
            
        return topoArr;
    }
}