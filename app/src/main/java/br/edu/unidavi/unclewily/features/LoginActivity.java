package br.edu.unidavi.unclewily.features;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.unidavi.unclewily.R;
import br.edu.unidavi.unclewily.data.Session;
import br.edu.unidavi.unclewily.web.WebTaskLogin;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonEnter = findViewById(R.id.entrar);
        buttonEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tryLogin();
                    }
                }
        );


        Session session = new Session(this);
        editTextLogin = findViewById(R.id.login);
        editTextLogin.setText(session.getEmailInSession());

        editTextPassword = findViewById(R.id.senha);
        editTextPassword.requestFocus();
    }

    public void tryLogin(){
        String emailValue = editTextLogin.getText().toString();
        String passwordValue = editTextPassword.getText().toString();

        showDialog();

        WebTaskLogin task = new WebTaskLogin(this,emailValue,
                passwordValue);
        task.execute();

    }

    public void showDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(
                R.string.aguarde));
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }
}
