package icesi.edu.co.reto1.comm;

import com.google.gson.Gson;

import icesi.edu.co.reto1.MapsFragment;

public class LocationWorker extends Thread{

    private MapsFragment ref;
    private String address;

    public LocationWorker(){
        this.address = address;
    }

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

    /*public String getCityName(){
        String myCity = "";
        if( ref.getCurrentPosition()!= null) {
         //   Position pos = new Position(ref.getCurrentPosition().getLat(), ref.getCurrentPosition().getLng());
            try {
                Geocoder geocoder = new Geocoder(ref.getActivity(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(ref.getCurrentPosition().getLat(), ref.getCurrentPosition().getLng(), 1);
                if (addresses.size() > 0) {
                    myCity = addresses.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myCity;
    }

    public String darDireccion(){
        address = ref.getCityName(pos);
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }*/
}
