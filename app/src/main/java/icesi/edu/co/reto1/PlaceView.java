package icesi.edu.co.reto1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceView extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ConstraintLayout root;
    private ImageView image;
    private TextView name;
    private TextView rate;
    private ImageButton seen;


    public PlaceView( ConstraintLayout root) {
        super(root);
        this.root = root;
        image = root.findViewById(R.id.imPlace);
        name = root.findViewById(R.id.placeName);
        rate = root.findViewById(R.id.placeAverage);
        seen = root.findViewById(R.id.imSearchBtn);
        seen.setOnClickListener(this);
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

    public TextView getRate() {
        return rate;
    }

    public ImageButton getSeen() {
        return seen;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imSearchBtn:
                //AQUI va el método donde mostrará el mapa con la ubicación del sitio.

                break;
        }
    }
}
