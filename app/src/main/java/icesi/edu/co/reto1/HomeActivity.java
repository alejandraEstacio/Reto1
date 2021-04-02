package icesi.edu.co.reto1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class HomeActivity extends AppCompatActivity {
    private NewItemFragment newItemFragment;
    private MapsFragment mapsFragment;
    private SearchFragment searchFragment;
    private BottomNavigationView navigator;

   /* private Button btnAddImage;
    private ImageView mainImage;
    private File file;

    public static final int PERMISSIONS_CALLBACK =11;
    private static final int CAMERA_CALLBACK=12;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      //  btnAddImage = findViewById(R.id.btnAddImage);
      //  mainImage = findViewById(R.id.mainImage);
        //btnAddImage.setOnClickListener(this);

        navigator = findViewById(R.id.navigator);
        newItemFragment = NewItemFragment.newInstance();
        searchFragment = SearchFragment.newInstance();
        showFragment(newItemFragment);

        navigator.setOnNavigationItemSelectedListener(
                (menuItem) ->{
                    switch (menuItem.getItemId()){
                        case R.id.newPlace:
                            showFragment(newItemFragment);
                        break;
                        case R.id.search:
                            showFragment(searchFragment);
                        break;
                    }
                    return  true;
                }
        );

       }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

}