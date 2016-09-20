package com.example.rm40300.democomponentsandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTel;
    private Button btLigar;
    private Button btContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTel = (EditText) findViewById(R.id.etTel);
        btLigar = (Button) findViewById(R.id.btLigar);
        btContatos = (Button) findViewById(R.id.btContatos);

        btLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ligar();
            }
        });

        btContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegarContatos();
            }
        });
    }

    private void ligar() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:" + etTel.getText().toString()));
            startActivity(phoneIntent);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    0);
        }
    }

    private void pegarContatos() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MainActivity.this, ListaContatosActivity.class);
            startActivityForResult(intent, 0);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                switch(resultCode) {
                    case RESULT_OK:
                        etTel.setText(data.getStringExtra("telefone"));
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ligar();
                } else {
                    Toast.makeText(MainActivity.this, ";( Não foi possível completar a ligação", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    pegarContatos();
                } else {
                    Toast.makeText(MainActivity.this, ";( Não foi possível obter os contatos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
