package br.edu.unidavi.unclewily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.unidavi.unclewily.features.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void teste(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}