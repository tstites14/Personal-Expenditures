package edu.ccm.tstites.personalexpenditures;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;

public class MainActivity extends AppCompatActivity {

    TextView txtCurrentCash;
    Button btnViewReceipts;
    Button btnAddReceipt;
    Button btnAddPaycheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Double cash = AccountRegister.get(getApplicationContext()).getCash();

        txtCurrentCash = findViewById(R.id.txt_currentCash);
        txtCurrentCash.setText(String.format(getString(R.string.txt_currentCash), formatCash(cash)));
        txtCurrentCash.setTextColor(getCashColor(cash));

        btnViewReceipts = findViewById(R.id.btn_viewReceipt);
        btnViewReceipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewReceiptsActivity.class));
            }
        });

        btnAddReceipt = findViewById(R.id.btn_addReceipt);
        btnAddReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddReceiptActivity.class));
            }
        });

        btnAddPaycheck = findViewById(R.id.btn_addPaycheck);
        btnAddPaycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddPaycheckActivity.class));
            }
        });
    }

    private int getCashColor(double cash) {
    if (cash >= 100) {
        return getResources().getColor(R.color.positiveCashValue);
    } else if (cash > 0 && cash < 100) {
        return getResources().getColor(R.color.moderateCashValue);
    } else {
            return getResources().getColor(R.color.negativeCashValue);
        }
    }

    private String formatCash(double cash) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(cash);
    }
}
