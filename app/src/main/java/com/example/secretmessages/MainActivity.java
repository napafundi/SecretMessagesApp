package com.example.secretmessages;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    EditText txtIn;
    EditText txtKey;
    EditText txtOut;
    SeekBar sb;
    Button btn;
    public String encode( String message, int keyVal ) {
        String output = "";
        char key = (char) keyVal;
        for (int x = 0; x < message.length(); x++) {
            char input = message.charAt(x);
            if (input >= 'A' && input <= 'Z') {
                input += key;
                if (input > 'Z') {
                    input -= 26;
                }
                if (input < 'A') {
                    input += 26;
                }
            } else if (input >= 'a' && input <= 'z') {
                input += key;
                if (input > 'z') {
                    input -= 26;
                }
                if (input < 'a') {
                    input += 26;
                }
            } else if (input >= '0' && input<= '9') {
                input += (keyVal % 10);
                if (input > '9') {
                    input -= 10;
                }
                if (input < '0') {
                    input += 10;
                }
            }
            output += input;
        }
        return output;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtIn = (EditText)findViewById(R.id.txtIn);
        txtOut = (EditText)findViewById(R.id.txtOut);
        txtKey = (EditText)findViewById(R.id.txtKey);
        sb = (SeekBar)findViewById(R.id.seekBar);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int key = Integer.parseInt(txtKey.getText().toString());
                String message = txtIn.getText().toString();
                String output = encode(message, key);
                txtOut.setText(output);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
