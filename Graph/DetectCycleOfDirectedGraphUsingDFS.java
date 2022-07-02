/// Cycle Detection in Directed Graph using DFS Traversal
///
/// Concepts:
///			1. 	Since in DFS Traversal algorithm, one path between nodes can be traversed only once. So, during traversal, if we found a node which is 
///				visited already and again reached to the same node, we can say there must be two different path to reach to the node.
///			2. 	If it is an Undirected graph, then through an edge, we can go back and forth, so through DFS traversal of an Undirected graph, 
///				we can reach to a node's parent which is already visited. But in case of Directed graph, this will not happen until there is a directed edge
///				between the nodes.
///			3.  So, to determine whether cycle exists in the directed graph, we need to traverse through the graph and identify whether we can reach to an
///				already visited node in the current traversal.
///			4. 	In order to do it, we need to utilize two visited array, one will store all the visited nodes so far, those we will not process or traverse through
///				again, and the second one will store the nodes which has been visited in the current traversal, or more specifically in the current starting node of
///				all the graph components.
///			5. 	Every time, while all the adjacent nodes of a node have been traversed and through that node, we confirm that there is no cycle present, 
///				we need to mark the current node as unvisited in the second array (visitedInCurTraversal), so that for the next path, this node can be 
///				traversed again.
///			6. 	If we see for any adjacent node of a node is already visited (visited[adjacentNode]==true) and in current traversal as well, this adjacent node
///				is visited [visitedInCurTraversal[adjacentNode]==true], that means this adjacent node has already been traversed in the current traversal,
///				and again we reach to the node, so there is a cycle present.
///
/// Time Complexity: O(V+E), where V is the number of vertices and E is the Number of Edges [Since we are utilizing DFS traversal]
/// Space Complexity: O(V) for Visited array + O(V) for visitedInCurTraversal array = O(2V), where V is the number of vertices
/// Auxiliary Space Complexity: O(V), where V is the number of vertices [this is the space to maintain the recursive call].


class Solution {
    
    private boolean isCycleUtil(int node, ArrayList<ArrayList<Integer>> adj,
                                    boolean[] visited, boolean[] visitedInCurTraversal) {
                                        
        visited[node] = true;
        visitedInCurTraversal[node] = true;
        
        for(Integer adjNode : adj.get(node)) {
            
            if(!visited[adjNode] && isCycleUtil(adjNode, adj, visited, visitedInCurTraversal))
                return true;
            
            else if(visitedInCurTraversal[adjNode])
                return true;
        }
        
        visitedInCurTraversal[node] = false;
        return false;
                                            
    }
    
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        if(V<2)
            return false;
            
        boolean[] visited = new boolean[V];
        boolean[] visitedInCurTraversal = new boolean[V];
        
        //Since Graph may contain multiple components
        for(int i=0;i<V;i++) {
            
            if(!visited[i] && isCycleUtil(i, adj, visited, visitedInCurTraversal))
                return true;
        }
        
        return false;
    }
}