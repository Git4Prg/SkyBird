package com.mintshock.ticketbookingapp.Activity;

import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.mintshock.ticketbookingapp.Model.Flight;
import com.mintshock.ticketbookingapp.databinding.ActivityTicketDetailBinding;

public class TicketDetailActivity extends BaseActivity {
    private ActivityTicketDetailBinding binding;
    private Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        binding.backBtnTicket.setOnClickListener(v -> finish());
        binding.fromText.setText(flight.getFromShort());
        binding.fromSmallText.setText(flight.getFrom());
        binding.toText.setText(flight.getTo());
        binding.toShortText.setText(flight.getToShort());
        binding.toSmallText.setText(flight.getTo());
        binding.dateSmallText.setText(flight.getDate());
        binding.timeSmallText.setText(flight.getTime());
        binding.arrivalText.setText(flight.getArriveTime());
        binding.classSmallText.setText(flight.getClassSeat());
        binding.priceSmallText.setText("$" + flight.getPrice());
        binding.airlineSmallText.setText(flight.getAirlineName());
        binding.seatsSmallText.setText(flight.getPassenger());

        Glide.with(TicketDetailActivity.this)
                .load(flight.getAirlineLogo())
                .into(binding.logo);
    }

    private void getIntentExtra() {
        flight = (Flight) getIntent().getSerializableExtra("flight");
    }
}