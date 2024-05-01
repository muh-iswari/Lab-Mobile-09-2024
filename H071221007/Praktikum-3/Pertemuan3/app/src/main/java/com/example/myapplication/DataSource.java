package com.example.myapplication;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateDummyInstagrams();

    private static ArrayList<Instagram> generateDummyInstagrams() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("nba", "The @nuggets take 1-0 series lead game with 114-103 home", R.drawable.nba, R.drawable.nba, R.drawable.nba,"87,7 JT", "1.159"));
        instagrams1.add(new Instagram("justinhubner5", "sh**too easy", R.drawable.hubner, R.drawable.hubner_post, R.drawable.hubner_post, "13.5 JT", "6452"));
        instagrams1.add(new Instagram("oplovers", "Para gorosai gais", R.drawable.op, R.drawable.opstory, R.drawable.oppost, "1.3 JT", "172"));
        instagrams1.add(new Instagram("mcqueen95", "Piston cup day 1, 1st", R.drawable.mcqueenpp, R.drawable.mcqueenpost, R.drawable.mcqueen, "61.9 JT", "120"));
        instagrams1.add(new Instagram("naga_imut", "naga, yang penting naga '.'", R.drawable.dragon, R.drawable.dragonpost, R.drawable.dragonstory, "1.2 JT", "5"));
        instagrams1.add(new Instagram("yo_kai", "Kappa adalah yokai yang berwujud manusia kura-kura", R.drawable.yokai, R.drawable.yokaipost, R.drawable.yokaistory, "100", "9.7 JT"));
        instagrams1.add(new Instagram("nolan_asasin", "Mclaren lu warna apa boss?", R.drawable.nolan, R.drawable.nolanpost, R.drawable.nolanstory, "4.1 JT", "20"));
        instagrams1.add(new Instagram("rojerbangkok", "By one sini dek dek", R.drawable.ayam, R.drawable.ayampost, R.drawable.ayamstory, "3.4 JT", "321"));
        instagrams1.add(new Instagram("gareccing_city", "suasana padi hari di Honto, Gareccing", R.drawable.gareccingpp, R.drawable.gareccingcitypos, R.drawable.gareccingstory, "3 RB", "52"));
        instagrams1.add(new Instagram("random", "pemandangan pagi hari di bikeru", R.drawable.randompp, R.drawable.randompost, R.drawable.randomstory, "241", "127"));
        return instagrams1;
    }



}
