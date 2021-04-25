package icesi.edu.co.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

        placesViewList =root.findViewById(R.id.placesViewList);
        placesViewList.setHasFixedSize(true);

        adapter = new PlacesAdapter();
        adapter.setPlaces(home.getPlaces());
        placesViewList.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this.getContext());
        placesViewList.setLayoutManager(layoutManager);

        return root;
    }

    public void setHome(HomeActivity homeActivity) {

        this.home = homeActivity;
    }


}