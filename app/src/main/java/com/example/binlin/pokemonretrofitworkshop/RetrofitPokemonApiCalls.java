package com.example.binlin.pokemonretrofitworkshop;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("pokemon/{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String name);

//    @GET("ability/{id}")
//    Call<PokemonEffects> getPokemonEffects(@Path("id") int id);

    @GET("ability/{abilityName}")
    Call<PokemonEffects> getPokemonEffects(@Path("abilityName") String abilityName);

    class PokemonInfo {
        @SerializedName("name")
        private String name;

//        @SerializedName("id")
//        private int id;

        @SerializedName("abilities")
        private List<PokemonAbilities> abilitiesList;

        @SerializedName("sprites")
        private PokemonSprites sprites;

        public String getName() {
            return name;
        }

//        public int getId() {
//            return id;
//        }

        public String getSprite() {
            return sprites.getImageUrl();
        }

        public String[] getAbilitiesWhole() {
            String[] temp = new String[abilitiesList.size()];
            for (int i = temp.length - 1; i >= 0; i--) {
                temp[i] = abilitiesList.get(i).getAbility();
            }
            return temp;
        }

        class PokemonSprites {

            @SerializedName("front_default")
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

        }

        class PokemonAbilities {
            @SerializedName("ability")
            private Ability ability;

            public String getAbility() {
                return ability.getAbilityName();
            }

            class Ability {
                @SerializedName("name")
                private String abilityName;

                public String getAbilityName() {
                    return abilityName;
                }
            }
        }
    }

    class PokemonEffects {
        @SerializedName("effect_entries")
        private List<PokemonEffectsEntries> effectsList;

        @SerializedName("name")
        private String effectName;

        public String getEffects() {
            return effectsList.get(0).getEffect();
        }

        public String getEffectName() {
            return effectName;
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
