package com.example.footballapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapp.databinding.TeamItemBinding;
import com.example.footballapp.model.Datum;
import com.example.footballapp.util.RecyclerViewClickInterface;


public class FootballInfoListAdapter extends ListAdapter<Datum, FootballInfoListAdapter.FootballViewHolder> {

    private final RecyclerViewClickInterface recyclerViewClickInterface;

    public FootballInfoListAdapter(@NonNull DiffUtil.ItemCallback<Datum> diffCallback, RecyclerViewClickInterface recyclerViewClickInterface) {
        super(diffCallback);
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public FootballViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TeamItemBinding teamItemBinding = TeamItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new FootballViewHolder(teamItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FootballViewHolder holder, int position) {
        Datum item = getItem(position);
        holder.bind(item);
    }

    class FootballViewHolder extends RecyclerView.ViewHolder {

        private final TeamItemBinding teamItemBinding;

        public FootballViewHolder(@NonNull TeamItemBinding teamItemBinding) {
            super(teamItemBinding.getRoot());
            this.teamItemBinding = teamItemBinding;

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Datum datum = getItem(position);
                int teamId = datum.getTeamId();
                Log.i("result", String.valueOf(teamId));

                if (position != RecyclerView.NO_POSITION) {
                    if (recyclerViewClickInterface == null) {
                        Log.e("result", "recyclerViewClickInterface==null");
                    } else {
                        recyclerViewClickInterface.ontTeamClicked(teamId, view);
                    }
                }
            });
        }

        public void bind(Datum item) {
            teamItemBinding.setTeam(item);
            teamItemBinding.setCountry(item.getCountry());
            teamItemBinding.setImageUrl(item.getLogo());
        }
    }
}
