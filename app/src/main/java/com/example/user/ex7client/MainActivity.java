package com.example.user.ex7client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String ACTION_REGISTER = "com.ex7.register";
    public static final int REGISTER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonOptions(View v) {
        switch (v.getId()) {
            case R.id.buttonCall: {
                String number = ((EditText) findViewById(R.id.etNumber)).getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                }
                break;
            }
            case R.id.buttonSurf: {
                String url = ((EditText) findViewById(R.id.etSurf)).getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
                startActivity(i);
                break;
            }
            case R.id.buttonMail: {
                String email = ((EditText) findViewById(R.id.etMail)).getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, "hello from ex7Client");
                i.putExtra(Intent.EXTRA_TEXT, "What's up?");
                i.setType("plain/text");
                startActivity(i);
                break;
            }
            case R.id.buttonRegister: {
                String name = ((EditText) findViewById(R.id.etRegister)).getText().toString();
                Intent i = new Intent(ACTION_REGISTER);
                startActivityForResult(i,REGISTER_REQUEST);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String firstName = "";
        String lastName = "";
        String gender = "";
        if(requestCode==REGISTER_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                firstName = data.getExtras().getString("FirstName");
                lastName = data.getExtras().getString("LastName");
                gender = data.getExtras().getString("Gender");
                String hello= "Hello" + " " +  gender + " " + firstName + " " + lastName;
               ((EditText)findViewById(R.id.etRegister)).setText(hello);
            }
        }
    }
}
