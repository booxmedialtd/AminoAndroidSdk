package com.aminocom.aminoandroidsdk.livetv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aminocom.aminoandroidsdk.R;
import com.aminocom.sdk.model.client.channel.Channel;

public class ChannelViewHolder extends RecyclerView.ViewHolder {
    private ImageView thumbnail;
    private TextView title;
    private TextView description;

    public ChannelViewHolder(@NonNull View view) {
        super(view);

        thumbnail = view.findViewById(R.id.channel_thumbnail);
        title = view.findViewById(R.id.channel_title);
        description = view.findViewById(R.id.channel_description);
    }

    public void bind(Channel channel) {
        title.setText(channel.getTitle());
        description.setText(channel.getDescription());
    }
}