package simulated_annealing;

public class City {

    private int x;
    private int y;

    public City(){
        this.x = (int) (Math.random() * 100);
        this.y = (int) (Math.random() * 100);
        System.out.println("City : " + x + " - " + y);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Euclidean distance
    public double distanceTo(City c){

        int distX = Math.abs(getX() - c.getX());
     //   System.out.println("Distance X to a city= Math.abs(" + getX() + " - " + c.getX() + ") = " + distX);
        int distY = Math.abs(getY() - c.getY());
    //    System.out.println("Distance Y to a city= Math.abs(" + getY() + " - " + c.getY() + ") = " + distY);

        double distance =  Math.sqrt((distX*distX) + (distY*distY));
    //    System.out.println("Distance = Math.sqrt(distanceX " + (distX*distX) + " + distance Y " + (distY*distY) + " = " + distance);

        return distance;
    }

    @Override
    public String toString() {
        return "City{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
