package com.example.binlin.pokemonretrofitworkshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.binlin.pokemonretrofitworkshop.MainActivity.POKEMON_NAME;

public class ResultsFragment extends Fragment {

    private String baseURL = "https://pokeapi.co/api/v2/";
    private Retrofit retrofit;
    private RetrofitPokemonApiCalls retrofitPokemonApiCalls;

    @BindView(R.id.pokemon_name_textView)
    protected TextView pokemonNameTextView;
    @BindView(R.id.pokemon_picture_image)
    protected ImageView pokemonImage;
    @BindView(R.id.pokemon_effect_textView)
    protected TextView pokemonEffectTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_results, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static ResultsFragment newInstance() {

        Bundle args = new Bundle();

        ResultsFragment fragment = new ResultsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void builtRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitPokemonApiCalls = retrofit.create(RetrofitPokemonApiCalls.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        String pokemonName = getArguments().getString(POKEMON_NAME);
        builtRetrofit();
        makeApiCallInfo(pokemonName);

    }

    private void makeApiCallInfo(String pokemonName) {
        retrofitPokemonApiCalls.getPokemonInfo(pokemonName).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonInfo>() {
            @Override
            public void onResponse(Call<RetrofitPokemonApiCalls.PokemonInfo> call, Response<RetrofitPokemonApiCalls.PokemonInfo> response) {
                if (response.isSuccessful()) {
                    pokemonNameTextView.setText(response.body().getName());
                    Glide.with(getView()).load(response.body().getSprite()).into(pokemonImage);
                    //makeApiCallEffect(response.body().getId());
                    makeApiCallEffect(response.body().getAbilitiesWhole());
                } else {
                    Toast.makeText(getActivity(), "Error While Query for Info, Try Again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitPokemonApiCalls.PokemonInfo> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Hit onFailure while searching info, check API info and network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void makeApiCallEffect(int id) {
//        retrofitPokemonApiCalls.getPokemonEffects(id).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonEffects>() {
//            @Override
//            public void onResponse(Call<RetrofitPokemonApiCalls.PokemonEffects> call, Response<RetrofitPokemonApiCalls.PokemonEffects> response) {
//                if (response.isSuccessful()) {
//                    pokemonEffectTextView.setText(response.body().getEffects());
//                } else {
//                    Toast.makeText(getActivity(), "Error While Query for Effects, Try Again!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RetrofitPokemonApiCalls.PokemonEffects> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(getActivity(), "Hit onFailure, check API info and network connection", Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private void makeApiCallEffect(String[] abilities) {
        for (int i = 0; i <= abilities.length - 1; i++) {
            retrofitPokemonApiCalls.getPokemonEffects(abilities[i]).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonEffects>() {
                @Override
                public void onResponse(Call<RetrofitPokemonApiCalls.PokemonEffects> call, Response<RetrofitPokemonApiCalls.PokemonEffects> response) {
                    if (response.isSuccessful()) {
                        pokemonEffectTextView.append(response.body().getEffects());
                        pokemonEffectTextView.append("\n\n");
                    } else {
                        Toast.makeText(getActivity(), "Error While Query for Effects, Try Again!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RetrofitPokemonApiCalls.PokemonEffects> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Hit onFailure while searching for effects, check API info and network connection", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}