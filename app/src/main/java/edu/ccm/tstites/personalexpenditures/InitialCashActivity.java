package edu.ccm.tstites.personalexpenditures;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InitialCashActivity extends AppCompatActivity {

    private EditText edt_balance;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_cash);

        final SharedPreferences pref = getSharedPreferences("CashValues", MODE_PRIVATE);
        if (pref.getLong("CurrentCash", -1) != -1) {
            startActivity(new Intent(InitialCashActivity.this, MainActivity.class));
        }

        edt_balance = findViewById(R.id.edt_balance);

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putLong("CurrentCash", Double.doubleToLongBits(
                        Double.parseDouble(edt_balance.getText().toString())));
                editor.apply();

                startActivity(new Intent(InitialCashActivity.this, MainActivity.class));
            }
        });
    }
}
