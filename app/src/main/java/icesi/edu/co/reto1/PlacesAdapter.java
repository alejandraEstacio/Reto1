package icesi.edu.co.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlaceView> {

    private ArrayList<Place> places;

    public PlacesAdapter(){
        places = new ArrayList<>();
    }

    public void addPlace(Place place){
        places.add(place);
    }

    @NonNull
    @Override
    public PlaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.placerow, null);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

}
