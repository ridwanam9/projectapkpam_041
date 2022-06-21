package com.example.uaspam_041.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaspam_041.database.DBLightNovel;
import com.example.uaspam_041.HomeActivity;
import com.example.uaspam_041.MainActivity;
import com.example.uaspam_041.R;
import com.example.uaspam_041.LN;
import com.example.uaspam_041.edit_novel;

import java.util.ArrayList;
import java.util.HashMap;


public class LNAdapter extends RecyclerView.Adapter<LNAdapter.LNViewHolder>{

    private ArrayList<LN> listData;
    private Context control;

    public LNAdapter(ArrayList<LN> listData) {
        this.listData = listData;
    }

    @Override
    public LNAdapter.LNViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_novel,parent,false);
        control = parent.getContext();
        return new LNViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LNAdapter.LNViewHolder holder, int position) {

        String judul,penulis, sinopsis, id_novel;

        id_novel = listData.get(position).getId_novel();
        judul = listData.get(position).getJudul();
        penulis = listData.get(position).getPenulis();
        sinopsis = listData.get(position).getSinopsis();
        DBLightNovel bookstore = new DBLightNovel(control);

        holder.judulTxt.setText(judul);
        holder.judulTxt.setTextSize(30);
        holder.judulTxt.setTextColor(Color.BLUE);
        holder.penulisTxt.setText(penulis);
        holder.sinopsisTxt.setText(sinopsis);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popmenu = new PopupMenu(control, holder.cardku);
                popmenu.inflate(R.menu.popupmenu);
                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                Intent i = new Intent(control, edit_novel.class);
                                i.putExtra("id_novel",id_novel);
                                i.putExtra("judul",judul);
                                i.putExtra("penulis",penulis);
                                i.putExtra("sinopsis",sinopsis);
                                control.startActivity(i);
                                break;

                            case R.id.hapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id_novel", id_novel);
                                bookstore.deleteData(values);
                                Intent j = new Intent(control, HomeActivity.class);
                                control.startActivity(j);
                                break;
                        }
                        return true;
                    }
                });
                popmenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)? listData.size() : 0;
    }

    public class LNViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView judulTxt, penulisTxt, sinopsisTxt;
        public LNViewHolder(View view) {
            super(view);
            cardku = (CardView)view.findViewById(R.id.kartuku);
            judulTxt = (TextView) view.findViewById(R.id.textJudul);
            penulisTxt = (TextView) view.findViewById(R.id.textPenulis);
            sinopsisTxt = (TextView) view.findViewById(R.id.textSinopsis);

            cardku.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
        }
    }
}


