package com.example.pertemuan4;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateDummyInstagrams();

    private static ArrayList<Instagram> generateDummyInstagrams() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("newton","Isaac Newton"
                ,"If I have seen further it is by standing on the shoulders of Giants."
                ,R.drawable.newtonpp ,R.drawable.newtonpost));

        instagrams1.add(new Instagram("einstein_1", "Albert Einsteint"
                ,"Weakness of attitude becomes weakness of character."
                ,R.drawable.einsteinpp,R.drawable.einsteinpost));

        instagrams1.add(new Instagram("galileo_lei", "Galileo Galilei"
                ,"Where the senses fail us, reason must step in."
                ,R.drawable.galileopp, R.drawable.galileopost));

        instagrams1.add((new Instagram("niko_tesla","Nikola Tesla"
                ,"The hard work of the future will be pushing buttons."
                ,R.drawable.teslapp,R.drawable.teslapost)));

        instagrams1.add(new Instagram("t.alva.edison", "Thomas Alva Edison"
                ,"I have not failed. I've just found 10,000 ways that won't work."
                ,R.drawable.edisonpp,R.drawable.edisonpost));

        return instagrams1;
    }
}
