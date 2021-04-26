package icesi.edu.co.reto1;

import
        androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import icesi.edu.co.reto1.comm.LocationWorker;
import icesi.edu.co.reto1.model.Position;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener
{
    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;
    private ArrayList<Marker> points;
    private Geocoder geocoder;
    private Button addButton;
    private LocationWorker locationWorker;
    private Position currentPosition;

    private HomeActivity home;

    private RatingDialog dialog;

    public RatingDialog getDialog() {
        return dialog;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2, this);
        setInitialPos();

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        locationWorker = new LocationWorker(this);
        locationWorker.start();
    }

    @Override
    public void onDestroy() {
        locationWorker.finish();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        addButton = root.findViewById(R.id.addButton);
        addButton.setVisibility(View.INVISIBLE);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        points = new ArrayList<>();
        geocoder = new Geocoder(getActivity());
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingPermission")
    public void setInitialPos() {
        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location != null) {
            updateMyLocation(location);
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        updateMyLocation(location);
    }

    public void updateMyLocation(Location location){
        LatLng  myPos = new LatLng(location.getLatitude(), location.getLongitude());
        String cityName = getCityName(myPos);
        if (me == null) {
            me = mMap.addMarker(new MarkerOptions().position(myPos).title("yo").icon(BitmapDescriptorFactory.fromResource(R.drawable.person)));
        } else {
            me.setPosition(myPos);
        }
          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos,17));
        currentPosition= new Position(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    @Override
    public void onProviderEnabled(@NonNull String provider) { }

    @Override
    public void onProviderDisabled(@NonNull String provider) { }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
       String cityName = getCityName(latLng);
        Marker p =  mMap.addMarker(new MarkerOptions().position(latLng).title("marcador"));
        points.add(p);
        addButton.setVisibility(View.VISIBLE);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("direccion", getCityName(points.get(points.size()-1).getPosition()));
                getParentFragmentManager().setFragmentResult("key", bundle);
                home.changeToNew();
            }
        });


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String dir = getCityName(marker.getPosition());
        Toast.makeText(getContext(), marker.getPosition().latitude+", "+marker.getPosition().longitude, Toast.LENGTH_LONG).show();
        marker.setSnippet(getCityName(marker.getPosition()));
        marker.showInfoWindow();

        dialog = RatingDialog.newInstance();
        dialog.setListener(home);
        //int position = home.
        dialog.setPlace(new Place(UUID.randomUUID().toString(), dir, 0.0));
        dialog.show(getActivity().getSupportFragmentManager(), "Rate Dialog");
      //  home.addPlace(marker.getPosition());

        return true;
    }

   public String getCityName(LatLng myPos){
        String myCity="";
        try {
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(myPos.latitude, myPos.longitude, 1);
           if (addresses.size() > 0) {
                myCity = addresses.get(0).getAddressLine(0);
           }
            } catch(IOException e){
                e.printStackTrace();
            }
        return myCity;
    }

    public Position getCurrentPosition(){
    return currentPosition;
    }

    public Marker getMarker(){
       return me;
    }


    public void setHome(HomeActivity homeActivity) {
        home = homeActivity;
    }

    public ArrayList<Marker> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Marker> points) {
        this.points = points;
    }

    public void drawMarkets() {

        if(getActivity()!=null){
            getActivity().runOnUiThread(
                    () -> {

                        for (int i = 0; i < points.size(); i++) {
                            Marker marker = points.get(i);
                            Marker marker1 = mMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getSnippet()));
                        }
                    }
            );
        }
    }
}

