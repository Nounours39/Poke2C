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

import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.List;

public class RecyclerViewAdapterColl extends RecyclerView.Adapter<RecyclerViewAdapterColl.ViewHolder> {
    private int recyclerItemRes; //id ressource items du recyclerView
    private List<CollectionN> data; // les données à afficher
    private Context context; // le contexte de l’application

    //Constructeur de notre classe
    public RecyclerViewAdapterColl(int recyclerItemRes, List<CollectionN> data, Context context) {
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
    public void onBindViewHolder(RecyclerViewAdapterColl.ViewHolder holder, int position) {

        holder.collection.setText(data.get(position).getNom());
        holder.categorie.setText(data.get(position).getCategorie().getCategorie());
        //holder.icon.setImageDrawable(null);
        holder.nbCarteAvoir.setText(data.get(position).nbCarte()+"");
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
            collection = itemView.findViewById(R.id.titleCollection);
            categorie = itemView.findViewById(R.id.titleCategorie);
            icon = itemView.findViewById(R.id.iconCollection);
            nbCarteAvoir = itemView.findViewById(R.id.NbCarteAvoir);
            nbCarteColl = itemView.findViewById(R.id.NbCarteCollection);
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
