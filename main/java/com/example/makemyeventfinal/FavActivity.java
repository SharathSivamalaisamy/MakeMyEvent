package com.example.makemyeventfinal;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.makemyeventfinal.Model.Events;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


import Adapter.EventAdapter;






public class FavActivity extends Fragment {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ListView eventsListView;

    private ArrayAdapter<Events> adapter;

    public Button load;

    public FavActivity() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        eventsListView = view.findViewById(R.id.eventsListView);
        //adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<Books>());

        adapter = new EventAdapter(getContext(), new ArrayList<Events>());

        load = view.findViewById(R.id.loadbutton);



        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchData();
            }
        });
        eventsListView.setAdapter(adapter);

        return view;
    }



    public void FetchData(){
        db.collection("Favourites").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    ArrayList<Events> event = new ArrayList<>();
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Events b = document.toObject(Events.class);
                            event.add(b);

                            Log.d(TAG, b.getEventname()+""+b.getCategory()+""+b.getDescription()+""+b.getLink());
                        }
                        adapter.addAll(event);

                    }
                });
    }
}