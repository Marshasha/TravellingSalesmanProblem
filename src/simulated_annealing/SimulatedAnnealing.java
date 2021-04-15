package simulated_annealing;

public class SimulatedAnnealing {

    private SingleTour actualRoot;
    private SingleTour nextRoot;
    private SingleTour bestRoot;

    public void simulate(){
        double temperature = Constants.MAX_TEMPERATURE;

        actualRoot = new SingleTour();
        actualRoot.generateTour();
        bestRoot = new SingleTour(actualRoot.getTour());

        System.out.println("Initial solution distance: " + actualRoot.calculateTourDistance());

        while(temperature > Constants.MIN_TEMPERATURE){

            nextRoot = generateAnotherTour(actualRoot);

            double currentDistance = actualRoot.calculateTourDistance();
            double neighbourDistance = nextRoot.calculateTourDistance();

            if(acceptanceProbability(currentDistance, neighbourDistance, temperature) > Math.random()) {// if we get 1, next root becomes actual root
                actualRoot = nextRoot;
                currentDistance = neighbourDistance;
            }


            if(currentDistance < bestRoot.calculateTourDistance()){
                bestRoot = actualRoot; // here we stock the best distance
            }

            temperature *= 1 - Constants.COOLING_RATE; // here we manage the number of iterations

        }
    }

    // we swap 2 cities randomly
    private SingleTour generateAnotherTour(SingleTour actualState) {

        SingleTour newState = new SingleTour(actualState.getTour());

        int randomIndex1 = (int) (Math.random() * newState.getTourSize());
        int randomIndex2 = (int) (Math.random() * newState.getTourSize());

        City city1 = newState.getCity(randomIndex1);
        City city2 = newState.getCity(randomIndex2);

        newState.setCity(randomIndex1, city2);
        newState.setCity(randomIndex2, city1);

        return newState;
    }

    public SingleTour getBest(){
        return bestRoot;
    }

    private double acceptanceProbability(double currentDistance, double nextDistance, double temperature){

        if(nextDistance < currentDistance ) return 1.0; // because we look for the shortest path

        // otherwise we use the Metropolis function
        double exp = Math.exp((currentDistance - nextDistance) / temperature);
        System.out.println(" acceptance probability = ((" + currentDistance + " - " + nextDistance + ") / " + temperature + ") = "  + exp);

        return exp; // (currentDistance-neighbourDistance)/temperature
    }
}
