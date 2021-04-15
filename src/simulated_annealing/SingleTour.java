package simulated_annealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleTour {

    private List<City> tour = new ArrayList<>();;

    private int distance = 0;

    public SingleTour(){

        for(int i=0; i<Repository.getNumberOfCities(); i++){
            tour.add(Repository.getCity(i));
        }
    }

    public SingleTour(List<City> tour){
        List<City> currentTour = new ArrayList<>();

        for(int i=0; i<tour.size(); i++)
            currentTour.add(null);

        for(int i=0; i<tour.size(); i++)
            currentTour.set(i, tour.get(i));

        this.tour = currentTour;
    }

    //we have to calculate the total sum of edge weights
    public double calculateTourDistance(){

        if(distance==0) {

            for (int cityIndex = 0; cityIndex < getTourSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;

                if (cityIndex + 1 < getTourSize()) { // to make sure that there are more than 1 cities
                    destinationCity = getCity(cityIndex + 1);
                } else
                    destinationCity = getCity(0); // to close hamiltonian cycle

                distance += fromCity.distanceTo(destinationCity);
            }

        }

        return distance;
    }

    //to generate a random individual tour and hamiltonial cycle
    public void generateTour(){
        for(int cityIndex = 0; cityIndex<Repository.getNumberOfCities(); cityIndex++){
            setCity(cityIndex, Repository.getCity(cityIndex)); // we create a Hamiltonian cycle

            // the order is randomized

            Collections.shuffle(tour);
        }
    }

    public List<City> getTour(){
        return this.tour;
    }

    public void setCity(int index, City c){
        tour.set(index, c);
    }

    public City getCity(int index){
        return (City) tour.get(index);
    }

    public int getTourSize(){
        return tour.size();
    }

    @Override
    public String toString() {
       String s = "";

       for(City city : tour){
           s += city.toString() + " - ";
       }

       return s;
    }
}
