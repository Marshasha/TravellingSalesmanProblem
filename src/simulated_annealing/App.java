package simulated_annealing;

public class App {

    public static void main(String []args){

        SimulatedAnnealing sa = new SimulatedAnnealing();

        for(int i=0; i<500; i++){
            City city = new City();
            Repository.addCity(city);
        }
        sa.simulate();

        System.out.println("The best solution: " + sa.getBest().calculateTourDistance());
        System.out.println(sa.getBest());
    }
}
