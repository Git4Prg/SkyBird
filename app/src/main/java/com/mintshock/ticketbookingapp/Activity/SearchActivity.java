package com.mintshock.ticketbookingapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mintshock.ticketbookingapp.Adapter.FlightAdapter;
import com.mintshock.ticketbookingapp.Model.Flight;
import com.mintshock.ticketbookingapp.R;
import com.mintshock.ticketbookingapp.databinding.ActivitySearchBinding;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity {
    private ActivitySearchBinding binding;
    private String from, to, date;
    private int numPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Flights");
        ArrayList<Flight> list = new ArrayList<>();
        Query query = myRef.orderByChild("from").equalTo(from);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot issue : snapshot.getChildren()) {
                        Flight flight = issue.getValue(Flight.class);
                        if(flight.getTo().equals(to)) {
                            list.add(flight);
                        }

//                        if(flight.getTo().equals(to) && flight.getDate().equals(date)) {
//                            list.add(flight);
//                        }

                        if(!list.isEmpty()) {
                            binding.searchView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
                            binding.searchView.setAdapter(new FlightAdapter(list));
                        }

                        binding.progressBarSearch.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        date = getIntent().getStringExtra("date");
    }
}