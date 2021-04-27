package icesi.edu.co.reto1.comm;

import android.location.Address;
import android.location.Geocoder;

import com.google.gson.Gson;

import icesi.edu.co.reto1.MapsFragment;

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

    }