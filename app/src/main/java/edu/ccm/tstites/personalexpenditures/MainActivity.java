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

public class MainActivity extends AppCompatActivity {

    TextView txtCurrentCash;
    Button btnViewReceipts;
    Button btnAddReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("CashValues", MODE_PRIVATE);
        Double cash = Double.longBitsToDouble(pref.getLong("CurrentCash", 0));

        txtCurrentCash = findViewById(R.id.txt_currentCash);
        txtCurrentCash.setText(String.format(getString(R.string.txt_currentCash), formatCash(cash)));
        txtCurrentCash.setTextColor(
                (cash >= 0)
                ? getResources().getColor(R.color.positiveCashValue)
                : getResources().getColor(R.color.negativeCashValue));

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
    }

    private String formatCash(double cash) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(cash);
    }
}
