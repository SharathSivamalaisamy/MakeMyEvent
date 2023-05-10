package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.makemyeventfinal.Model.Favs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.makemyeventfinal.Model.Events;
import com.example.makemyeventfinal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.BreakIterator;
import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<Events> {
    TextView EventName;
    TextView Description;
    TextView Link;
    ArrayList<Events> events;
    Button fav;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public EventAdapter(Context context, ArrayList<Events> events){
        super(context,0,events);
        this.events = events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView =  LayoutInflater.from(getContext()).inflate(R.layout.event_rv_item, parent, false);
        }

        EventName = convertView.findViewById(R.id.eventName);
        Description = convertView.findViewById(R.id.description);
        Link = convertView.findViewById(R.id.registrationLink);
        fav = convertView.findViewById(R.id.fav);
        Events b = events.get(position);
        EventName.setText(b.getEventname());
        Description.setText(b.getDescription());
        Link.setText(b.getLink());


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
                clear();

            }
        });










        return convertView;


    }

    public void addBook() {

       String  Event = EventName.getText().toString();
       String desc= Description.getText().toString();
       String linkk=Link.getText().toString();

        Favs b = new Favs(Event, desc, linkk);

        /*Map<String, Object> book = new HashMap<>();
        book.put(BOOK_NAME,Bookname);
        book.put(AUTHOR,Author);
        book.put(EDITION,Edition);
        book.put(ISBN,Isbn);
        book.put(COPIES,Copies);*/

        db.collection("Favourites").document(Event).set(b)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Added to Fav", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Errrrorrr!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}