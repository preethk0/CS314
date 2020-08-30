import java.util.TreeSet;
public class EVERYTHINGSONFIRETester {
	
	public static int testNum = 1;
	public static int numRight = 0;
	public static int numWrong = 0;

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)  {
        graphTests();
        System.out.println("Test Completed\nTests passed: " + numRight + "\nTests failed: " + numWrong);
    }

    private static void graphTests() {
       	// Fully Connected Graph
        String [][] edges =  {{"A", "B", "2"},
                        {"A", "C", "5"},
                        {"A", "F", "13"},
                        {"B", "C", "7"},
                        {"B", "D", "6"},
                        {"B", "E", "5"},
                        {"C", "D", "6"},
                        {"C", "E", "1"},
                        {"D", "E", "1"},
                        {"D", "F", "3"},
                        {"E", "F", "6"}};
        Graph g = getGraph(edges, false);
        
        Object[][] expected = {{"A", 0.0, "[A]", 5, 30.0},
        		{"B", 2.0, "[A, B]", 5, 28.0},
        		{"C", 5.0, "[A, C]", 5, 19.0},
        		{"D", 7.0, "[A, C, E, D]", 5, 19.0},
        		{"E", 6.0, "[A, C, E]", 5, 17.0},
        		{"F", 10.0, "[A, C, E, D, F]", 5, 31.0}};       
        testGraph(g, true, "A", expected, 1, 10.0, 4);
        
       expected = new Object[][] {{"A", 0.0, "[A]", 5, 7.0},
        		{"B", 1.0, "[A, B]", 5, 6.0},
        		{"C", 1.0, "[A, C]", 5, 6.0},
        		{"D", 2.0, "[A, B, D]", 5, 6.0},
        		{"E", 2.0, "[A, B, E]", 5, 6.0},
        		{"F", 1.0, "[A, F]", 5, 7.0}};       
        testGraph(g, false, "A", expected, 1, 2.0, 2);
        
        // Two Unconnected Subgraphs
        edges = new String[][] {{"A", "B", "2"},
                {"A", "C", "4"},
                {"C", "B", "1"},
                {"D", "E", "3"},
                {"D", "F", "2"},
                {"E", "F", "6"}};
		g = getGraph(edges, false);
		g.addVertex("G");
		
		expected = new Object[][] {{"A", 0.0, "[A]", 2, 5.0},
				{"B", 2.0, "[A, B]", 2, 3.0},
				{"C", 3.0, "[A, B, C]", 2, 4.0},
				{"D", -1.0, "[]", 2, 5.0},
				{"E", -1.0, "[]", 2, 8.0},
				{"F", -1.0, "[]", 2, 7.0},
				{"G", -1.0, "[]", 0, 0.0}};       
		testGraph(g, true, "A", expected, 2, 5.0, 2);
		
		expected = new Object[][] {{"A", 0.0, "[A]", 2, 2.0},
			{"B", 1.0, "[A, B]", 2, 2.0},
			{"C", 1.0, "[A, C]", 2, 2.0},
			{"D", -1.0, "[]", 2, 2.0},
			{"E", -1.0, "[]", 2, 2.0},
			{"F", -1.0, "[]", 2, 2.0}, 
			{"G", -1.0, "[]", 0, 0.0}};  
		testGraph(g, false, "A", expected, 2, 1.0, 1);
		
        // Graph in a line
        edges = new String[][] {{"A", "B", "1"},
        		{"A", "G", "2"},
                {"B", "C", "14"},
                {"C", "D", "1"},
                {"D", "E", "3"},
                {"E", "F", "1"},
                {"F", "G", "3"}};
		g = getGraph(edges, false);
		g.addVertex("G");
		
		expected = new Object[][] {{"A", 10.0, "[C, D, E, F, G, A]", 6, 33.0},
				{"B", 11.0, "[C, D, E, F, G, A, B]", 6, 38.0},
				{"C", 0.0, "[C]", 6, 39.0},
				{"D", 1.0, "[C, D]", 6, 34.0},
				{"E", 4.0, "[C, D, E]", 6, 25.0},
				{"F", 5.0, "[C, D, E, F]", 6, 24.0},
				{"G", 8.0, "[C, D, E, F, G]", 6, 27.0}};       
		testGraph(g, true, "C", expected, 3, 11.0, 6);
		
		expected = new Object[][] {{"A", 2.0, "[C, B, A]", 6, 12.0},
			{"B", 1.0, "[C, B]", 6, 12.0},
			{"C", 0.0, "[C]", 6, 12.0},
			{"D", 1.0, "[C, D]", 6, 12.0},
			{"E", 2.0, "[C, D, E]", 6, 12.0},
			{"F", 3.0, "[C, D, E, F]", 6, 12.0},
			{"G", 3.0, "[C, B, A, G]", 6, 12.0}};       
		testGraph(g, false, "C", expected, 3, 3.0, 3);
		
        // Simple directed graph
        edges = new String[][] {{"A", "B", "3.2"},
                {"A", "D", "5.2"},
                {"B", "D", "1.3"},
                {"C", "A", "5.6"},
                {"E", "C", "3.2"},
                {"E", "D", "1.4"}};
		g = getGraph(edges, true);
		g.addVertex("G");
		
		expected = new Object[][] {{"A", 0.0, "[A]", 2, 7.7},
				{"B", 3.2, "[A, B]", 1, 1.3},
				{"C", -1.0, "[]", 3, 24.5},
				{"D", 4.5, "[A, B, D]", 0, 0.0},
				{"E", -1.0, "[]", 4, 25.4}};       
		testGraph(g, true, "A", expected, 4, 12.0, 3);
		
		expected = new Object[][] {{"A", 0.0, "[A]", 2, 2.0},
			{"B", 1.0, "[A, B]", 1, 1.0},
			{"C", -1.0, "[]", 3, 5.0},
			{"D", 1.0, "[A, D]", 0, 0.0},
			{"E", -1.0, "[]", 4, 7.0}};       
		testGraph(g, false, "A", expected, 4, 3.0, 3);
		
        // Bi-directional(?) directed graph
        edges = new String[][] {{"A", "B", "7"},
                {"A", "C", "3"},
                {"B", "A", "2"},
                {"B", "C", "1"},
                {"B", "D", "1"},
                {"C", "A", "5"},
                {"C", "B", "2"},
                {"C", "D", "6"}};
		g = getGraph(edges, true);
		g.addVertex("G");
		
		expected = new Object[][] {{"A", 0.0, "[A]", 3, 14.0},
				{"B", 5.0, "[A, C, B]", 3, 4.0},
				{"C", 3.0, "[A, C]", 3, 9.0},
				{"D", 6.0, "[A, C, B, D]", 0, 0.0}};    
		testGraph(g, true, "A", expected, 5, 6.0, 3);
		
		expected = new Object[][] {{"A", 0.0, "[A]", 3, 4.0},
			{"B", 1.0, "[A, B]", 3, 3.0},
			{"C", 1.0, "[A, C]", 3, 3.0},
			{"D", 2.0, "[A, B, D]", 0, 0.0}};    
		testGraph(g, false, "A", expected, 5, 2.0, 2);
    }
    
    private static void testGraph(Graph g, boolean weighted, String start, Object[][] expected, int graphNum, double expectLongest, int expectDiameter) {
    	String detail = weighted ? "(Weighted)" : "(Unweighted)";
    	System.out.println("///// Testing Graph #" + graphNum + " " + detail + " \\\\\\\\\\");
    	g.findAllPaths(weighted);
    	TreeSet<AllPathsInfo> allVertPaths = g.getAllPaths();
    	if (weighted) {
    		g.dijkstra(start);
    	} else {
    		g.findUnweightedShortestPath(start);
    	}
    	for (Object[] elems : expected) {
    		String vert = (String) elems[0];
    		System.out.println("--- Testing Vertex " + vert + " ---");

    		checkSame("Weights of vertex", "Weight", (Double) elems[1], g.getWeightedCostFromStart(vert));
    		checkSame("Path to vertex", "Path", (String) elems[2], g.findPath(vert).toString());
    		
    		AllPathsInfo vertPaths = getVertexPathInfo(vert, allVertPaths);
    		if (vertPaths != null) {
    			checkSame("Total paths with vertex", "amount", (Integer) elems[3], vertPaths.getNumPaths());
    			checkSame("Total paths costs with vertex", "costs", (Double) elems[4], vertPaths.getTotalCost());
    		} else {
    			System.out.println("No adjacent vertices for " + vert + ".");
    		}
    	}
    	System.out.println("--- Testing Overall ---");
    	checkSame("Longest path of graph", "longest", expectLongest, g.costOfLongestShortestPath());
    	checkSame("Diameter of graph", "diameter", expectDiameter, g.getDiameter());
    	System.out.println();
    }
    
    private static AllPathsInfo getVertexPathInfo(String name, TreeSet<AllPathsInfo> paths) {
    	for (AllPathsInfo path : paths) {
    		if (path.getName().equals(name)) {
    			return path;
    		}
    	}
    	return null;
    }
    
    private static void checkSame(String phrase, String word, Object expect, Object actual) {
		System.out.print("Test " + testNum++ + " - " + phrase + ": ");
		if (expect.equals(actual)) {
			numRight++;
			System.out.println("Passed. " + word + ": " + expect);
		} else {
			numWrong++;
			System.out.println("!~!FAILED!~!. Expected " + word + ": " + expect + " | Actual " + word + ": " + actual);
		}
    }
    
    // return a Graph based on the given edges
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }
}
