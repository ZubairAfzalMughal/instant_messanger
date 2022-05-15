package com.example.instant_messanger.message_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instant_messanger.R;
import com.example.instant_messanger.messages_data.Messages;

import java.util.ArrayList;

public class MyAdapater extends RecyclerView.Adapter<MyAdapater.ViewHolder> {

    private final ArrayList<Messages>messages;
    public MyAdapater(ArrayList<Messages>messages){
        this.messages=messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_field, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getEmail().setText(messages.get(position).getEmail());
        viewHolder.getMessage().setText(messages.get(position).getMessage());
        viewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView message;
        private final TextView email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message=itemView.findViewById(R.id.message);
            email=itemView.findViewById(R.id.user_email);
        }

        public TextView getMessage() {
            return message;
        }

        public TextView getEmail() {
            return email;
        }
    }

}
