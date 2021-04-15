package backtracking;

public class App {

    public static void main(String[] args){

        int [][] map = {{0,1,0,0,0,0,1,0}, // 8 cities = 8! = 40 320 paths
                        {1,0,1,0,0,0,0,0},
                        {0,1,0,1,0,0,0,0},
                        {0,0,1,0,1,0,0,1},
                        {0,0,0,1,0,1,0,1},
                        {0,0,0,0,1,0,1,1},
                        {1,0,0,0,0,1,0,1},
                        {0,0,0,1,1,1,1,0}};

        TSP walking = new TSP(map);
        walking.find(0,1,0);
        walking.print();
    }
}
