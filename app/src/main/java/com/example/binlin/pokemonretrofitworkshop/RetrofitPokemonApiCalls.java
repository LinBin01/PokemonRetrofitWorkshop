package com.example.binlin.pokemonretrofitworkshop;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("pokemon/{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String name);

    @GET("ability/{id}")
    Call<PokemonEffects> getPokemonEffects(@Path("id") int id);

    class PokemonInfo {
        @SerializedName("name")
        private String name;

        @SerializedName("id")
        private int id;

        @SerializedName("sprites")
        private PokemonSprites sprites;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getSprite() {
            return sprites.getImageUrl();
        }

        class PokemonSprites {

            @SerializedName("front_default")
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

        }
    }

    class PokemonEffects {
        @SerializedName("effect_entries")
        private List<PokemonEffectsEntries> effectsList;

        public String getEffects() {
            return effectsList.get(0).getEffect();
        }

        class PokemonEffectsEntries {
            @SerializedName("effect")
            private String effect;

            public String getEffect() {
                return effect;
            }
        }
    }
}
