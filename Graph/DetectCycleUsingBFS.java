/// Detect whether Undirected Graph contains cycle using BFS algorithm
/// Concept:
///			1.	During traversal, if a node is already visited, and we reach to that node again, that means there are at least two paths through which
///				we can reach to the same node [Since each node is traversed once only, same path we do not traverse more than once]. It means there might be a cycle.
///			2.	Since, we are considering undirected graph, if there is an edge between u to v, the same edge exists between v to u. So, if we are at node u, 
///				mark node v as visited and node u is already visited (since we are already processing Node u), again while we're at node v and we can reach to node u, 
///				which is already visited. But it doesn't mean that there is a cycle between node u and v.
///			3. 	So, simple intuition is, if we find an adjacent node which is visited already but this is the same node from where current processing node has been
///				traversed, then that will not be considered as cycle, otherwise there is a cycle. 
///			2.	Every time while we are visiting some node, we are storing the node and it's parent node, means from where the node is getting traversed.
///				So, when an adjacent node we found as already visited, then we will check if the adjacent node is not the parent node of the current node.
///				If not, then there is a cycle.
/// Time Complexity: O(V+E), where V is number of vertices and E is number of Edges [We are using BFS traversal]
/// Space Complexity: O(V) for V vertices + O(V) for the queue



class Node {
    
    private int val;
    private int parentVal;
    
    public Node(int val, int parentVal) {
        
        this.val = val;
        this.parentVal = parentVal;
    }
    
    public int getVal(){ 
        
        return this.val; 
    }
    
    public int getParentVal() {
        
        return this.parentVal;
    }
}

class Solution {
    
    private boolean isCycleUtil(Node node, ArrayList<ArrayList<Integer>> adj,
                                    boolean[] visited) {
                                        
        Queue<Node> bfsQueue = new LinkedList<>();
        bfsQueue.offer(node);
        visited[node.getVal()] = true;
        
        while(!bfsQueue.isEmpty()) {
            
            Node tempNode = bfsQueue.poll();
            int val = tempNode.getVal();
            int parentVal = tempNode.getParentVal();
            
            for(Integer adjNodeVal : adj.get(val)) {
                
                if(!visited[adjNodeVal]){
                    
                    bfsQueue.offer(new Node(adjNodeVal, val));
                    visited[adjNodeVal] = true;   
                }
                
                else {
                    
                    if(adjNodeVal != parentVal)
                        return true;
                }
            }
        }
        
        return false;
        
    }
    
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        if(V<=2)
            return false;
        
        boolean[] visited = new boolean[V];
        
        // Since graph can contain multiple components
        for(int i=0;i<V;i++) {
            
            if(!visited[i] && isCycleUtil(new Node(i, -1), adj, visited))
                return true;
        }
        
        return false;
    }
}