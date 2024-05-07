package com.example.pertemuan5;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams =generateDummyInstagram();

    private static ArrayList<Instagram> generateDummyInstagram() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("da_vinci", "Leonardo Da Vinci", "Mona Lisa, En el libro que conmemora los 500 años de su muerte, la editorial Taschen reúne toda la obra pictórica, existente y perdida, de Leonardo Da Vinci."
                ,R.drawable.davinci, R.drawable.davincipost));

        instagrams1.add(new Instagram("affandi", "Affandi Koesoema", "View Eiffel Tower By Affandi; oil on canvas; 125.5 by 100cm.; 49¼ by 39¼in.; . Access more artwork lots and estimated & realized auction prices on MutualArt."
                ,R.drawable.affandi, R.drawable.affandipost));

        instagrams1.add(new Instagram("picasso", "Pablo Ruiz Picasso", "Renowned paintings including his masterpieces in Cubism and those made during the Blue Period."
                ,R.drawable.picasso, R.drawable.picassopost));

        instagrams1.add(new Instagram("raden_saleh", "Saleh Sjarif Boestaman", "De gevangenneming van Diponegoro, door Raden Saleh"
                ,R.drawable.rsaleh, R.drawable.rsalehpost));

        instagrams1.add(new Instagram("v_gogh", "Vincent Willem van Gogh", "A Starry Night Van Gogh Mountain Inspiration Products Sold On Redbubble Clothing, Home Décor, Mugs and More"
                ,R.drawable.vgogh, R.drawable.vgoghpost));

        return instagrams1;

    }

}
