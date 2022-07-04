/**
	Detection if a graph is Bipartite or not
	
	Concepts:
		1. A Graph is called Bipartite if we can color the vertices of the graph in such a way that no two adjacent vertices have same color
		2. We will do DFS traversal of each component of the graph, and while we will be at a vertex, we will check
			a. 	if the adjacent vertex is colored or not		
				i. 	if yes, we will check if the color of current vertex and color of adjacent vertex is same or not. If same, that means those two vertex
					can never be colored with two different colors, and we can return false as if the Graph is not Bipartite. 
					If not same, we can proceed with the next adjacent node.
					
			b. 	If the adjacent vertex is not colored yet, means the adjacent node is not processed yet, 
				We will color the adjacent vertex with the opposite color of the current vertex and do DFS traversal with the adjacent vertex.
				
	Observation: 
		A graph will not be Bipartite only if the Graph contains Odd length Cycle. If a Graph will not contain Odd Length cycle, then it can always be bipartite.
		A Graph not containing Odd Length cycle doesn't mean that the Graph contains even length cycle. If Graph does not contain any cycle, or Graph
		contains even length cycle, then the graph must be Bipartite. 

	Time Complexity: O(V+E), where V is the number of vertices and E is the number of Edges [Since we are utilizing DFS traversal algorithm]
	Space Complexity: O(V+E) for creating or maintaining the Adjacency list + O(V) for the Color array
	Auxiliary Space: O(V) for maintaining the Recursion Stack

*/

class Solution
{
    private boolean isBipartiteUtil(int node, ArrayList<ArrayList<Integer>> adj,
                                        int[] nodeColor) {
    
        for(Integer adjNode : adj.get(node)) {
            
            if(nodeColor[adjNode]==-1) {
                
                nodeColor[adjNode] = nodeColor[node]==0 ? 1 : 0;
                if(!isBipartiteUtil(adjNode, adj, nodeColor))
                    return false;
            }
            
            else {
                
                if(nodeColor[adjNode] == nodeColor[node])
                    return false;
            }
        }
        
        return true;
    }
    
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // If a graph contains less than three nodes, it must be a bipartite graph
        // Because to create cycle, atleast three nodes are required
        if(V < 3)
            return true;
            
        int[] nodeColor = new int[V];
        
        // nodeColor[i] = -1 means not processed
        // nodeColor[i] = 0 means colored with 0
        // nodeColor[i] = 1 means colored with 1
        Arrays.fill(nodeColor, -1);
        
        //Since Graph may contain multiple components
        for(int i=0;i<V;i++) {
            
            if(nodeColor[i]==-1) {
                
                nodeColor[i] = 0;
                if(!isBipartiteUtil(i, adj, nodeColor))
                    return false;
            }
        }
        
        return true;
    }
}