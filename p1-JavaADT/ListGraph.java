import java.util.*;

public class ListGraph implements Graph {
        private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

        /**********************************************************************
        * Purpose: add a node to current graph                                *
        * Input: a string representing the data of the node to be added       *
        * Output:                                                             *
        *      - TRUE if a node is successfully added                         *
        *      - FALSE if the given node is already exist in the current      *
        *      graph                                                          *
        **********************************************************************/
        public boolean addNode(String n)
        {
            boolean result = false;

            /* only add a node to the graph if given string is non-null and
            non-empty */
            if (n != null && !n.isEmpty())
            {
                /* ensure that the node doesn't exist in the graph prior to
                adding 'n' to the graph */
                if (!this.nodes.containsKey(n))
                {
                    /* add new node to the graph */
                    this.nodes.put(n, new LinkedList<String>());
                    
                    /* verify that newly added node exists in the graph */
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

        /**********************************************************************
        * Purpose: add an edges between two nodes that exist in the current   *
        *          graph                                                      *
        * Input:                                                              *
        *       - n1 which is a string representing the data of the           *
        *       source node                                                   *
        *       - n2 which is a string representing the data of the           *
        *       destination node                                              *
        * Output:                                                             *
        *      - TRUE if an edges between two nodes was successfully added    *
        *      to the current graph                                           *
        *      - FALSE if an edge between the two nodes is currently exist    *
        *      in the current graph                                           *
        **********************************************************************/
        public boolean addEdge(String n1, String n2)
        {
            Boolean result = false;

            /* check if both nodes exist in the graph */
            if (!this.nodes.containsKey(n1) || !this.nodes.containsKey(n2)) {
                throw new NoSuchElementException();
            }
        
            /* attempt to add an edge from n1 to n2 */
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
            
        /**********************************************************************
        * Purpose: check if the given node is currently exist in the current  *
        *          graph                                                      *
        * Input:                                                              *
        *       - n which is a string representing the data of the node to    *
        *       look for                                                      *
        * Output:                                                             *
        *      - TRUE if the given node is currently exist in the current     *
        *      graph                                                          *
        *      - FALSE if the given node is currently doesn't exist in the    *
        *      current graph                                                  *
        **********************************************************************/
        public boolean hasNode(String n)
        {
            /* check if node 'n' exist in the graph */
            return (this.nodes.containsKey(n)) ? true : false;
        }

        /**********************************************************************
        * Purpose: check if an edge between the two given node is currently   *
        *          exist in the current graph                                 *
        * Input:                                                              *
        *       - n1 which is a string representing the data of the           *
        *       source node                                                   *
        *       - n2 which is a string representing the data of the           *
        *       destination node                                              *
        * Output:                                                             *
        *      - TRUE if an edges between two nodes is currently exist in     *
        *      the current graph                                              *
        *      - FALSE if an edges between two nodes is currently doesn't     *
        *      exist in the current graph                                     *
        **********************************************************************/
        public boolean hasEdge(String n1, String n2)
        {
            boolean result = false;

            boolean graphContainN1 = hasNode(n1);
            boolean graphContainN2 = hasNode(n2);
            
            /* ensure that both 'n1' and 'n2' exist in the graph prior to
            checking their edge */
            if (graphContainN1 && graphContainN2)
            {
                /* check if there is already exist an edge between the
                two nodes */
                result = (this.nodes.get(n1).contains(n2)) ? true : false;
            }
            else
            {
                result = false;
            }

            return result;
        }

        /**********************************************************************
        * Purpose: remove all edges of the given node, remove the given node  *
        *          from the current graph, and remove all edges from other    *
        *          nodes to the given node                                    *
        * Input:                                                              *
        *       - n which is a string representing the data of the node to    *
        *       be remove                                                     *
        * Output:                                                             *
        *      - TRUE if the given node is successfully removed from the      *
        *      current graph                                                  *                                            *
        *      - FALSE if the given node is unsuccessfully removed from the   *
        *      current graph                                                  *
        **********************************************************************/
        public boolean removeNode(String n)
        {
            boolean result = false;

            if (hasNode(n))
            {
                /* remove all edges from node 'n' to other nodes */
                for (String edgeOfGivenNode : this.nodes.get(n))
                {
                    /* remove the current edge from node 'n' */
                    result = removeEdge(n, edgeOfGivenNode);
                    if (!result)
                    {
                        return result;
                    }
                }

                /* remove node 'n' from the graph */
                this.nodes.remove(n);
                    
                for (String currNode : nodes.keySet()) {
                    LinkedList<String> currNodeEdges = nodes.get(currNode);
                    
                    int i = 0;
                    while (i < currNodeEdges.size()) {
                        String edgeOfCurrentNode = currNodeEdges.get(i);
                
                        /* remove an edge from current node to node 'n' */
                        if (edgeOfCurrentNode.equals(n))
                        {
                            currNodeEdges.remove(i);
                        }
                        else
                        {
                            /* move to the next edge */
                            i++;
                        }
                    }
                }

                /* verify that node 'n' has been removed from graph */
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

            /* get all edges of node 'n1' */
            LinkedList allEdgesOfN1 = this.nodes.get(n1);

            if (graphContainN1 && graphContainN2)
            {
                /* ensure that node 'n1' has an edge to node 'n2' prior to removing the edge from node 'n1' to node 'n2' */
                if (allEdgesOfN1.contains(n2))
                {
                    /* remove the edge from node 'n1' to node 'n2' */
                    allEdgesOfN1.remove(n2);

                    /* verify that the edge from node 'n1' to node 'n2' has been removed */
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

            /* iterate throw all nodes in the hashmap */
            for (String currNode : nodes.keySet())
            {
                /* add each node from graph to an array */
                allNodesInGraph.add(currNode);
            }
            return allNodesInGraph;
        }

        public List<String> succ(String n)
        {
            boolean hasEdgeFromGivenNode = false;
            List<String> nodesWithEdgeFromGivenNode = new ArrayList<String>();

            /* ensure that node 'n' exist in the graph */
            if (hasNode(n))
            {
                /* iterate through all nodes in the hashmap */
                for (String currNode : this.nodes.keySet())
                {
                    /* check if there an edge from node 'n' to current node */
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

            /* ensure that node 'n' exist in the graph */
            if (hasNode(n))
            {
                /* iterate through all nodes in the hashmap */
                for (String currNode : nodes.keySet())
                {
                    /* check if there an edge from current to node 'n' */
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
                /* return the current graph if g is null */
                return this;
            }
    
            /* create a new graph to store the union */
            Graph newGraph = new ListGraph();
    
            /* add all nodes from the current graph into new graph*/
            for (String currentNode : this.nodes.keySet())
            {
                newGraph.addNode(currentNode);
            }

            
            /* add all nodes from given graph into new graph */
            List<String> allNodesOfGivenGraph = g.nodes();
            for (String currentNode : allNodesOfGivenGraph)
            {
                newGraph.addNode(currentNode);
            }
            
            /* iterate through all the added node and add their edges */
            List<String> allCombineNodes = newGraph.nodes();
            for (String currentNode : allCombineNodes)
            {
                /* add all edges of current node from the current graph into the new graph */
                if (this.hasNode(currentNode))
                {
                    List<String> currNodeEdgesOfCurrGraph = this.succ(currentNode);
                    for (String currGraphCurrNodeEdge : currNodeEdgesOfCurrGraph)
                    {
                        newGraph.addEdge(currentNode, currGraphCurrNodeEdge);
                    }
                }

                /* add all edges of the current node from the given graph into the new graph */
                if (g.hasNode(currentNode))
                {
                    List<String> currNodeEdgesOfGivenGraph = g.succ(currentNode);
                    for (String givenGraphCurrNodeEdge : currNodeEdgesOfGivenGraph)
                    {
                        newGraph.addEdge(currentNode, givenGraphCurrNodeEdge);
                    }
                }
            }
    
            return newGraph;
        }

        public Graph subGraph(Set<String> nodes)
        {
            Graph subGraph = null;
        
            /* ensure that given set is not null nor empty */
            if (nodes != null && !nodes.isEmpty())
            {
                /* create a new subgraph */
                subGraph = new ListGraph();
        
                /* iterate through the given set of nodes */
                for (String currentNode : nodes)
                {
                    /* check if the current graph has the current node in the given set */
                    if (this.hasNode(currentNode) && nodes.contains(currentNode)) {

                        /* add the current node to the new graph */
                        subGraph.addNode(currentNode);
        
                        /* get all edges of the current node from the current graph */
                        List<String> currNodeEdges = this.succ(currentNode);
        
                        /* iterate through all the edges of the current node */
                        for (String currNodeEdge : currNodeEdges)
                        {
                            /* check if the edge's destination node is in the set of nodes */
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
                /* set subGraph to null if nodes are empty or null */
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

            /* use to keep track of nodes that need to be visited */
            Set<String> visitedNodes = new HashSet<>();

            /* use to keep track of nodes that have already been visited */
            Queue<String> nodesToVisit = new LinkedList<>();

            /* visit 'n1' node as starting point */
            nodesToVisit.add(n1);
            visitedNodes.add(n1);

            /* traverse through all the edges of all nodes that needs to be visited */
            while (!nodesToVisit.isEmpty())
            {
                /* get the current node off the 'nodesToVisit' queue and save it */
                String currentNode = nodesToVisit.poll();

                /* return true if the popped node from the queue is the target node */
                if (currentNode.equals(n2))
                {
                    return result = true;
                }
                else
                {
                    /* get a list of all the edges of the current visiting node */
                    List<String> currNodeEdges = this.succ(currentNode);

                    /* iterate through the list and add nodes that have not
                    been visited to the 'nodesToVisit' queue */
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
