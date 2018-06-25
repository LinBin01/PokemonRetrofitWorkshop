package com.example.binlin.pokemonretrofitworkshop;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("pokemon/{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String name);

    @GET("pokemon/{id}")
    Call<PokemonEffects> getPokemonEffects(@Path("id") int id);

    class PokemonInfo {
        @SerializedName("name")
        private String name;

        @SerializedName("id")
        private int id;

        @SerializedName("sprite")
        private ImageView sprite;

        public  String getName() {
            return name;
        }
        public  int getId() {
            return id;
        }
        public Drawable getSprite() {
            return sprite.getDrawable();
        }
    }

    class PokemonEffects {
        @SerializedName("effects")
        private String effects;

        public String getEffects() {
            return effects;
        }
    }
}
