package Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makemyeventfinal.ImagesActivity;
import com.example.makemyeventfinal.Model.Favs;
import com.example.makemyeventfinal.Model.Upload;
import com.example.makemyeventfinal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context context, List<Upload> uploads) {
       mContext = context;
        this.mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);

        holder.textViewName.setText(uploadCurrent.getName());
        holder.desc.setText(uploadCurrent.getDescription());
        holder.link.setText(uploadCurrent.getLink());

        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageV);

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String  Event = holder.textViewName.getText().toString();
                    String desc= holder.desc.getText().toString();
                    String linkk=holder.link.getText().toString();

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
                                    Toast.makeText(mContext.getApplicationContext(), "Added to Fav", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(mContext.getApplicationContext(), "Errrrorrr!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }



        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageV;
        public TextView desc;
        public TextView link;
        public Button fav;
        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.ename);
            imageV = itemView.findViewById(R.id.image);
            desc = itemView.findViewById(R.id.desc);
            link = itemView.findViewById(R.id.link);
            fav = itemView.findViewById(R.id.button);
        }
    }


}