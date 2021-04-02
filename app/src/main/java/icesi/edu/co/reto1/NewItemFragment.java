package icesi.edu.co.reto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class NewItemFragment extends Fragment implements View.OnClickListener, ModalDialog.OnOkListener{
    private EditText nameText;
    private Button btnRegister;
    private Button btnAddImage;
    private ImageView mainImage;
    private ModalDialog dialog;
    private File file;

    public static final int PERMISSIONS_CALLBACK =11;
    private static final int CAMERA_CALLBACK=12;

    private static final int GALERY_CALLBACK = 13 ;

    public NewItemFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static NewItemFragment newInstance() {
        NewItemFragment fragment = new NewItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_item, container, false);
        nameText = root.findViewById(R.id.nameText);
        btnRegister = root.findViewById(R.id.btnRegister);
        btnAddImage = root.findViewById(R.id.btnAddImage);
        mainImage = root.findViewById(R.id.mainImage);

        btnAddImage.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        //Camara
       ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                },
                PERMISSIONS_CALLBACK);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Toast.makeText(getContext(), nameText.getText().toString(), Toast.LENGTH_LONG).show();
                break;

            case R.id.btnAddImage:
                dialog = ModalDialog.newInstance();
                dialog.setListener(this);
                dialog.show(getFragmentManager(),"dialog");

                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_CALLBACK && resultCode==RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(
                    image, image.getWidth()/4, image.getHeight()/4, true
            );

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotateBitmap = Bitmap.createBitmap(thumbnail, 0,0, thumbnail.getWidth(), thumbnail.getHeight(), matrix, true);
            mainImage.setImageBitmap(rotateBitmap);
        }
        else if(requestCode == GALERY_CALLBACK && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Log.e(">>>>", uri+"");
            String path = UtilDomi.getPath(getContext(), uri);
            Log.e(">>>>", path+"");
            Bitmap image = BitmapFactory.decodeFile(path);
            mainImage.setImageBitmap(image);
        }

    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSIONS_CALLBACK){
            boolean allGrant= true;
            for(int i =0; i<grantResults.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allGrant = false;
                }
            }
            if (allGrant){
                Toast.makeText( getContext(), "Todos los permisos concedidos", Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Alerta!, no todos los permisos concedidos", Toast.LENGTH_LONG).show();

            }
        }

    }


    @Override
    public void onOkGalery(String s) {
        dialog.dismiss();
        Intent j = new Intent(Intent.ACTION_GET_CONTENT);
        j.setType("image/*");
        startActivityForResult(j, GALERY_CALLBACK);
    }

    @Override
    public void onOkCamera(String s) {
        dialog.dismiss();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(getActivity().getExternalFilesDir(null)+"/photo.png");
        Log.e(">>>>", ""+file);
        Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName(), file);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(i, CAMERA_CALLBACK);
    }
}