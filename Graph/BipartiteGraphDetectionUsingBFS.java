/*
	Bipartite Graph -- a graph is called Bipartite graph, if we are able to color all the nodes with two different colors in such a way that
						two adjacent nodes are colored with same color
	Concepts:
		1. We will do BFS traversal for all the components of the graph, and for each node, we will check if 
			a. the adjacent node is not colored yet, then color it with exact opposite color of current node and add it to queue
			b. the adjacent node is already colored,
				i. if color of adjacent node is opposite of color of current node, then it is absolutely fine
				ii. if adjacent node color is equal to current node color, then that is violating the Bipartite graph property.
					So, we can directly return false, since those two nodes can not be colored with opposite colors.
					
	Observation: 
		A graph will not be Bipartite only if the Graph contains Odd length Cycle. If a Graph will not contain Odd Length cycle, then it can always be bipartite.
		A Graph not containing Odd Length cycle doesn't mean that the Graph contains even length cycle. If Graph does not contain any cycle, or Graph
		contains even length cycle, then the graph must be Bipartite.
		
	Time Complexity: O(V + E), where V is the number of vertices and E is the Number of Edges [Since, we are using BFS traversal algorithm]
	Space Complexity: O(V+E) for Adjacency List + O(V) for Color array + O(V) for BFS Queue
*/

class Solution
{
    private boolean isBipartiteUtil(int node, ArrayList<ArrayList<Integer>> adj, int[] nodeColor) {
                                            
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.offer(node);
        nodeColor[node] = 0;
        
        while(!bfsQueue.isEmpty()) {
            
            int curNode = bfsQueue.poll();
            
            for(Integer adjNode : adj.get(curNode)) {
                
                // If adjacent node is not yet processed
                if(nodeColor[adjNode]==-1) {
                    
                    nodeColor[adjNode] = nodeColor[curNode]==0 ? 1 : 0;
                    bfsQueue.offer(adjNode);
                } 
                
                else {
                    // If adjacent node is already colored and both current and adjacent node is of same color
                    // we will return false, since these two adjacent node can't be colored with two different color
                    if(nodeColor[adjNode] == nodeColor[curNode])
                        return false;
                }
            }
        }
        
        return true;
                                            
    }
    
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // If number of vertices is less than or equal to 2, then the graph must be bipertite
        if(V <= 2)
            return true;
            
        int[] nodeColor = new int[V];
        
        // nodeColor[i] = -1, means not processed
        // nodeColor[i] = 0, means colored with 0
        // nodeColor[i] = 1, means colored with 1
        Arrays.fill(nodeColor, -1);
        
        // Since graph can contain multiple components
        for(int i=0;i<V;i++) {
            
            if(nodeColor[i]==-1 && !isBipartiteUtil(i, adj, nodeColor))
                return false;
        }
        
        return true;
    }
}