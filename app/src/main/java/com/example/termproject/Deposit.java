package com.example.termproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.db.LedgerItem;
import com.example.termproject.db.LedgerRepository;

public class Deposit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText am =findViewById(R.id.editText2);
                int amount = Integer.parseInt(am.getText().toString());
                LedgerItem ledgerItem = new LedgerItem(0,amount);

                insertLedger(ledgerItem);
            }
        });
    }

    private void insertLedger(LedgerItem item) {
        LedgerRepository repo = new LedgerRepository(Deposit.this);
        repo.insertLedger(item, new LedgerRepository.InsertCallback() {
            @Override
            public void onInsertSuccess() {
                finish();
            }
        });
    }
}
