package icesi.edu.co.reto1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceView extends RecyclerView.ViewHolder{
    private ConstraintLayout root;
    private ImageView image;
    private TextView name;
    private TextView address;
    private TextView rate;
    private ImageButton seen;

    public PlaceView( ConstraintLayout root) {
        super(root);
        this.root = root;
        image = root.findViewById(R.id.imPlace);
        name = root.findViewById(R.id.placeName);
        address = root.findViewById(R.id.placeAddr);
        rate = root.findViewById(R.id.placeAverage);
        seen = root.findViewById(R.id.imSearchBtn);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getName() {
        return name;
    }

    public TextView getAddress() {
        return address;
    }

    public TextView getRate() {
        return rate;
    }

    public ImageButton getSeen() {
        return seen;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }


}
