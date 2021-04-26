package icesi.edu.co.reto1.comm;

import com.google.gson.Gson;

import icesi.edu.co.reto1.MapsFragment;

public class LocationWorker extends Thread {

    private MapsFragment ref;

    public LocationWorker(MapsFragment ref) {

        this.ref = ref;
    }

    @Override
    public void run() {

        while (isAlive()) {
            delay(10000);
           // ref.darMarcadores();
        }
    }

    public void delay(long time) {
        try {
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finish() {

    }


}