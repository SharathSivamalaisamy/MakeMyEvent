package com.example.makemyeventfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.makemyeventfinal.Model.UserCart;
import com.example.makemyeventfinal.Model.User;
import com.example.makemyeventfinal.Model.UserCart;
import com.example.makemyeventfinal.ProfilePage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button signin_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        signin_button=findViewById(R.id.Googlesignin);


        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc= GoogleSignIn.getClient(this, gso);



        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });


    }

    private void SignIn() {

        Intent intent= gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
                movetoinfo();
            }
            catch (ApiException e){
                Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void movetoinfo(){
        finish();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        List<String> EmptyList = Collections.<String>emptyList();
        int PRESENT = 0;
        UserCart u = new UserCart(account.getId(), account.getDisplayName(), account.getEmail(), String.valueOf(account.getPhotoUrl()), EmptyList);
        db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Boolean PRESENT = false;
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    UserCart u1 = document.toObject(UserCart.class);
                    if(account.getId() == u1.getUser_id()){
                        PRESENT = true;
                    }
                }
                if (!PRESENT) {
                    db.collection("Users").document("first note").set(u)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(LoginActivity.this, "User Added ", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, "Errorrr", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(LoginActivity.this, "User Already Added", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
        startActivity(intent);
    }

}