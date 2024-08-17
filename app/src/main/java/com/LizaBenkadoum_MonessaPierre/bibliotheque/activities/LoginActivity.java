package com.LizaBenkadoum_MonessaPierre.bibliotheque.activities;

import com.LizaBenkadoum_MonessaPierre.bibliotheque.R;
import com.LizaBenkadoum_MonessaPierre.bibliotheque.presentateur.LoginPresenter;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginPresenter presenter;
    private EditText email;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenter = new LoginPresenter(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        presenter.login(emailText, passwordText);
    }

    public void onLoginSuccess() {
        // Navigate to the main activity
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailure() {
        // Show a failure message
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }
}