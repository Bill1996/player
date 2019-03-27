package vvfgaa.player;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder> {

    private List<Playsource>playsources;

    PlayAdapter(List<Playsource>playsources){
        this.playsources=playsources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(playsources.get(position).name);
        holder.sharpness.setText(playsources.get(position).sharpness);
        holder.itemView.setOnClickListener((v)->{
            Intent intent=new Intent(holder.itemView.getContext(),PlayActivity.class);
            intent.putExtra("name",playsources.get(position).name);
            intent.putExtra("addr",playsources.get(position).addr);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return playsources.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView sharpness;

         ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            sharpness = itemView.findViewById(R.id.sharpness);
        }
    }
}
