import java.util.*;

public class ListGraph implements Graph {
        private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

        /*
         * Purpose: add a node to current graph
         * Input: a string representing the data of the node to be added
         * Output:
         *      TRUE if a node is successfully added
         *      FALSE if the given node is already exist in the current graph
         */
        public boolean addNode(String n)
        {
            boolean result = false;

            /* Only add a node to the graph if given string is non-null
            and non-empty */
            if (n != null && !n.isEmpty())
            {
                /* Ensure that the node doesn't exist in the graph
                prior to adding 'n' to the graph */
                if (!this.nodes.containsKey(n))
                {
                    // Add new node to the graph */
                    this.nodes.put(n, new LinkedList<String>());
                    
                    // Verify that newly added node exists in
                    // the graph
                    if (this.nodes.containsKey(n))
                    {
                        result = true;
                    }
                    else
                    {
                        result = false;
                    }
                }
                else
                {
                    result = false;
                }
            }

            return result;
        }

        public boolean addEdge(String n1, String n2)
        {
            Boolean result = false;

            // Check if both nodes exist in the graph
            if (!this.nodes.containsKey(n1) || !this.nodes.containsKey(n2)) {
                throw new NoSuchElementException();
            }
        
            // Attempt to add an edge from n1 to n2
            if (!this.nodes.get(n1).contains(n2))
            {
                result = this.nodes.get(n1).add(n2);
            }
            else
            {
                result = false;
            }

            return result;
        }
            

        public boolean hasNode(String n)
        {
            // Check if node 'n' exist in the graph
            return (this.nodes.containsKey(n)) ? true : false;
        }

        public boolean hasEdge(String n1, String n2)
        {
            boolean result = false;

            boolean graphContainN1 = hasNode(n1);
            boolean graphContainN2 = hasNode(n2);
            // Ensure that both 'n1' and 'n2' exist in the graph prior to
            // checking their edge
            if (graphContainN1 && graphContainN2)
            {
                // Check if there is already exist an edge between
                //the two nodes
                result = (this.nodes.get(n1).contains(n2)) ? true : false;
            }
            else
            {
                result = false;
            }

            return result;
        }

        public boolean removeNode(String n)
        {
            boolean result = false;

            if (hasNode(n))
            {
                // Remove all edges of node 'n' to other nodes
                for (String edgeOfGivenNode : this.nodes.get(n))
                {
                    // Remove the current edge from node 'n'
                    result = removeEdge(n, edgeOfGivenNode);
                    if (!result)
                    {
                        return result;
                    }
                }

                // Remove node 'n' from the graph
                this.nodes.remove(n);
                    
                // Iterate through all nodes in the hashmap
                for (String currNode : nodes.keySet())
                {
                    LinkedList<String> currNodeEdges = nodes.get(currNode);

                    // Use an iterator for safe removal
                    Iterator<String> iterator = currNodeEdges.iterator();

                    while (iterator.hasNext())
                    {
                        String edgeOfCurrentNode = iterator.next();

                        // Remove node 'n' from current node's edge
                        if (edgeOfCurrentNode.equals(n))
                        {
                            // Safely remove the edge
                            iterator.remove();
                        }
                    }
                }

                // Verify that node 'n' has been removed from graph
                if (hasNode(n))
                {
                    result = false;
                }
                else
                {
                    result = true;
                }
            }
            return result;
        }

        public boolean removeEdge(String n1, String n2)
        {
            boolean result = false;

            boolean graphContainN1 = hasNode(n1);
            boolean graphContainN2 = hasNode(n2);

            // Get all edges of node 'n1'
            LinkedList allEdgesOfN1 = this.nodes.get(n1);

            if (graphContainN1 && graphContainN2)
            {
                // Ensure that node 'n1' has an edge to node 'n2'
                // prior to removing the edge from node 'n1' to
                // node 'n2'
                if (allEdgesOfN1.contains(n2))
                {
                    // Remove the edge from node 'n1' to node 'n2'
                    allEdgesOfN1.remove(n2);

                    // Verify that the edge from node 'n1' to
                    // node 'n2' has been removed
                    result = (this.nodes.get(n1).contains(n2)) ? false : true;
                }
                else
                {
                    result = false;
                }
            }
            else
            {
                throw new NoSuchElementException();
            }

            return result;
        }

        public List<String> nodes()
        {
            List<String> allNodesInGraph = new ArrayList<String>();

            // Iterate throw all nodes in the hashmap
            for (String currNode : nodes.keySet())
            {
                // Add each node from graph to an array
                allNodesInGraph.add(currNode);
            }
            return allNodesInGraph;
        }

        public List<String> succ(String n)
        {
            boolean hasEdgeFromGivenNode = false;
            List<String> nodesWithEdgeFromGivenNode = new ArrayList<String>();

            // Ensure that node 'n' exist in the graph
            if (hasNode(n))
            {
                // Iterate through all nodes in the hashmap
                for (String currNode : this.nodes.keySet())
                {
                    // Check if there an edge from node 'n' to
                    // current node
                    hasEdgeFromGivenNode = hasEdge(n, currNode);
                    if (hasEdgeFromGivenNode)
                    {
                        nodesWithEdgeFromGivenNode.add(currNode); 
                    }
                }
            }
            else
            {
                throw new NoSuchElementException();
            }

            return nodesWithEdgeFromGivenNode;
        }

