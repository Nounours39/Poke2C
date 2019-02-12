package com.example.lucas.poke2c;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.CollectionPerso;

import java.util.List;

public class RecyclerViewAdapterPerso extends RecyclerView.Adapter<RecyclerViewAdapterPerso.ViewHolder> {
    private int recyclerItemRes; //id ressource items du recyclerView
    private List<CollectionPerso> data; // les données à afficher
    private Context context; // le contexte de l’application

    //Constructeur de notre classe
    public RecyclerViewAdapterPerso(int recyclerItemRes, List<CollectionPerso> data, Context context) {
        this.recyclerItemRes = recyclerItemRes;
        this.data = data;
        this.context = context;
    }

    //Méthode à surcharger : affichage de la vue via la classe viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Introduire ca dans le vue
        View view = LayoutInflater.from(parent.getContext()).inflate(recyclerItemRes, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //Méthode à surcharger : mappage du nom affiché en fonction de la place de l’élément
    @Override
    public void onBindViewHolder(RecyclerViewAdapterPerso.ViewHolder holder, int position) {

        holder.collection.setText(data.get(position).getNom());
        holder.categorie.setText(data.get(position).getCategorie().getCategorie());
        //holder.icon.setImageDrawable(null);
        holder.nbCarteAvoir.setText(data.get(position).nbCartePerso()+"");
        holder.nbCarteColl.setText(data.get(position).getNombre_max()+"");
    }

    //Méthode à surcharger : retourne la taille de la liste
    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    //Classe faisant le lien entre le composant de la vue et l’item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView collection;
        private TextView categorie;
        private TextView nbCarteAvoir;
        private TextView nbCarteColl;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            collection = itemView.findViewById(R.id.titleCollectionPerso);
            categorie = itemView.findViewById(R.id.titleCategoriePerso);
            icon = itemView.findViewById(R.id.iconCollectionPerso);
            nbCarteAvoir = itemView.findViewById(R.id.NbCarteAvoirPerso);
            nbCarteColl = itemView.findViewById(R.id.NbCarteCollectionPerso);
        }


        @Override
        public void onClick(View view) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Collection : ");
            alertDialog.setMessage(data.get(this.getAdapterPosition()).getNom());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            //Log.e("test",data.get((int) this.getAdapterPosition()).getLibVers());
        }
    }
}
