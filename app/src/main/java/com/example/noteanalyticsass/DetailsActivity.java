package com.example.noteanalyticsass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView textView2;
    ImageView imageView;

    TextView textView;
    String id;
    String cat;
    String cid;
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            screenTrack("Details Notes Screen");
            Intent intent = getIntent();
            cid = intent.getStringExtra("cid");
            Notes note = (Notes) intent.getSerializableExtra("Note");
            id = note.getId();
            textView2 = findViewById(R.id.textView2);

        textView = findViewById(R.id.textView);
            imageView = findViewById(R.id.imageView);

            GetAllNotes();
        }
        private void GetAllNotes() {
            DocumentReference docRef =db.collection("Category").document(cid).collection("NoteA").document(id);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("Title");
                            String content = documentSnapshot.getString("Content");
                            String image = documentSnapshot.getString("Image");
                            Picasso.get().load(image).into(imageView);

                            textView.setText(name);
                            textView2.setText(content);
                            Log.e("LogDATA", id + " " + name);
                        } else {
                            Log.d("tag", "onSuccess: LIST EMPTY");
                            return;
                        }
                    } else {
                        Log.e("LogDATA", "get failed with ");
                    }
                }
            });


        }


        public void screenTrack(String screenName){
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Main Activity3");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        }

        @Override
        protected void onPause() {

            Calendar calendar = Calendar.getInstance();
            int hour2 = calendar.get(Calendar.HOUR);
            int minute2 = calendar.get(Calendar.MINUTE);
            int second2 = calendar.get(Calendar.SECOND);

            int h = hour2-hour;
            int m = minute2-minute;
            int s = second2-second;
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            HashMap<String,Object> screens = new HashMap<>();
            screens.put("name","Details Notes Screen");
            screens.put("hours",h);
            screens.put("minute",m);
            screens.put("seconds",s);

            db.collection("Details Notes Screen").add(screens)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
            Log.e("Hours",String.valueOf(h));
            Log.e("Minutes",String.valueOf(m));
            Log.e("Seconds",String.valueOf(s));
            super.onPause();
        }
    }
