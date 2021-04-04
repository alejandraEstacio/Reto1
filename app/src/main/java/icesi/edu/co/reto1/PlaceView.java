package icesi.edu.co.reto1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceView extends RecyclerView.ViewHolder {
    private ConstraintLayout root;
    private ImageView image;
    private TextView rate;
    private Button seen;


    public PlaceView( View itemView) {
        super(itemView);
    }
}
