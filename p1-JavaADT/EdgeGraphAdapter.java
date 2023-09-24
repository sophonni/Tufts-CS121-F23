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

            /* ensure that edge between srcNode and dstNode doesn't already exist in the graph */
            if (!this.g.hasEdge(srcNode, dstNode))
            {
                /* automatically add both source node and destination node to the graph */
                this.g.addNode(srcNode);
                this.g.addNode(dstNode);

                /* add th edge to the graph */
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
        String srcNode = e.getSrc();
        String dstNode = e.getDst();
    
        if (this.g.hasEdge(srcNode, dstNode)) {
            this.g.removeEdge(srcNode, dstNode);
    
            List<String> succOfDstNode = this.g.succ(dstNode);
            List<String> predOfDstNode = this.g.pred(dstNode);
            
            /* check if the destination node of the removed edge is connected
            to any other nodes or if there are node/s connecting to it */
            if (succOfDstNode.isEmpty() && predOfDstNode.isEmpty())
            {
                /* remove the destination node if it's isolated i.e. meaning
                there are no node connected to it AND there are no node that
                it's connected to */
                this.g.removeNode(dstNode);
            }
            
            List<String> succOfSrcNode = this.g.succ(srcNode);
            List<String> predOfSrcNode = this.g.pred(srcNode);
            
            /* check if the source node of the removed edge is connected
            to any other nodes or if there are node/s connecting to it */
            if (succOfSrcNode.isEmpty() && predOfSrcNode.isEmpty())
            {
                /* remove the source node if it's isolated i.e. meaning
                there are no node connected to it AND there are no node that
                it's connected to */
                this.g.removeNode(srcNode);
            }
    
            /* edge successfully removed */
            return true;
        }
    
        return false; // Edge not found
    }
    

    public List<Edge> outEdges(String n)
    {
        List<Edge> edgesWithGivenNodeAsSource = new ArrayList<Edge>();

        /* get all nodes with an edge from the given node */
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

        /* get all nodes with an edge to the given node */
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

        /* get all available nodes from the graph */
        List<String> allNodes = this.g.nodes();

        for (String currentNode : allNodes)
        {
            /* get all edges of current node */
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
        
        /* graph to store all the nodes and edges of the given Edge Graph */
        Graph nodesAndEdgesOfG = new ListGraph();

        /* get all the edges of the given Edge Graph and put them in a list
        for iterating purposes */
        List<Edge> edgesOfG = g.edges();

        /* iterate through all the edges of the given Edge Graph */
        for (Edge edge : edgesOfG)
        {
            /* add both the source node and destination node to the graph
            containign all nodes and edges of the given Edge Graph */
            nodesAndEdgesOfG.addNode(edge.getSrc());
            nodesAndEdgesOfG.addNode(edge.getDst());

            /* add and edge between the source node and destination node to
            the same graph */
            nodesAndEdgesOfG.addEdge(edge.getSrc(), edge.getDst());
        }

        /* combine all nodes and edges between current Edge Graph and the
        given Edge Graph */
        Graph combineNodesAndEdges = this.g.union(nodesAndEdgesOfG);

        EdgeGraph combineEdges = new EdgeGraphAdapter(combineNodesAndEdges);
        
        return combineEdges;
    }

    public boolean hasPath(List<Edge> e)
    {

        if (e == null)
        {
            return false;
        }

        /* every graph includes the empty path */
        if (e.isEmpty())
        {
            return true;
        }

        /* ensure that all of the given edges exist in the graph */
        for (Edge edge : e)
        {
            // if (!this.g.hasEdge(edge.getSrc(), edge.getDst()))
            // {
            //     return false;
            // }
            if (!this.hasEdge(edge))
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
                /* get the destination node of the current edge */
                String currDstNode = currEdge.getDst();

                Edge nextEdge = e.get(i + 1);

                /* get the soruce node of the next edge */
                String nextSrcNode = nextEdge.getSrc();

                /* ensure that current node of the current edge is connected to the source node of the next edge */
                if (currDstNode != nextSrcNode)
                {
                    throw new BadPath();
                }
            }
        }
        return true;
    }

}
