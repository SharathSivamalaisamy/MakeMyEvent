package com.example.makemyeventfinal;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.makemyeventfinal.Model.Events;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class UploadEventActivity extends Fragment {

    EditText eventname,category,description,link;
    Button addevent;



    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private static final String EVENT_NAME = "eventname";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private Button mButtonChooseImage;
    private ImageView mImageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upload_event, container, false);


        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc= GoogleSignIn.getClient(getContext(), gso);

        eventname = view.findViewById(R.id.eventNameText);
        category = view.findViewById(R.id.categoryText);
        description=view.findViewById(R.id.descriptionText);
        link = view.findViewById(R.id.registrationLinkText);
        mImageView = view.findViewById(R.id.image_view);

        mButtonChooseImage = view.findViewById(R.id.select);
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        addevent = view.findViewById(R.id.uploadEventButton);
        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
                clear();
                Toast.makeText(getContext(), "Events Added ",Toast.LENGTH_SHORT).show();
            }
        });


return view;
    }



    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView);
        }
    }

    public void clear() {

        eventname.setText("");
        category.setText("");
        description.setText("");
        link.setText("");

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void addBook() {

        String Eventname = eventname.getText().toString();
        String Category = category.getText().toString();
        String Description = description.getText().toString();
        String Link = link.getText().toString();


        Events b = new Events(Eventname, Category, Description, Link);

        /*Map<String, Object> book = new HashMap<>();
        book.put(BOOK_NAME,Bookname);
        book.put(AUTHOR,Author);
        book.put(EDITION,Edition);
        book.put(ISBN,Isbn);
        book.put(COPIES,Copies);*/

        db.collection("Events").document(Eventname).set(b)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Events Added", Toast.LENGTH_SHORT).show();
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