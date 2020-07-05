package com.example.inputdatapenjual;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLihatPenjual extends
        RecyclerView.Adapter<AdapterLihatPenjual.ViewHolder> {

    private ArrayList<Penjual> daftarPenjual;
    private Context context;



    public AdapterLihatPenjual(ArrayList<Penjual> penjuals, Context ctx){
        daftarPenjual = penjuals;
        context = ctx;


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namapenjual);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penjual, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarPenjual.get(position).getNama();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(ReadData.getActIntent((Activity) context).putExtra("data", daftarPenjual.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                context.startActivity(TambahData.getActIntent((Activity) context).putExtra("data", daftarPenjual.get(position)));
                            }
                        }
                );




                return true;
            }
        });
        holder.tvTitle.setText(name);
    }
    @Override
    public int getItemCount() {
        return daftarPenjual.size();
    }
    }



