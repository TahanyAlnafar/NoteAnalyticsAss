package com.example.noteanalyticsass;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Notes> nData;
    private LayoutInflater iInflater;
    private NoteAdapter.ItemClickListener nClickListener;

    NoteAdapter(Context context, List<Notes> data , NoteAdapter.ItemClickListener onClick) {
        this.iInflater = LayoutInflater.from(context);
        this.nData = data;
        this.nClickListener = onClick;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = iInflater.inflate(R.layout.cat_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.category.setText(nData.get(position).getName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nClickListener.onItemClick(holder.getAdapterPosition(), nData.get(position).id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return nData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView category;
        public CardView card;
        ViewHolder(View itemView) {
            super(itemView);
            this.category = itemView.findViewById(R.id.title);
            this.card = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    Notes getItem(int id) {
        return nData.get(id);
    }

    void setClickListener(NoteAdapter.ItemClickListener itemClickListener) {
        this.nClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, String id);
    }
}