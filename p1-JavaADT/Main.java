import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main
{

        // Run "java -ea Main" to run with assertions enabled (If you run
        // with assertions disabled, the default, then assert statements
        // will not execute!)
        
        //     public static void test1() {
        // 	Graph g = new ListGraph();
        // 	assert g.addNode("a");
        // 	assert g.hasNode("a");
        //     }

        //     public static void test2() {
        // 	Graph g = new ListGraph();
        // 	EdgeGraph eg = new EdgeGraphAdapter(g);
        // 	Edge e = new Edge("a", "b");
        // 	assert eg.addEdge(e);
        // 	assert eg.hasEdge(e);
        //     }


        /**********************************************************************
        *           Shared Test Cases                                         *
        **********************************************************************/
        public static void test1()
        {
                Graph g = new ListGraph();
        
                g.addNode("a");
                g.addNode("b");
                g.addNode("c");
                g.addEdge("a", "b");
                g.addEdge("b", "c");
        
                assert g.connected("a", "b");
                assert g.connected("a", "c");
                assert !g.connected("c", "a");
        
                try {
                    g.connected("a", "d");
                    assert false;
                } catch (Exception e)
                {
                    assert true;
                }
        }
        public static void test2()
        {
                Graph g = new ListGraph();
                String n1 = "a";
                String n2 = "b";
                String n3 = "c";
                String n4 = "d";
                assert g.addNode(n1);
                assert g.addNode(n2);
                assert g.addNode(n3);
                assert g.addNode(n4);
                assert g.addEdge(n2, n1);
                assert g.addEdge(n3, n1);
                assert g.addEdge(n4, n1);
                assert g.addEdge(n1, n2);
                List<String> pred = g.pred(n1);
                assert (pred.size() == 3);
        }
        public static void test3()
        {
                Graph g = new ListGraph();
                g.addNode("a");
                g.addNode("b");
                g.addEdge("a", "b");
                assert g.hasNode("a");
                assert g.hasNode("b");
                assert g.hasEdge("a", "b");
                assert !g.hasEdge("b", "a");
                assert !g.addNode("a");
                assert !g.addEdge("a", "b");
                try {
                    g.addEdge("c", "d");
                } catch (Exception e)
                {
                    assert e instanceof NoSuchElementException;
                }
        
                assert g.removeEdge("a", "b");
                assert !g.removeEdge("a", "b");
                try {
                    g.removeEdge("c", "d");
                } catch (Exception e)
                {
                    assert e instanceof NoSuchElementException;
                }
        
                assert g.nodes().size()==2;
        
                g.addNode("c");
                g.addNode("d");
                g.addNode("e");
                g.addEdge("c", "d");
                g.addEdge("c", "e");
                g.addEdge("e", "c");
                assert g.succ("c").size()==2;
                assert g.succ("d").size()==0;
                try {
                    g.succ("f");
                } catch (Exception e)
                {
                    assert e instanceof NoSuchElementException;
                }
        }

        public static void test4()
        {
                Graph g = new ListGraph();
                assert g.addNode("a");
                assert g.addNode("b");
                assert g.hasNode("a");
                assert g.hasNode("b");
                assert !(g.addNode("a"));
                assert !(g.addNode("b"));
                
                assert g.addEdge("a", "b");
                assert g.hasEdge("a", "b");
                assert !(g.addEdge("a", "b"));
                
                assert !(g.hasEdge("b", "a"));
                
                assert g.removeNode("b");
                assert !(g.hasNode("b"));
                
                List<String> successors = g.succ("a");
                assert (successors.size() == 0);
        }

        public static void test5()
        {
                Graph g = new ListGraph();
        
                assert !g.hasNode("a");
                assert g.addNode("a");
                assert g.hasNode("a");
                assert g.addNode("b");
                assert g.hasNode("b");
        
                assert !g.hasEdge("a", "b");
                assert g.addEdge("a", "b");
                assert g.hasEdge("a", "b");
                assert !g.hasEdge("b", "a");
        
                assert g.removeNode("b");
                assert !g.hasNode("b");
                assert !g.hasEdge("a", "b");
        }

        public static void test6()
        {
            Graph g = new ListGraph();
        
            try
            {
                g.addEdge("a", "b");
            } catch(Exception e)
            {
                assert (e instanceof NoSuchElementException);
            }
        
            g.addNode("a");
        
            try
            {
                g.addEdge("a", "b");
            } catch(Exception e)
            {
                assert (e instanceof NoSuchElementException);
            }
        
            g.addNode("b");
        
            try
            {
                g.addEdge("c", "b");
            } catch(Exception e)
            {
                assert (e instanceof NoSuchElementException);
            }
        }

        public static void test7()
        {
            Graph g = new ListGraph();
            List<String> successorList = new ArrayList<>();
        
            assert g.addNode("a");
            assert g.addNode("b");
            assert g.addNode("c");
        
            assert successorList.containsAll(g.pred("a"));
            assert successorList.containsAll(g.pred("b"));
            assert successorList.containsAll(g.pred("c"));
        
            assert g.addEdge("a", "b");
            assert g.addEdge("b", "c");
            assert g.addEdge("c", "a");
        
            successorList.add("a");
            assert successorList.containsAll(g.pred("b"));
        
            assert g.addEdge("c", "b");
            successorList.add("c");
            assert successorList.containsAll(g.pred("b"));
        
            assert g.addEdge("b", "b");
            successorList.add("b");
            assert successorList.containsAll(g.pred("b"));
            
        }
        
        public static void test8()
        {
            // Setup
            Graph g = new ListGraph();
            
            // Act and Assert
            boolean result = false;
            try
            {
                result = g.addNode(null);
            } catch (IllegalArgumentException e)
            {
                result = false;
            }
            
            assert !result;
            
            try
            {
                result = g.addNode("");
            } catch (IllegalArgumentException e)
            {
                result = false;
            }
            
            assert !result;
        }

        public static void test9()
        {
            Graph g = new ListGraph();
            assert g.addNode("a");
            assert g.addNode("b");
            assert g.addEdge("a", "b");
            assert g.addNode("c");
            assert g.addEdge("b", "c");
            List<String> successors = new ArrayList<String>(2);
            successors.add("b");
            successors.add("c");
            assert successors.containsAll(g.succ("a"));
        }

        public static void test10()
        {
            Graph g = new ListGraph();

            g.addNode("A");
            g.addNode("B");
            g.addNode("C");

            g.addEdge("A", "B");
            g.addEdge("A", "C");
            
            List<String> ll = g.succ("A");
            assert ll.size() == 2;

            // for (String s : ll) {
            //         // System.out.println(s); // ["B", "C"]
            // }

            try
            {
                g.succ("M");
                assert false;
            }
            catch(NoSuchElementException err)
            {
                assert true;
            }
        }

        public static void test11()
        {
            // Remove node not in graph to start with
            Graph notInGraph = new ListGraph();

            assert !notInGraph.removeNode("Does Not Exist");

            assert notInGraph.addNode("In Graph");
            assert notInGraph.removeNode("In Graph");

            // Removing a cut vertex makes the vertices not connected
            Graph cutVertexGraph = new ListGraph();
            
            // Make the initial vertices
            assert cutVertexGraph.addNode("left");
            assert cutVertexGraph.addNode("right");
            assert cutVertexGraph.addNode("middle");
            
            // left -> middle -> right
            assert cutVertexGraph.addEdge("left", "middle");
            assert cutVertexGraph.addEdge("middle", "right");

            // confirm that they are connected
            assert cutVertexGraph.connected("left", "right");

            // remove the middle, so left and right should be disconnected now
            assert cutVertexGraph.removeNode("middle");

            // Confirm the deletions worked properly
            assert !cutVertexGraph.connected("left", "right");
            assert !cutVertexGraph.hasNode("middle");
            assert !cutVertexGraph.hasEdge("left", "middle");
            assert !cutVertexGraph.hasEdge("middle", "right");

            // Removing a vertex in a cycle keeps the vertices connected
            Graph cycleGraph = new ListGraph();

            // Make the initial vertices
            assert cycleGraph.addNode("a");
            assert cycleGraph.addNode("b");
            assert cycleGraph.addNode("c");
            assert cycleGraph.addNode("d");

            // Graph:
            // a -> b
            // ^    |
            // |    v
            // d <- c
            assert cycleGraph.addEdge("a", "b");
            assert cycleGraph.addEdge("b", "c");
            assert cycleGraph.addEdge("c", "d");
            assert cycleGraph.addEdge("d", "a");

            // confirm that they are connected
                    // can find more than one away, in both directions
            assert cycleGraph.connected("a", "c"); 
            assert cycleGraph.connected("c", "a");
                    // can find one away, in both directions (since cycle)
            assert cycleGraph.connected("a", "b");
            assert cycleGraph.connected("b", "a");

            // Now:
            // a -> b
            //      |
            //      v
            //      c
            assert cycleGraph.removeNode("d");

                    // Only one way now
            assert cycleGraph.connected("a", "c");
            assert !cycleGraph.connected("c", "a");
            assert cycleGraph.connected("a", "b");
            assert !cycleGraph.connected("b", "a");
        }

        public static void test12()
        {
            ListGraph graph = new ListGraph();
            graph.addNode("A");
            graph.addNode("B");
            graph.addEdge("A", "B");

            // Edge present
            try
            {
                assert graph.hasEdge("A","B");
                // System.out.println("Edge Present: Passed");
            } catch (AssertionError e)
            {
                // System.out.println("Assertion failed: Edge present check");
            }

            // Edge not present
            try
            {
                assert graph.hasEdge("B","A");
            }
            catch (AssertionError e)
            {
                // System.out.println("graph.hasEdge(\"B\",\"A\");");
                // System.out.println("Edge Present: Failed (as it should)");
            }

            // Edge with null source
            try
            {
                assert graph.hasEdge(null,"B");
            }
            catch (AssertionError e)
            {
                // System.out.println("graph.hasEdge(null,\"B\");");
                // System.out.println("Edge Present: Failed (as it should)");
            }

            // Edge with null destination
            try
            {
                assert graph.hasEdge("A",null);
            } catch (AssertionError e)
            {
                // System.out.println("graph.hasEdge(\"A\",null);");
                // System.out.println("Edge Present: Failed (as it should)");
            }

            // Edge with empty source
            try
            {
                assert graph.hasEdge("    ","B");
            }
            catch (AssertionError e)
            {
                // System.out.println("graph.hasEdge(\"    \",\"B\");");
                // System.out.println("Edge Present: Failed (as it should)");
            }

            // Edge with empty destination
            try
            {
                assert graph.hasEdge("A","    ");
            }
            catch (AssertionError e)
            {
                // System.out.println("graph.hasEdge(\"A\",\"    \");");
                // System.out.println("Edge Present: Failed (as it should)");
            }
        }

        public static void test13()
        {
            Graph g = new ListGraph();
            EdgeGraph eg = new EdgeGraphAdapter(g);
            Edge e = new Edge("x", "y");
            assert eg.addEdge(e);
            assert eg.removeEdge(e);
            assert eg.edges().isEmpty(); // Validate remove method works and edges() is updated appropriately             
        }

        /**********************************************************************
        *                  Add Nodes                                          *
        **********************************************************************/
        public static void addNodeTest()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.hasNode("A") == false;
            assert g.addNode("A") == true;
            assert g.hasNode("A") == true;

            assert g.addNode("") == false;
            assert g.addNode(null) == false;
        }

        /**********************************************************************
        *                               Add Edges                             *
        **********************************************************************/
        public static void addEdgeTest1()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.hasEdge("A", "B") == false;
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.hasEdge("A", "B") == false;
            assert g.addEdge("A", "B") == true;
            assert g.hasEdge("A", "B") == true;
        }


        public static void addEdgeTest2()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.hasNode("A") == false;
            assert g.addNode("A") == true;
            assert g.hasNode("A") == true;
        }

        /**********************************************************************
        *                        Remove Nodes                                 * 
        **********************************************************************/
        public static void removeNodeTest()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("E") == true;
            assert g.addNode("F") == true;


            assert g.hasEdge("A", "B") == false;
            assert g.hasEdge("B", "C") == false;
            assert g.hasEdge("C", "D") == false;
            assert g.hasEdge("D", "E") == false;
            assert g.hasEdge("E", "F") == false;
            assert g.hasEdge("F", "A") == false;

            assert g.addEdge("A", "B") == true;

            assert g.addEdge("B", "A") == true;
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("B", "D") == true;
            assert g.addEdge("B", "E") == true;
            assert g.addEdge("B", "F") == true;

            assert g.addEdge("C", "A") == true;
            assert g.addEdge("C", "B") == true;
            assert g.addEdge("C", "D") == true;
            assert g.addEdge("C", "E") == true;
            assert g.addEdge("C", "F") == true;
            
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("D", "B") == true;
            assert g.addEdge("D", "C") == true;
            assert g.addEdge("D", "E") == true;
            assert g.addEdge("D", "F") == true;

            assert g.hasEdge("B", "A") == true;
            assert g.hasEdge("B", "C") == true;
            assert g.hasEdge("B", "D") == true;
            assert g.hasEdge("B", "E") == true;
            assert g.hasEdge("B", "F") == true;

            assert g.hasEdge("C", "A") == true;
            assert g.hasEdge("C", "B") == true;
            assert g.hasEdge("C", "D") == true;
            assert g.hasEdge("C", "E") == true;
            assert g.hasEdge("C", "F") == true;
            
            assert g.hasEdge("D", "A") == true;
            assert g.hasEdge("D", "B") == true;
            assert g.hasEdge("D", "C") == true;
            assert g.hasEdge("D", "E") == true;
            assert g.hasEdge("D", "F") == true;

            assert g.removeNode("A") == true;

            assert g.hasNode("A") == false;

            assert g.hasEdge("B", "A") == false;
            assert g.hasEdge("C", "A") == false;
            assert g.hasEdge("D", "A") == false;
            assert g.hasEdge("E", "A") == false;
            assert g.hasEdge("F", "A") == false;
        }

        /**********************************************************************
        *                  All Nodes in List                                  *
        **********************************************************************/
        public static void nodesListTest()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("E") == true;
            assert g.addNode("F") == true;

            assert g.hasNode("A") == true;
            assert g.hasNode("B") == true;
            assert g.hasNode("C") == true;
            assert g.hasNode("D") == true;
            assert g.hasNode("E") == true;
            assert g.hasNode("F") == true;

            List<String> allNodesList = g.nodes();
            assert allNodesList != null;
            assert allNodesList.size() == 6;
        }

        /**********************************************************************
        *           List of All Nodes That Given Node is Connected T          *
        **********************************************************************/
        public static void successcorListTest()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("E") == true;
            assert g.addNode("F") == true;
            
            assert g.addEdge("A", "B") == true;
            assert g.addEdge("A", "C") == true;
            assert g.addEdge("A", "D") == true;
            assert g.addEdge("A", "E") == true;
            assert g.addEdge("A", "F") == true;
            
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("B", "D") == true;
            
            assert g.addEdge("C", "A") == true;
            
            assert g.hasNode("A") == true;
            
            
            List<String> fromAToOthers = g.succ("A");
            assert fromAToOthers.size() == 5;
            
            List<String> fromBToOthers = g.succ("B");
            assert fromBToOthers.size() == 2;
            
            List<String> fromCToOthers = g.succ("C");
            assert fromCToOthers.size() == 1;
        }

        /**********************************************************************
        *           List of All Nodes That Are Connected to The Given Node    *
        **********************************************************************/
        public static void predecessorListTest()
        {
            Graph g = new ListGraph();
            assert g != null;
            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("E") == true;
            assert g.addNode("F") == true;

            assert g.addEdge("B", "A") == true;
            assert g.addEdge("C", "A") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("E", "A") == true;
            assert g.addEdge("F", "A") == true;
            
            assert g.addEdge("B", "C") == true;
            //assert g.addEdge("B", "C") == false;  //SD TODO: this test throws an exception which is expected

            assert g.addEdge("C", "E") == true;
            assert g.addEdge("C", "F") == true;

            List<String> fromOtherToA = g.pred("A");
            assert fromOtherToA.size() == 5;

            List<String> fromOtherToB = g.pred("B");
            assert fromOtherToB.size() == 0;
            
            List<String> fromOtherToC = g.pred("C");
            assert fromOtherToC.size() == 1;
        }

        /**********************************************************************
        *           List of All Nodes That Are Connected to The Given Node    *
        **********************************************************************/
        public static void unionTest()
        {
            Graph g = new ListGraph();
            assert g != null;

            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("E") == true;
            assert g.addNode("F") == true;

            assert g.addEdge("A", "B") == true;

            assert g.addEdge("B", "A") == true;
            assert g.addEdge("C", "A") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("E", "A") == true;
            assert g.addEdge("F", "A") == true;
            
            assert g.addEdge("B", "C") == true;
            //assert g.addEdge("B", "C") == false;

            assert g.addEdge("C", "E") == true;
            assert g.addEdge("C", "F") == true;

            
            Graph h = new ListGraph();
            assert h.addNode("W") == true;
            assert h.addNode("X") == true;
            assert h.addNode("Y") == true;
            assert h.addNode("A") == true;
            
            
            assert h.addEdge(("W"), "X") == true;
            assert h.addEdge(("W"), "Y") == true;
            assert h.addEdge(("X"), "W") == true;
            assert h.addEdge(("X"), "Y") == true;
            assert h.addEdge(("Y"), "A") == true;
            
            assert h.addEdge(("A"), "W") == true;
            assert h.addEdge(("A"), "X") == true;
            assert h.addEdge(("A"), "Y") == true;

            Graph t = g.union(h);
            assert t.hasNode("W") == true;
            assert t.hasNode("X") == true;
            assert t.hasNode("Y") == true;
            assert t.hasNode("B") == true;
            assert t.hasEdge("A", "Y") == true;
            //assert t.hasEdge("A", "B") == true;
        }

        /**********************************************************************
        *           Combine All Node From Two Graphs Into One Graph           *
        **********************************************************************/
        public static void unionSimpleTest()
        {
            Graph g = new ListGraph();
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addEdge("A", "B") == true;

            Graph h = new ListGraph();
            assert h.addNode("C") == true;
            assert h.addNode("D") == true;
            assert h.addEdge("C", "D") == true;

            Graph k = g.union(h);
            assert k.hasNode("A");
            assert k.hasNode("B");
            assert k.hasNode("C");
            assert k.hasNode("D");
        }

        public static void subGraphTest()
        {
            Graph g = new ListGraph();
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addEdge("A", "B") == true;
            assert g.addEdge("A", "D") == true;
            assert g.addEdge("D", "B") == true;

            assert g.hasNode("A") == true;
            assert g.hasNode("D") == true;


            Set<String> mySet = new HashSet<>();

            mySet.add("A");
            mySet.add("E");
            mySet.add("D");

            Graph i = g.subGraph(mySet);
            assert i.hasEdge("A", "B") == false;
            assert i.hasEdge("A", "D") == true;
            // assert i.hasEdge("D", "B") == true;
            // assert i.hasNode("E") == false;
            // assert i.hasEdge("E", "K") == false;
            // List<String> subGraph = i.nodes();
            // assert subGraph.contains("A") == true;
            // assert subGraph.contains("B") == true;
            // assert subGraph.contains("D") == true;
            // assert i.hasEdge("A", "B") == true;
            // assert i.hasNode("D") == true;
            // assert i.hasEdge("D", "B") == true;      
        }

        public static void simpleSubGraphTest()
        {
            Graph g = new ListGraph();
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;

            assert g.addEdge("A", "B") == true;
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("C", "A") == true;

            Set<String> mySet = new HashSet<>();
            mySet.add("A");
            mySet.add("B");

            Graph i = g.subGraph(mySet);
            assert i.hasEdge("B", "C") == false;
            assert i.hasNode("A") == true;
            assert i.hasNode("B") == true;
            assert i.hasEdge("B", "C") == false;
            assert i.hasEdge("A", "B") == true;
        }
    
        public static void main(String[] args)
        {
            //test1();

            //PART 1
            addNodeTest();
            addEdgeTest1();
            addEdgeTest2();
            removeNodeTest();
            nodesListTest();
            successcorListTest();
            predecessorListTest();
            unionSimpleTest();
            unionTest();
            subGraphTest();
            simpleSubGraphTest();

            //OTHERS' Tests
            test1();
            test2();
            test3();
            test4();
            test5();
            test6();
            test7();
            test8();
            test9();
            test10();
            test11();
            test12();
            test13();

            //PART 2
            addEdgeTest();
            hasEdgeTest();
            removeEdgeTest();
            outEdgesTest();
            inEdgesTest();
            unionEdgeGraphTest();
            hasPathTest();
        }

        public static void addEdgeTest()
        {
            Graph g = new ListGraph();
            Edge e1 = new Edge("A", "B");
            //Edge e2 = new Edge("", "B");
            Edge e3 = new Edge("C", "F");


            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("") == false;

            
            assert g.addEdge("A", "B") == true;
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("B", "A") == true;
            assert g.addEdge("C", "B") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("D", "D") == true;
            
            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.addEdge(e1) == false;
            assert ega.addEdge(null) == false;
            assert ega.addEdge(e3) == true;
            assert ega.hasNode("F") == true;
            

            //assert ega.addEdge(e2) == false; //SD TODO: addNode in ListGraph might want to through an exception when trying to add an empty string node
        }

        public static void hasEdgeTest()
        {
            Graph g = new ListGraph();
            Edge e1 = new Edge("A", "B");
            //Edge e2 = new Edge("", "B");
            Edge e3 = new Edge("C", "F");
            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("") == false;

            
            assert g.addEdge("A", "B") == true;
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("B", "A") == true;
            assert g.addEdge("C", "B") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("D", "D") == true;

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.addEdge(e1) == false;
            assert ega.addEdge(null) == false;
            assert ega.hasNode("F") == false;
            assert ega.hasEdge(e1) == true;
            assert ega.hasEdge(e3) == false;
            assert ega.addEdge(e3) == true;
            assert ega.hasEdge(e3) == true;
            assert ega.hasNode("F") == true;
        }

        public static void removeEdgeTest()
        {
            Graph g = new ListGraph();
            //Edge e1 = new Edge("A", "B");
            //Edge e2 = new Edge("", "B");
            Edge e3 = new Edge("C", "B");
            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("") == false;

            
            assert g.addEdge("A", "B") == true;
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("B", "A") == true;
            assert g.addEdge("C", "B") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("D", "D") == true;

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.hasNode("C") == true;
            assert ega.hasEdge(e3) == true;
            assert ega.removeEdge(e3) == true;
            assert ega.hasEdge(e3) == false;
            assert ega.hasNode("C") == false;
        }

        public static void outEdgesTest()
        {
            Graph g = new ListGraph();
            Edge e1 = new Edge("A", "B");
            //Edge e2 = new Edge("", "B");
            Edge e3 = new Edge("C", "B");
            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;
            assert g.addNode("") == false;

            
            assert g.addEdge("B", "C") == true;
            assert g.addEdge("C", "B") == true;
            assert g.addEdge("D", "A") == true;
            assert g.addEdge("D", "D") == true;

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.hasNode("A") == true;
            assert ega.hasNode("B") == true;
            assert ega.hasNode("C") == true;
            assert ega.hasNode("D") == true;
            assert ega.addEdge(e1) == true;
            assert ega.hasNode("C") == true;
            assert ega.hasEdge(e3) == true;
            assert ega.removeEdge(e3) == true;
            assert ega.hasEdge(e3) == false;
            assert ega.hasNode("C") == false;

            Edge e5 = new Edge("A", "C");
            Edge e6 = new Edge("A", "D");

            assert ega.addEdge(e5) == true;
            assert ega.addEdge(e6) == true;

            List<Edge> edgesStartWithA = ega.outEdges("A");

            Edge e4 = edgesStartWithA.get(0);
            assert e4.getSrc() == "A";
            assert e4.getDst() == "B";

            Edge e7 = edgesStartWithA.get(1);
            assert e7.getDst() == "C";

            Edge e8 = edgesStartWithA.get(2);
            assert e8.getDst() == "D";
        }

        public static void inEdgesTest()
        {
            Graph g = new ListGraph();
            //Edge e1 = new Edge("A", "B");
            Edge e2 = new Edge("B", "C");
            Edge e3 = new Edge("A", "C");
            Edge e4 = new Edge("C", "B");
            //Edge e7 = new Edge("D", "C");
            
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;

            assert g.addEdge("D", "C");

            
            assert g.addEdge("A", "B") == true;
            //assert g.addEdge("B", "C") == true;

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.hasNode("A") == true;
            assert ega.hasNode("B") == true;
            assert ega.hasNode("C") == true;

            assert ega.addEdge(e3) == true;
            assert ega.addEdge(e4) == true;
            assert ega.addEdge(e2) == true;

            List<Edge> edgesStartWithA = ega.inEdges("C");
            // System.out.println("Size: " + edgesStartWithA.size());

            Edge e5 = edgesStartWithA.get(0);
            assert e5.getSrc() == "A";
            assert e5.getDst() == "C";

            Edge e6 = edgesStartWithA.get(1);
            assert e6.getSrc() == "B";
            assert e6.getDst() == "C";

            Edge e8 = edgesStartWithA.get(2);
            assert e8.getSrc() == "D";
            assert e8.getDst() == "C";      //SD TODO: added edge is not stored in order of when each edges have been added

            //Edge e6 = edgesStartWithA.get(1);
            // System.out.println(e6.getSrc());
            // System.out.println(e6.getDst());

            // Edge e7 = edgesStartWithA.get(1);
            // assert e4.getSrc() == "A";
            // assert e7.getDst() == "C";
        }

        public static void unionEdgeGraphTest()
        {
            Graph g = new ListGraph();
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;

            assert g.addEdge("A", "B") == true;
            assert g.addEdge("A", "C") == true;

            Edge e = new Edge("A", "D");

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            assert ega.addEdge(e) == true;

            Graph h = new ListGraph();
            assert h.addNode("W") == true;
            assert h.addNode("X") == true;
            assert h.addNode("Y") == true;
            assert h.addNode("Z") == true;

            assert h.addEdge("X", "Y") == true;
            assert h.addEdge("Z", "W") == true;
            EdgeGraphAdapter ega2 = new EdgeGraphAdapter(h);

            Edge e2 = new Edge("X", "Y");

            EdgeGraph combine = ega.union(ega2);
            assert combine.hasNode("A") == true;
            assert combine.hasNode("X") == true;
            assert combine.hasEdge(e) == true;
            assert combine.hasEdge(e2) == true;
        }

        public static void hasPathTest()
        {
            Graph g = new ListGraph();
            assert g.addNode("A") == true;
            assert g.addNode("B") == true;
            assert g.addNode("C") == true;
            assert g.addNode("D") == true;

            assert g.addEdge("A", "B") == true;

            EdgeGraphAdapter ega = new EdgeGraphAdapter(g);
            
            Edge e = new Edge("B", "C");
            assert ega.addEdge(e) == true;

            Edge e1 = new Edge("C", "D");
            assert ega.addEdge(e1) == true;

            List<Edge> edges = ega.edges();

            assert ega.hasPath(edges) == true;
        }

}