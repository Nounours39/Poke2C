package com.example.lucas.poke2c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.poke2c.model.Information;

import java.util.List;

public class InfoAdapter extends ArrayAdapter<Information> {

    public InfoAdapter(Context context, List<Information> infos){
        super(context,0,infos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.info,parent,false);
        }

        InfoViewHolder viewHolder = (InfoViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new InfoViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.Titre);
            viewHolder.description = (TextView) convertView.findViewById(R.id.Description);
            viewHolder.date = (TextView) convertView.findViewById(R.id.Date);
            convertView.setTag(viewHolder);
        }

        Information info = getItem(position);
        viewHolder.titre.setText(info.getTitre());
        viewHolder.description.setText(info.getDescription());
        viewHolder.date.setText(info.getDate_creation());

        return convertView;
    }

    public class InfoViewHolder{
        public TextView titre;
        public TextView description;
        public TextView date;
    }
}
