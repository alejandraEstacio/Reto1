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

import android.content.Intent;
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
                            newItemFragment.setHome(this);
                            break;
                        case R.id.map:
                            showFragment(mapsFragment);
                            mapsFragment.setHome(this);
                            break;
                        case R.id.search:
                            showFragment(searchFragment);
                            searchFragment.setHome(this);
                            break;
                    }
                    return  true;
                }
        );

       }


    public void onRequestPermissionResult(int requesCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        super.onRequestPermissionsResult(requesCode, permissions, grantResult);
        if(requesCode == 11){
            Intent i = new Intent(this, MapsFragment.class);
            startActivity(i);
        }
    }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void cancelarSemestre(int unaMillonada){

    }

}