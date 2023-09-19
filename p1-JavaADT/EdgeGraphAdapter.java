import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

        private Graph g;

        public EdgeGraphAdapter(Graph g) { this.g = g; }

        public boolean addEdge(Edge e)
        {
            Boolean result = false;
            if (e != null)
            {
                String srcNode = e.getSrc();
                String dstNode = e.getDst();

                // Ensure that edge between srcNode and dstNode doesn't already exist in the graph
                if (!this.g.hasEdge(srcNode, dstNode))
                {
                    // System.out.println("src: " + srcNode);       //SD TODO: to be remove before submitting
                    // System.out.println("dest: " + dstNode);

                    // Automatically add both source node and destination ndoe to the graph
                    this.g.addNode(srcNode);
                    this.g.addNode(dstNode);

                    // add th edge to the graph
                    return result = this.g.addEdge(srcNode, dstNode);      //SD TODO: refer back to part two. Not really sure about the 'Note' section.
                }
                else
                {
                    return result = false;
                }
            }
            return result;
        }

        public boolean hasNode(String n)
        {
            return this.g.hasNode(n);
        }

        public boolean hasEdge(Edge e)
        {

            String srcNode = e.getSrc();
            String dstNode = e.getDst();

            return this.g.hasEdge(srcNode, dstNode);
        }

        public boolean removeEdge(Edge e)
        {
                Boolean result = false;
                String srcNode = e.getSrc();
                String dstNode = e.getDst();

                if (this.g.hasEdge(srcNode, dstNode))
                {
                    this.g.removeEdge(srcNode, dstNode);

                    // Get all edges of the source node
                    List<String> srcNodeEdges = this.g.succ(srcNode);

                    // Check if the removed edge node was the last edge of
                    // the source node
                    if (srcNodeEdges.size() == 0)
                    {
                        // Remove source node after removing its last
                        // edge
                        this.g.removeNode(srcNode);

                        // Get a list of all available nodes
                        List<String> allNodes = this.g.nodes();

                        // Remove the eddge of all nodes to the source
                        // node that has already been removed due to
                        // the removal of its last edge
                        for (String currNode : allNodes)
                        {
                            if (this.g.hasEdge(currNode, srcNode))
                            {
                                this.g.removeEdge(currNode, srcNode);
                            }
                        }
                    }
                    result = true;

                }
                else
                {
                    result = false;
                }
                return result;
        }

        public List<Edge> outEdges(String n)
        {
            List<Edge> edgesWithGivenNodeAsSource = new ArrayList<Edge>();

            // Get all nodes with an edge from the given node
            List<String> nodesWithEdgeFromGivenNode = this.g.succ(n);

            for (String currentNode : nodesWithEdgeFromGivenNode)
            {
                Edge e = new Edge(n, currentNode);
                edgesWithGivenNodeAsSource.add(e);
            }

            return edgesWithGivenNodeAsSource;
        }
        

        public List<Edge> inEdges(String n)
        {
            List<Edge> edgesWithGivenNodeAsDest = new ArrayList<Edge>();

            // Get all nodes with an edge to the given node
            List<String> nodesWithEdgeToGivenNode = this.g.pred(n);

            for (String currentNode : nodesWithEdgeToGivenNode)
            {
                Edge e = new Edge(currentNode, n);
                edgesWithGivenNodeAsDest.add(e);
            }

            return edgesWithGivenNodeAsDest;
        }

        public List<Edge> edges()
        {
            List<Edge> allEdges = new ArrayList<Edge>();

            // Get all available nodes from the graph
            List<String> allNodes = this.g.nodes();

            for (String currentNode : allNodes)
            {
                // Get all edges of current node
                List<String> currNodeEdges = this.g.succ(currentNode);

                for (String currNodeEdge : currNodeEdges)
                {
                    Edge e = new Edge(currentNode, currNodeEdge);
                    allEdges.add(e);
                }
            }
            return allEdges;
        }

        public EdgeGraph union(EdgeGraph g)
        {
            if (g == null)
            {
                return this;
            }
            
            // Graph to store all the nodes and edges of the given
            // Edge Graph
            Graph nodesAndEdgesOfG = new ListGraph();

            // Get all the edges of the given Edge Graph and put them in a
            // list for iterating purposes
            List<Edge> edgesOfG = g.edges();

            // Iterate through all the edges of the given Edge Graph
            for (Edge edge : edgesOfG)
            {
                // Add both the source node and destination node to
                // the graph containign all nodes and edges of the
                // given Edge Graph
                nodesAndEdgesOfG.addNode(edge.getSrc());
                nodesAndEdgesOfG.addNode(edge.getDst());

                // Add and edge between the source node and
                // destination node to the same graph
                nodesAndEdgesOfG.addEdge(edge.getSrc(), edge.getDst());
            }

            // combine all nodes and edges between current Edge Graph and
            // the given Edge Graph
            Graph combineNodesAndEdges = this.g.union(nodesAndEdgesOfG);

            EdgeGraph combineEdges = new EdgeGraphAdapter(combineNodesAndEdges);
            
            return combineEdges;
        }

        public boolean hasPath(List<Edge> e)
        {

            if (e == null || e.isEmpty())
            {
                return false;
            }

            // Ensure that all of the given edges exist in the graph
            for (Edge edge : e)
            {
                if (!this.g.hasEdge(edge.getSrc(), edge.getDst()))
                {
                    return false;
                }
            }

            /*
            * A: B, C, D
            * B: C, A
            * D: B
            * 
            * edges: AB, AC, AD, BC, BA, DB
            */

            for (int i = 0; i < e.size() - 1; i++)
            {
                Edge currEdge = e.get(i);
                
                if (i != e.size())
                {
                    // Get the destination node of the current edge
                    String currDstNode = currEdge.getDst();

                    Edge nextEdge = e.get(i + 1);

                    // Get the soruce node of the next edge
                    String nextSrcNode = nextEdge.getSrc();

                    // Ensure that current node of the current
                    // edge is connected to the source node of the
                    // next edge
                    if (currDstNode != nextSrcNode)
                    {
                        throw new BadPath();
                    }

                }
            }

            return true;
        }

}
