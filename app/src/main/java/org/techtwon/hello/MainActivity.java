package org.techtwon.hello;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    EditText edittext;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice04);

        edittext = findViewById(R.id.input);
        textview = findViewById(R.id.textcount);

        Button sendButton = findViewById(R.id.sendbutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edittext.getText().toString();
                Toast.makeText(getApplicationContext(), "전송할 메시지\n\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        Button closeButton = findViewById(R.id.closebutton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                byte[] bytes = null;
                try {
                    bytes = s.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    textview.setText(strCount + " / 80 바이트");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                try {
                    byte[] strBytes = str.getBytes("KSC5601");
                    if (strBytes.length > 80) {
                        s.delete(s.length() - 2, s.length() - 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        edittext.addTextChangedListener(watcher);
    }
}