package simulated_annealing;

public class SimulatedAnnealing {

    private SingleTour actualState;
    private SingleTour nextState;
    private SingleTour bestState;

    public void simulate(){
        double temperature = Constants.MAX_TEMPERATURE;

        actualState = new SingleTour();
        actualState.generateTour();
        bestState = new SingleTour(actualState.getTour());

        System.out.println("Initial solution distance: " + actualState.calculateTourDistance());

        while(temperature > Constants.MIN_TEMPERATURE){

            nextState = generateAnotherTour(actualState);

            double currentDistance = actualState.calculateTourDistance();
            double neighbourDistance = nextState.calculateTourDistance();

            if(acceptanceProbability(currentDistance, neighbourDistance, temperature) > Math.random()) {// if we get 1, next root becomes actual root
                actualState = nextState;
                currentDistance = neighbourDistance;
            }


            if(currentDistance < bestState.calculateTourDistance()){
                bestState = actualState; // here we stock the best distance
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
        return bestState;
    }

    private double acceptanceProbability(double currentEnergy, double neighbourEnergy, double temperature){

        if(neighbourEnergy < currentEnergy ) return 1.0; // because we look for the shortest path

        // otherwise we use the Metropolis function
        double exp = Math.exp((currentEnergy - neighbourEnergy) / temperature);
    //    System.out.println(" acceptance probability = ((" + currentEnergy + " - " + neighbourEnergy + ") / " + temperature + ") = "  + exp);

        return exp; // (currentDistance-neighbourDistance)/temperature
    }
}
