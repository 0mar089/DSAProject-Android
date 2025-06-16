package com.example.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.Clases.UsersScoreResponse;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {
    private Context context;
    private List<UsersScoreResponse> rankingList;

    public RankingAdapter(Context context, List<UsersScoreResponse> rankingList) {
        this.context = context;
        this.rankingList = rankingList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsuario, txtScore, txtPosicion;
        RelativeLayout circleLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsuario = itemView.findViewById(R.id.usernameTxtRanking);
            txtScore = itemView.findViewById(R.id.RecordTxt);
            txtPosicion = itemView.findViewById(R.id.positionTxt);
            circleLayout = itemView.findViewById(R.id.positionCircle);
        }
    }

    @NonNull
    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_row_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingAdapter.ViewHolder holder, int position) {
        UsersScoreResponse user = rankingList.get(position);
        int displayPosition = position + 1;

        holder.txtUsuario.setText(user.getUsername());
        holder.txtScore.setText("Score: " + user.getScore());
        holder.txtPosicion.setText(String.valueOf(displayPosition));

        // Cambia el fondo del círculo según la posición
        switch (displayPosition) {
            case 1:
                holder.circleLayout.setBackgroundResource(R.drawable.circle_gold);
                break;
            case 2:
                holder.circleLayout.setBackgroundResource(R.drawable.circle_silver);
                break;
            case 3:
                holder.circleLayout.setBackgroundResource(R.drawable.circle_bronze);
                break;
            default:
                holder.circleLayout.setBackgroundResource(R.drawable.circle_gray);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return rankingList.size();
    }
}
