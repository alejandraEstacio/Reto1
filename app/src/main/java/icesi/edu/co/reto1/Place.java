package icesi.edu.co.reto1;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import icesi.edu.co.reto1.model.Position;

public class Place {

    private String id;
    private String name;
    private String address;
    private double rate;
    private Drawable img;
    private Position pos;
    private ArrayList<Position> positions;

    public Place(){ }

    public Place(String id, String name, String address, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.address = address;

    }

    public Place(String id, String name,String address, double rate, Position pos, Drawable img){
        this.id = id;
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.pos = pos;
        this.img = img;
        positions = new ArrayList<>();
        positions.add(new Position(pos.getLat(), pos.getLng()));
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
        if(rate==0) {
           this.rate =rate;
        }else {
            double aux = (this.rate + rate) / 2;
            this.rate = aux;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
}
