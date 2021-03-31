package icesi.edu.co.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemFragment extends Fragment implements View.OnClickListener{
    private EditText nameText;
    private Button btnRegister;
    public NewItemFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static NewItemFragment newInstance() {
        NewItemFragment fragment = new NewItemFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_item, container, false);
        nameText = root.findViewById(R.id.nameText);
        btnRegister = root.findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Toast.makeText(getContext(), nameText.getText().toString(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}