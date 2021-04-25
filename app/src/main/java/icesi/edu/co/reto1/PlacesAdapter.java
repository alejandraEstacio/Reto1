package icesi.edu.co.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.UUID;

public class PlacesAdapter extends RecyclerView.Adapter<PlaceView> implements RatingDialog.onSumitListener{

    private ArrayList<Place> places;

    public PlacesAdapter(){

        places = new ArrayList<>();
        places.add(new Place(UUID.randomUUID().toString(), "plaza 1", 3.2));
        places.add(new Place(UUID.randomUUID().toString(), "plaza 2", 3.8));
        places.add(new Place(UUID.randomUUID().toString(), "plaza 3", 1.0));
    }

    public void addPlace(Place place){
        places.add(place);
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

    @Override
    public void onSumit(double rate, String placeName) {
        boolean still = false;
        for(int i=0; i<places.size()&&!still;i++){
            if(places.get(i).getName().equals(placeName)) {
                places.get(i).recalculateRate(rate);
                still=true;
            }
        }
    }
}
