package com.andreakim.concertconcierge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by andreakim on 7/6/16.
 */
public class CardFragment extends android.support.v4.app.Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {


        View cardFragment = inflater.inflate(R.layout.fragment_card,container,false);



        return cardFragment;
    }


}
