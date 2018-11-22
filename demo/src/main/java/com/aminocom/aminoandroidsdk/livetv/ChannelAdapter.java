package com.aminocom.aminoandroidsdk.livetv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aminocom.aminoandroidsdk.R;
import com.aminocom.sdk.model.client.channel.LiveChannel;

import java.util.ArrayList;
import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelViewHolder> {
    private List<LiveChannel> items = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false);

        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder viewHolder, int position) {
        viewHolder.bind(context, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<LiveChannel> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}