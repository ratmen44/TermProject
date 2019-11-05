package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.db.LedgerItem;
import com.example.termproject.db.LedgerRepository;

import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button withbutton = findViewById(R.id.withdraw_btn) ;
        withbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Withdraw.class);
                startActivity(intent);
            }
        });

        Button depobutton = findViewById(R.id.deposit_btn) ;
        depobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Deposit.class);
                startActivity(intent);
            }
        });
}
    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
    }

    private void reloadData() {
        LedgerRepository repo = new LedgerRepository(Home.this);

        repo.getLedger(new LedgerRepository.Callback() {
            @Override
            public void onGetLedger(List<LedgerItem> itemList) {
                int totalAmount = 0;

                for (LedgerItem item : itemList) {
                    totalAmount += item.amount;
                }

                TextView balanceTextView = findViewById(R.id.textView2);
                balanceTextView.setText(String.valueOf(totalAmount));
            }
        });
    }
}