        public List<String> pred(String n)
        {
            boolean hasEdgeToGivenNode = false;
            List<String> nodesWithEdgeToGivenNode = new ArrayList<String>();

            //Ensure that node 'n' exist in the graph
            if (hasNode(n))
            {
                // Iterate through all nodes in the hashmap
                for (String currNode : nodes.keySet())
                {
                    // Check if there an edge from current to
                    // node 'n'
                    hasEdgeToGivenNode = hasEdge(currNode, n);
                    if (hasEdgeToGivenNode)
                    {
                        nodesWithEdgeToGivenNode.add(currNode);
                    }
                }
            }
            else
            {
                throw new NoSuchElementException();
            }

            return nodesWithEdgeToGivenNode;
        }

        public Graph union(Graph g)
        {
            if (g == null)
            {
                    return this; // Return the current graph if g is null
            }
    
            // Create a new graph to store the union
            Graph newGraph = new ListGraph();
    
            // Add all nodes from the current graph
            for (String currentNode : this.nodes.keySet())
            {
                newGraph.addNode(currentNode);
            }
    
            // Add all nodes from graph 'g,' coalescing nodes with the
            // same name
            List<String> allNodesOfGivenGraph = g.nodes();
            for (String currentNode : allNodesOfGivenGraph)
            {
                if (!newGraph.hasNode(currentNode))
                {
                    newGraph.addNode(currentNode);
                }
            }
    
            // Add edges to all nodes in the union
            List<String> combinedNodes = newGraph.nodes();
            for (String currentNode : combinedNodes)
            {
                for (String edgeNode : combinedNodes)
                {
                    // Ensure not to add itself as its own edge
                    if (!currentNode.equals(edgeNode))
                    {
                        if (this.hasEdge(currentNode, edgeNode) || g.hasEdge(currentNode, edgeNode))
                        {
                            newGraph.addEdge(currentNode, edgeNode); //SD TODO: Might want to come back and check
                        }
                    }
                }
            }
    
            return newGraph;
        }

        public Graph subGraph(Set<String> nodes)
        {
            Graph subGraph = null;
        
            // Ensure that given set is not null nor empty
            if (nodes != null && !nodes.isEmpty())
            {
                // Create a new subgraph
                subGraph = new ListGraph();
        
                // Iterate through the given set of nodes
                for (String currentNode : nodes)
                {
                    // Check if the current graph has the current
                    // node in the given set
                    if (this.hasNode(currentNode) && nodes.contains(currentNode)) {

                        // Add the current node to the new
                        // graph
                        subGraph.addNode(currentNode);
        
                        // Get all edges of the current node
                        // from the current graph
                        List<String> currNodeEdges = this.succ(currentNode);
        
                        // Iterate through all the edges of the
                        // current node
                        for (String currNodeEdge : currNodeEdges)
                        {
                            // Check if the edge's destination
                            // node is in the set of nodes
                            if (nodes.contains(currNodeEdge))
                            {
                                subGraph.addNode(currNodeEdge);
                                subGraph.addEdge(currentNode, currNodeEdge);
                            }
                        }
                    }
                }
            }
            else
            {
                // Set subGraph to null if nodes are empty or null
                subGraph = null;
            }
            return subGraph;
        }
            

        public boolean connected(String n1, String n2)
        {
            Boolean result = false;

            if (!this.hasNode(n1) || ! this.hasNode(n2))
            {
                throw new NoSuchElementException();
            }

            // Use to keep track of nodes that need to be visited
            Set<String> visitedNodes = new HashSet<>();

            // Use to keep track of nodes that have already been visited
            Queue<String> nodesToVisit = new LinkedList<>();

            // Visit 'n1' node as starting point
            nodesToVisit.add(n1);
            visitedNodes.add(n1);

            // Traverse through all the edges of all nodes that needs
            // to be visited 
            while (!nodesToVisit.isEmpty())
            {
                // Get the current node off the 'nodesToVisit'
                // queue and save it
                String currentNode = nodesToVisit.poll();

                // Return true if the popped node from the queue is
                // the target node
                if (currentNode.equals(n2))
                {
                    return result = true;
                }
                else
                {
                    // Get a list of all the edges of the current
                    // visiting node
                    List<String> currNodeEdges = this.succ(currentNode);

                    // Iterate through the list and add nodes
                    //that have not been visited to the
                    // 'nodesToVisit' queue
                    for (String currNodeEdge : currNodeEdges)
                    {
                        if (!visitedNodes.contains(currNodeEdge))
                        {
                            nodesToVisit.add(currNodeEdge);
                            visitedNodes.add(currNodeEdge);
                        }
                    }
                }
            }

            return result;
        }
}
