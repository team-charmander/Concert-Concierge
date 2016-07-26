package com.andreakim.concertconcierge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spoorthi on 7/25/16.
 */
public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ViewHolder>{

    private ArrayList<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> concerts;

    public ConcertAdapter(ArrayList<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> concerts){
        this.concerts=concerts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_rows,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_name_artist.setText(concerts.get(position).getMetroArea().getDisplayName());
        holder.tv_date.setText(concerts.get(position).getCity().toString());
        holder.tv_venue.setText(concerts.get(position).getMetroArea().toString());

    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name_artist, tv_date, tv_venue;
        public ViewHolder(View view) {
            super(view);

            tv_name_artist = (TextView)view.findViewById(R.id.concert_artist);
            tv_date = (TextView)view.findViewById(R.id.concert_date);
            tv_venue = (TextView)view.findViewById(R.id.concert_venue);
        }
    }
}
