package com.example.binlin.pokemonretrofitworkshop;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pokemon_name_editText)
    protected TextInputEditText pokemonName;
    private ResultsFragment resultsFragment;

    public static final String POKEMON_NAME = "pokemon name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.capture_button)
    protected void captureClicked(){
        if(pokemonName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill out the pokemon's name", Toast.LENGTH_LONG).show();
        }else{
            resultsFragment = ResultsFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(POKEMON_NAME, pokemonName.getText().toString());
            resultsFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, resultsFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(resultsFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().remove(resultsFragment).commit();
        }else{
            super.onBackPressed();
        }
    }
}
