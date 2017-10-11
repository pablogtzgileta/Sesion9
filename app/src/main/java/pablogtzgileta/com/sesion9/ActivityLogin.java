package pablogtzgileta.com.sesion9;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pablogtzgileta.com.sesion9.beans.User;

public class ActivityLogin extends AppCompatActivity {

    protected TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextInputEditText) findViewById(R.id.activity_login_username);
        password = (TextInputEditText) findViewById(R.id.activity_login_pwd);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.activity_login_signin:

                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.savePreferences(this);

                Intent intent = new Intent(this, ActivityMain.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
