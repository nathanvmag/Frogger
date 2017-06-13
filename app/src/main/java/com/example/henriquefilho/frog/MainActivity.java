package com.example.henriquefilho.frog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FrogView(this));
    }
    @Override
    protected void onPause()
    {
        super.onPause();
       // Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        if (FrogView.letters.size()==3)Toast.makeText(this,"Você ja obteve todas as letras",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
       // Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
       // Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
