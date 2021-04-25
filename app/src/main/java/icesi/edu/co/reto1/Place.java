package icesi.edu.co.reto1;

public class Place {

    private String id;
    private String name;
    private String address;
    private double rate;

    public Place(){ }

    public Place(String id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public Place(String id, String name,String address, double rate){
        this.id = id;
        this.name = name;
        this.address = address;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void recalculateRate(double rate){
        double aux = (this.rate+rate)/2;
        this.rate = aux;
    }
}
