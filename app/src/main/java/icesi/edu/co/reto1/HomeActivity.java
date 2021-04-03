package icesi.edu.co.reto1;

import
        androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class HomeActivity extends AppCompatActivity {
    private NewItemFragment newItemFragment;
    private MapsFragment mapsFragment;
    private SearchFragment searchFragment;
    private BottomNavigationView navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 1
        );

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
                        break;
                        case R.id.map:
                            showFragment(mapsFragment);
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