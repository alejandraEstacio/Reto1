package icesi.edu.co.reto1;

import
        androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;
    private ArrayList<Marker> points;
    private  List<Address> addresses;
    private Geocoder geocoder;

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2, this);
        setInitialPos();

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
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

        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(getContext());
        try {
            addresses= geocoder.getFromLocationName("abc.xyz", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());

            if (me == null) {
                    me = mMap.addMarker(new MarkerOptions().position(myPos).title("Yo"));
            } else {
                me.setPosition(myPos);
                //   String cityName= getCityName(myPos);
            }
         //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos,17));
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
          /*  if(addresses.size() >0) {
                Address address = addresses.get(0);
                MarkerOptions markerOptions = new MarkerOptions().
                        position(latLng)
                        .title(address.getLocality());*/
               Marker p =  mMap.addMarker(new MarkerOptions().position(latLng).title("marcador"));
               // Marker p=  mMap.addMarker(markerOptions);
                points.add(p);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), marker.getPosition().latitude+", "+marker.getPosition().longitude, Toast.LENGTH_LONG).show();
        
          marker.showInfoWindow();
        return true;
    }

   /* private String getCityName(LatLng myPos)  {
        String myCity="";
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {

        List<Address> addresses = null;
        addresses = geocoder.getFromLocation(myPos.latitude, myPos.longitude, 1);
        String address = addresses.get(0).getAddressLine(0);
        myCity = addresses.get(0).getLocality();
        Log.d("mylog", "Complete Addresses: "+addresses.toString());
        Log.d("mylog", "Addresses: "+addresses);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCity;
    }*/
}


