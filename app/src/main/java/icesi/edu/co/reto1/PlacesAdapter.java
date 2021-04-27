package icesi.edu.co.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.UUID;

public class PlacesAdapter extends RecyclerView.Adapter<PlaceView> {

    private ArrayList<Place> places;
    private ArrayList<Place> placesMemory;

    public PlacesAdapter(){

        places = new ArrayList<>();
        placesMemory = new ArrayList<>();

    }

    public void setPlaces(ArrayList<Place> places){
        this.places = places;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.placerow, null);
        ConstraintLayout rowroot = (ConstraintLayout) root;
        PlaceView placeView = new PlaceView(rowroot);
        return placeView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceView holder, int position) {

        //holder.setImage();
        holder.getName().setText(places.get(position).getName());
        holder.getRate().setText(""+places.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    public void search(String search) {

        placesMemory = places;
        places = new ArrayList<>();

        for(int i=0; i<placesMemory.size(); i++){
            if(placesMemory.get(i).getName().contains(search)){
                places.add(placesMemory.get(i));
            }
        }
        //setPlaces(places);
        this.notifyDataSetChanged();
        places = placesMemory;
    }
}
