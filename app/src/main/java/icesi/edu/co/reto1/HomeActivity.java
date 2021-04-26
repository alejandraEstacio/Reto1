package icesi.edu.co.reto1;

import
        androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import icesi.edu.co.reto1.comm.LocationWorker;

import android.Manifest;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements RatingDialog.onSumitListener {
    private NewItemFragment newItemFragment;
    private MapsFragment mapsFragment;
    private SearchFragment searchFragment;
    private BottomNavigationView navigator;

    private ArrayList<Place> places;

    public ArrayList<Place> getPlaces() {
        return places;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        places = new ArrayList<>();

        navigator = findViewById(R.id.navigator);
        newItemFragment = NewItemFragment.newInstance();
        searchFragment = SearchFragment.newInstance();
        mapsFragment = MapsFragment.newInstance();

        showFragment(newItemFragment);

        navigator.setOnNavigationItemSelectedListener(
                (menuItem) ->{
                    switch (menuItem.getItemId()){
                        case R.id.newPlace:
                            showFragment(newItemFragment);
                            newItemFragment.setHome(this);

                            break;
                        case R.id.map:
                            showFragment(mapsFragment);
                            // if(mapsFragment.getPoints()!=null) {

                             //   if(mapsFragment.getPoints().size()>0){
                                    //mapsFragment.drawMarkets();
                             // }
                         //  }
                            mapsFragment.setHome(this);
                            //mapsFragment.darMarcadores(places);
                            break;
                        case R.id.search:
                            showFragment(searchFragment);
                            searchFragment.setHome(this);
                            break;
                    }
                    return  true;
                }
        );

       }


    public void onRequestPermissionResult(int requesCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        super.onRequestPermissionsResult(requesCode, permissions, grantResult);
        if(requesCode == 11){
            Intent i = new Intent(this, MapsFragment.class);
            startActivity(i);
        }
    }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
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
        mapsFragment.getDialog().dismiss();
    }

    public void changeToNew() {
        showFragment(newItemFragment);
        newItemFragment.setHome(this);
    }



    public void addPlace(Place place){
        places.add(place);
        searchFragment.getAdapter().setPlaces(places);
    }

}