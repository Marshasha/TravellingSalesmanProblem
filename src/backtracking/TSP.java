package backtracking;

import java.util.ArrayList;
import java.util.List;

public class TSP {

    // The matrix presentation of the Graph(city,path)
    private int [][] graph;
    private boolean [] visited;
    private List<Integer> path;
    private int min; // we look for minimum hamiltonian cycle

    public TSP(int [][] graph){
        this.graph=graph;
        this.visited = new boolean[graph.length];
        this.path = new ArrayList<>();

        initialize();
    }

    private void initialize(){
        visited[0]=true; // start with index 0, city A
        path.add(0);
        min = Integer.MAX_VALUE; // all values are < MAX_VALUE
    }

    private boolean isValid(int city, int position){
        // if we have visited already this city
        if(visited[city])
            return false;

        // if there is a connection between 2 cities
        if(graph[position][city] == 0)
            return false;

        return true;
    }

    public void find(int position, int count, int cost){

        //we return when we visited all cities only ones
        // and the last city is the same as the first one
        if(count==graph.length && graph[position][0] != 0){
            path.add(0); // example : 0 1 2 3 0 we add 0 to make sure that we came back to the same city
            path.forEach(num -> System.out.print(num + " "));
            System.out.println("\nThe number of cities : " + (cost + graph[position][0] + "\n"));

            //to make sure that we make minimum route
            if(cost + graph[position][0]<min)
                min = cost + graph[position][0];

            path.remove(path.size()-1); // we remove 0 which was added at the beginning of the function
            return;
        }

        for(int i=0; i<graph.length; i++){
            if(isValid(i, position)){
                visited[i]=true;
                path.add(i);
                find(i, count + 1, cost + graph[position][i]);

                // backtrack
                visited[i] = false;
                path.remove(path.size()-1);
            }
        }
    }

    public void print(){
        System.out.println("Min hamiltonian cycle: " + min);
    }
}
