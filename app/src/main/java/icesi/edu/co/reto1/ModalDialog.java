package icesi.edu.co.reto1;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ModalDialog extends DialogFragment implements  View.OnClickListener {

    private Button btnCamera;
    private Button btnGalery;

    private OnOkListener listener;

    public ModalDialog() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModalDialog newInstance() {
        ModalDialog fragment = new ModalDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modal_dialog, container, false);
        btnCamera = root.findViewById(R.id.btnCamera);
        btnGalery = root.findViewById(R.id.btnGalery);
        btnCamera.setOnClickListener(this);
        btnGalery.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCamera:
                if(listener==null){
                    Log.e("Error", "No hay observer");
                }else{
                    listener.onOkCamera("Camara");
                }
                break;
            case R.id.btnGalery:
                if(listener==null){
                    Log.e("Error", "No hay observer");
                }else{
                    listener.onOkGalery("Galeria");
                }
                break;
        }
    }


public void setListener(OnOkListener listener){
        this.listener= listener;
    }
    public interface OnOkListener{
        void onOkGalery(String s);
        void onOkCamera(String  s);
    }

}