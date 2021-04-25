package icesi.edu.co.reto1.comm;

import android.location.Address;
import android.location.Geocoder;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import icesi.edu.co.reto1.MapsFragment;
import icesi.edu.co.reto1.model.Position;


public class LocationWorker extends Thread{

    private MapsFragment ref;

    public LocationWorker(MapsFragment ref){
        this.ref = ref;
    }

    @Override
    public void run(){
        HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
        Gson gson = new Gson();
        while (isAlive()){
            delay(10000);
          if( ref.getCurrentPosition()!= null){
              utilDomi.PUTrequest("https://reto1-fe470-default-rtdb.firebaseio.com/"+"/location.json", gson.toJson(ref.getCurrentPosition()));
          }
        }
    }
    public void delay(long time){
        try{
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finish(){

    }

    public String getCityName(){
        String myCity = "";
        if( ref.getCurrentPosition()!= null) {
           Position pos = new Position(ref.getCurrentPosition().getLat(), ref.getCurrentPosition().getLng());
            try {
                Geocoder geocoder = new Geocoder(ref.getActivity(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(pos.getLat(), pos.getLng(), 1);
                if (addresses.size() > 0) {
                    myCity = addresses.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myCity;
    }
}
