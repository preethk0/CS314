import java.util.Arrays;
import java.util.List;

/*  Student information for assignment:
 *
 *  On my honor, Preeth Kanamangala, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: pk9297
 *  email address: preeth@utexas.edu
 *  Grader name: Amir
 *  Number of slip days I am using:
 */

/*
 * Question. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results:
 *
 * The unexpected results that occur are that some of our high predicted ranks are closer to the middle
 * or bottom of the list of actual ranks. If we look closer, we notice that these high predicted ranks
 * are from teams that are either D2 or D3. Thus, we can see that the problem is that D2 and D3 teams that win
 * lots of games are ranked similarly to D1 teams. Since teams only play teams in their division, our program
 * thinks that D2 and D3 teams that win lots of games are actually as good as D1 teams that win lots
 * of games. From real life observations, we know this is not the case. The likely cause of this is
 * because we don't have a way to assign a weight to the division that a team is in. 
 *
 * Suggest another way of method of ranking teams using the results
 * from the graph. Thoroughly explain your method. The method can build
 * on one of the three existing algorithms.
 *
 * Another method of ranking teams could build off of the current weighted graph method. The goal is to account
 * for the division that a college is in. Thus, in addition to scores, we want edge cost to also take into account
 * the division. This can be done having the division of team as a variable and adding specific fixed cost to the weight of
 * an edge based on what division a team is.
 * 
 * So if a D1 team were to beat another D1 team, the edge cost would be higher than if a D3 team were
 * to beat another D3 team by the same score differential. This logically makes sense because a win for the D1 team
 * should be "worth" more since they are playing at a higher skill level. Similarly, if a D1 team were to beat a D3 team,
 * the win shouldn't be "worth" that much since it should be the case that a D1 team beats a D3 team.
 */

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)  {
        graphTests();

        String actual = "2008ap_poll.txt";
        String gameResults = "div12008.txt";

        FootballRanker ranker = new FootballRanker(gameResults, actual);

        ranker.doUnweighted(true);
        ranker.doWeighted(true);
        ranker.doWeightedAndWinPercentAdjusted(true);

        System.out.println();
        doRankTests(ranker);

        System.out.println();

    }

    // tests on various simple Graphs
    private static void graphTests() {
        System.out.println("PERFORMING TESTS ON SIMPLE GRAPHS\n");
        dijkstraTests();
    }

    private static void dijkstraTests() {

        int testNumber = 0;
        Graph g;
        List<String> result, expected;

        test("Dijkstra on small, undirected, and \"unweighted\" graph");

        g = getGraph(new String[][] { //
            { "John", "Tim", "1" }, //
            { "John", "Gary", "1" }, //
            { "Tim", "Gary", "1" }, //
            { "Bob", "Gary", "1" }, //
        }, false);

        g.dijkstra("John");

        result = g.findPath("John");
        expected = Arrays.asList(new String[] { "John" });
        assertEqual(g.getNumEdgesFromStart("John"), 0, ++testNumber, "John is 0 edges away");
        assertEqual(result, expected, ++testNumber, "John to John");
        assertEqual(g.getWeightedCostFromStart("John"), 0d, ++testNumber, "John to John costs nothing");

        result = g.findPath("Tim");
        expected = Arrays.asList(new String[] { "John", "Tim" });
        assertEqual(g.getNumEdgesFromStart("Tim"), 1, ++testNumber, "Tim is 1 edges away");
        assertEqual(result, expected, ++testNumber, "John to neighbor");
        assertEqual(g.getWeightedCostFromStart("Tim"), 1d, ++testNumber, "John to neighbor cost");

        result = g.findPath("Gary");
        expected = Arrays.asList(new String[] { "John", "Gary" });
        assertEqual(g.getNumEdgesFromStart("Gary"), 1, ++testNumber, "Gary is 1 edges away");
        assertEqual(result, expected, ++testNumber, "John to neighbor");
        assertEqual(g.getWeightedCostFromStart("Gary"), 1d, ++testNumber, "John to neighbor cost");

        result = g.findPath("Bob");
        expected = Arrays.asList(new String[] { "John", "Gary", "Bob" });
        assertEqual(g.getNumEdgesFromStart("Bob"), 2, ++testNumber, "Bob is 2 edges away");
        assertEqual(result, expected, ++testNumber, "John to Bob neighbor");
        assertEqual(g.getWeightedCostFromStart("Bob"), 2d, ++testNumber, "John to not near neighbor cost");
        g.findAllPaths(false);
        assertEqual(g.getDiameter(), 2, ++testNumber, "Diameter test");
        assertEqual(g.costOfLongestShortestPath(), 2.0, ++testNumber, "Longest path test");
        
        test("Dijkstra on small, undirected, and weighted graph");

        g = getGraph(new String[][] { //
            { "John", "Tim", "1" }, //
            { "Tim", "Gary", "1" }, //
            { "Gary", "Bob", "1" }, //
            { "Bob", "Sam", "1" }, //
            { "Sam", "Jerry", "1" }, //
            { "Jerry", "Adam", "1" }, //
            { "John", "Gary", "50" }, //
            { "John", "Bob", "50" }, //
            { "John", "Sam", "50" }, //
            { "John", "Jerry", "3" }, //
            { "John", "Adam", "50" }, //
        }, false);

        g.dijkstra("John");

        result = g.findPath("Tim");
        expected = Arrays.asList(new String[] { "John", "Tim" });
        assertEqual(g.getNumEdgesFromStart("Tim"), 1, ++testNumber, "Tim is 1 edge away");
        assertEqual(result, expected, ++testNumber, "John to Tim");
        assertEqual(g.getWeightedCostFromStart("Tim"), 1d, ++testNumber, "John to Tim small cost");

        result = g.findPath("Gary");
        expected = Arrays.asList(new String[] { "John", "Tim", "Gary" });
        assertEqual(g.getNumEdgesFromStart("Gary"), 2, ++testNumber, "Gary is 2 edges away");
        assertEqual(result, expected, ++testNumber, "John to Gary takes longer route, less cost");
        assertEqual(g.getWeightedCostFromStart("Gary"), 2d, ++testNumber, "John to Gary small cost");

        result = g.findPath("Bob");
        expected = Arrays.asList(new String[] { "John", "Tim", "Gary", "Bob" });
        assertEqual(g.getNumEdgesFromStart("Bob"), 3, ++testNumber, "Bob is 3 edges away");
        assertEqual(result, expected, ++testNumber, "John to Bob takes longer route, less cost");
        assertEqual(g.getWeightedCostFromStart("Bob"), 3d, ++testNumber, "John to Bob small cost");

        result = g.findPath("Sam");
        expected = Arrays.asList(new String[] { "John", "Jerry", "Sam" });
        assertEqual(g.getNumEdgesFromStart("Sam"), 2, ++testNumber, "Sam is 2 edges away");
        assertEqual(result, expected, ++testNumber, "John to Sam has equal cost route, selects fewest nodes");
        assertEqual(g.getWeightedCostFromStart("Sam"), 4d, ++testNumber, "John to Sam small cost");

        result = g.findPath("Jerry");
        expected = Arrays.asList(new String[] { "John", "Jerry" });
        assertEqual(g.getNumEdgesFromStart("Jerry"), 1, ++testNumber, "Jerry is 1 edge away");
        assertEqual(result, expected, ++testNumber, "John to Jerry takes direct route");
        assertEqual(g.getWeightedCostFromStart("Jerry"), 3d, ++testNumber, "John to Jerry small cost");

        result = g.findPath("Adam");
        expected = Arrays.asList(new String[] { "John", "Jerry", "Adam" });
        assertEqual(g.getNumEdgesFromStart("Adam"), 2, ++testNumber, "Adam is 2 edges away");
        assertEqual(result, expected, ++testNumber, "John to Adam uses shortcut");
        assertEqual(g.getWeightedCostFromStart("Adam"), 4d, ++testNumber, "John to Adam small cost");
        g.findAllPaths(true);
        assertEqual(g.getDiameter(), 3, ++testNumber, "Diameter test");
        assertEqual(g.costOfLongestShortestPath(), 5.0, ++testNumber, "Longest path test");
      }

      /**
       * tests equivalence between two instances of Object and prints result to
       * System.out
       */
      private static void assertEqual(Object given, Object expected, int testNumber, String message) {
        boolean passed = given.equals(expected);
        String passFailOutput = passed ? "PASSED" : "FAILED";

        System.out.printf("%s Test %d: %s%n", passFailOutput, testNumber, message);
        if (!passed)
          System.out.printf("\tEXPECTED %s; RECIEVED %s%n", expected, given);
      }

      /**
       * print testing header
       */
      private static void test(String what) {
        System.out.printf("\nNOW TESTING %s:\n\n", what);
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

    // Test the FootballRanker on the given file.
    private static void doRankTests(FootballRanker ranker) {
        System.out.println("\nTESTS ON FOOTBALL TEAM GRAPH WITH FootBallRanker CLASS: \n");
        double actualError = ranker.doUnweighted(false);
        if (actualError == 13.7) {
            System.out.println("Passed unweighted test");
        } else {
            System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 13.7, actual: " + actualError);
        }

        actualError = ranker.doWeighted(false);
        if (actualError == 12.6) {
            System.out.println("Passed weigthed test");
        } else {
            System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 12.6, actual: " + actualError);
        }


        actualError = ranker.doWeightedAndWinPercentAdjusted(false);
        if (actualError == 6.3) {
            System.out.println("Passed unweighted win percent test");
        } else {
            System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 6.3, actual: " + actualError);
        }
    }
}