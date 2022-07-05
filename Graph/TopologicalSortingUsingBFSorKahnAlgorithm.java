/*
	Topological Sorting using BFS traversal, aka Kahn's algorithm
	
	Concepts:
		1. 	The ordering of vertices in a directed Acyclic Graph is told to be in Topological ordering if and only if the below condition satisfies
				for every edge u -> v, u must appear before v in the ordering
				
		2. The graph must be Directed Acyclic Graph. 
			a. In case of Undirected graph, if there is an edge u -- v, it is not possible to put u before v and v before u in the same ordering.
			b. In case of Cyclic graph, if the edge sequences are u -> v, v -> w and w -> u, we can put u before v, v before w, but in the same 
				time w can not appear before u.

		3. One Directed Acyclic Graph may contain multiple Topological ordering.

		4. The intuition is
			a.	Make an InDegree array for each vertices. Initially, if a vertex has indegree is equal to Zero, means there is no vertex u for vertex v,
				such that u -> v exists. And in this case, if no u->v exists for vertex v, we can put v in the beginning of the Topological ordering.
			b. 	While processing vertex with indegree zero, we will decrease the indegree of all the adjacent vertices by one, since vertex v appears
				already in the topological ordering before the adjacent nodes, (more specifically the v->w edge has already considered and put v in the topoSort
				ordering).
					Whenever any adjacent node's indegree value becomes zero, we will put it into queue, since all the parent vertices of that node	
					have been appeared already in the ordering. 

	Time complexity: O(V+E) for BFS traversal + O(E) for creating indegree array + O(V) for getting the initial nodes having indegree zero, 
						where V is the number of Vertices and E is the number of Edges
						
	Space Complexity: O(V) for indegree array + O(V) for BFS Queue
*/

class Solution
{
    private static void topoSortUtil(ArrayList<ArrayList<Integer>> adj,
                    Queue<Integer> bfsQueue, int[] indegree, int[] topoSortArr) {
        
        int idx = 0;
        // Do until queue is empty              
        while(!bfsQueue.isEmpty()) {
            
            int node = bfsQueue.poll();
            topoSortArr[idx++] = node;
            
            for(Integer adjNode : adj.get(node)) {
                
                indegree[adjNode]--;
                if(indegree[adjNode]==0)
                    bfsQueue.offer(adjNode);
            }
        }
    }
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        if(V == 0)
            return new int[0];
        if(V==1)
            return new int[]{0};
            
        int[] indegree = new int[V];
        for(int i=0;i<V;i++) {
            for(Integer adjNode : adj.get(i))
                indegree[adjNode]++;
        }
        
        Queue<Integer> bfsQueue = new LinkedList<>();
        
        // Add all the nodes having indegree zero to the queue, since no node can appear before the nodes having indegree zero
        for(int node=0;node<V;node++)
            if(indegree[node]==0)
                bfsQueue.offer(node);
        
        int[] topoSortArr = new int[V];
        topoSortUtil(adj, bfsQueue, indegree, topoSortArr);
        
        return topoSortArr;
    }
}