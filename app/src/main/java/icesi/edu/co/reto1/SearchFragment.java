package icesi.edu.co.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText placeToSearch;
    private RecyclerView placesViewList;
    private LinearLayoutManager layoutManager;
    private PlacesAdapter adapter;

    private HomeActivity home;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);


        placeToSearch = root.findViewById(R.id.placeToSearch);
<<<<<<< HEAD
        placeToSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                search(placeToSearch.getText().toString());
                Log.d( "algo","PERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRAAAAAAAAAAAAAAAAAA"+keyCode + " "+ event.toString());
                return false;
            }
        });

=======
>>>>>>> cc90ffd072453c115ad9a30a7aba21ac0e3760ab
        placesViewList =root.findViewById(R.id.placesViewList);
        placesViewList.setHasFixedSize(true);

        adapter = new PlacesAdapter();
        adapter.setPlaces(home.getPlaces());
        placesViewList.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this.getContext());
        placesViewList.setLayoutManager(layoutManager);

        return root;
    }

    private void search(String search) {

        adapter.search(search);
        adapter.notifyDataSetChanged();
    }

    public void setHome(HomeActivity homeActivity) {

        this.home = homeActivity;
    }

    public PlacesAdapter getAdapter(){
        return this.adapter;
    }


}