/// BFS -- Breadth First Search Traversal
/// Concepts: 
///			1.	Since Graph can contain multiple components, so running a loop with all the vertices is the best option to check if the specific vertex is 
///				visited or not through some other traversal path. 
///			2.	While a node is inserted into queue, then only we should mark the node as viisted, otherwise, we might add same node in the queue multiple times,
///				Though we will not process same node multiple times, still adding same node multiple times will affect in the space complexity.
/// Time Complexity: O(V+E), where V is the number of vertices and E is the number of Edges
/// Space Complexity: O(V) for visited Array + O(V) for the Queue, which is almost equal to O(V) 
			  


class Solution {
    
    private void bfsUtil(int node, ArrayList<ArrayList<Integer>> adj,
                            boolean[] visited, ArrayList<Integer> bfsList) {
                                
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.offer(node);
        visited[node] = true;
        
        while(!bfsQueue.isEmpty()) {
            
            int curNode = bfsQueue.poll();
            
            bfsList.add(curNode);
            
            for(Integer adjNode : adj.get(curNode)) {
                
                if(!visited[adjNode]) {
                    
                    visited[adjNode] = true;
                    bfsQueue.offer(adjNode);
                }
            }
        }
    }
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        if(V<=0)
            return new ArrayList<>();
            
        boolean[] visited = new boolean[V];
        ArrayList<Integer> bfsList = new ArrayList<>();
        
        for(int i=0;i<V;i++) {
            
            if(!visited[i])
                bfsUtil(i, adj, visited, bfsList);
        }
		
        return bfsList;
    }
}