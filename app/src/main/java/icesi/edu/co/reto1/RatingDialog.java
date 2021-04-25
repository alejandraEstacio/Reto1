package icesi.edu.co.reto1;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class RatingDialog extends DialogFragment implements View.OnClickListener {

    private ImageButton rate1;
    private ImageButton rate2;
    private ImageButton rate3;
    private ImageButton rate4;
    private ImageButton rate5;
    private Button sumitRate;
    private TextView addressET;
    private TextView placeNameET;
    private ImageView imagePlace;
    private double rate;

    private onSumitListener listener;

    private Place place;

    public TextView getPlaceNameET() {
        return placeNameET;
    }

    public void setPlaceNameET(TextView placeNameET) {
        this.placeNameET = placeNameET;
    }

    public RatingDialog() {
        // Required empty public constructor
    }

    public void setPlace(Place place){

        this.place = place;
        rate = place.getRate();
    }

    public void setListener(onSumitListener listener){
        this.listener = listener;
    }

    public static RatingDialog newInstance() {
        RatingDialog fragment = new RatingDialog();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_rating, container, false);

        rate1 = root.findViewById(R.id.rate1);
        rate2 = root.findViewById(R.id.rate1);
        rate3 = root.findViewById(R.id.rate1);
        rate4 = root.findViewById(R.id.rate1);
        rate5 = root.findViewById(R.id.rate1);
        sumitRate = root.findViewById(R.id.sumitRate);
        addressET = root.findViewById(R.id.addressET);
        placeNameET = root.findViewById(R.id.placeName);
        imagePlace = root.findViewById(R.id.imagePlace);

        rate1.setOnClickListener(this);
        rate2.setOnClickListener(this);
        rate3.setOnClickListener(this);
        rate4.setOnClickListener(this);
        rate5.setOnClickListener(this);
        sumitRate.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rate1:
                rate = 1.0;
                break;
            case R.id.rate2:
                rate = 2.0;
                break;
            case R.id.rate3:
                rate = 3.0;
                break;
            case R.id.rate4:
                rate = 4.0;
                break;
            case R.id.rate5:
                rate = 5.0;
                break;
            case R.id.sumitRate:
                if(listener!=null) {
                    listener.onSumit(rate, placeNameET.getText().toString());
                }
                else {
                    Log.e("Error", "No hay observer");
                }
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                break;

        }
    }

    public interface onSumitListener{
        void onSumit(double rate, String placeName);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}