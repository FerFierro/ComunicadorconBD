package com.escom.tt2016.comunicadorconbd;

import com.escom.tt2016.comunicadorconbd.R;

public class Utilidades {




    public static int getBackground(int categoria){
        int d;

        switch (categoria){
            case 1:
                d = R.drawable.pic_border_alimentos;
                break;
            case 2:
                d = R.drawable.pic_border_animales;
                break;
            case 3:
                d = R.drawable.pic_border_familia;
                break;
            case 4:
                d = R.drawable.pic_border_profesiones;
                break;
            case 5:
                d = R.drawable.pic_border_estados_animos;
                break;
            case 6:
                d = R.drawable.pic_border_sociales;
                break;
            default:
                d = R.drawable.pic_border;
                break;
        }

        return d;
    }



}
