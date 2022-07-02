/// DFS -- Depth First Search
/// Concept: While we will add the node into DFSList, then only we can mark that node as visited 
/// Time Complexity: O(V+E), where V is the no of Vertices and E is the number of Edges
/// Space Complexity: O(V) for the visited array + Auxiliary space for the Recursion 


class Solution {
    
    private void dfsUtil(int node, ArrayList<ArrayList<Integer>> adj, 
                            ArrayList<Integer> dfsList, boolean[] visited) {
        
        if(visited[node])
            return;
        visited[node] = true;    
        dfsList.add(node);
        
        for(Integer adjNode : adj.get(node)) {
            
            if(!visited[adjNode]) {
                
                
                dfsUtil(adjNode, adj, dfsList, visited);
            }
        }
    }
    
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        if(V<=0)
            return new ArrayList<>();
            
        ArrayList<Integer> dfsList = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        for(int i=0;i<V;i++) {
            
            if(!visited[i]) { 
                dfsUtil(i, adj, dfsList, visited);
            }
        }
        
        return dfsList; 
    }
}