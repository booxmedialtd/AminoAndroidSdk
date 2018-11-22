package com.aminocom.aminoandroidsdk.livetv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aminocom.aminoandroidsdk.R;
import com.aminocom.sdk.model.client.channel.LiveChannel;
import com.bumptech.glide.Glide;

class ChannelViewHolder extends RecyclerView.ViewHolder {
    private final ImageView thumbnail;
    private final TextView title;
    private final TextView currentProgram;
    private final TextView nextProgram;
    private final TextView afterNextProgram;

    ChannelViewHolder(@NonNull View view) {
        super(view);

        thumbnail = view.findViewById(R.id.channel_thumbnail);
        title = view.findViewById(R.id.channel_title);
        currentProgram = view.findViewById(R.id.channel_current_program);
        nextProgram = view.findViewById(R.id.channel_next_program);
        afterNextProgram = view.findViewById(R.id.channel_after_next_program);
    }

    void bind(Context context, LiveChannel channel) {
        if(channel.getChannel().getThumbnails().size() > 0) {
            Glide.with(context).load(channel.getChannel().getThumbnails().get(0).getUrl()).into(thumbnail);
        }

        title.setText(channel.getChannel().getTitle());

        switch (channel.getPendingPrograms().size()) {
            case 1:
                currentProgram.setText(channel.getPendingPrograms().get(0).getTitle());
                nextProgram.setVisibility(View.GONE);
                afterNextProgram.setVisibility(View.GONE);
                break;

            case 2:
                currentProgram.setText(channel.getPendingPrograms().get(0).getTitle());
                nextProgram.setText(channel.getPendingPrograms().get(1).getTitle());
                afterNextProgram.setVisibility(View.GONE);
                break;

            case 3:
                currentProgram.setText(channel.getPendingPrograms().get(0).getTitle());
                nextProgram.setText(channel.getPendingPrograms().get(1).getTitle());
                afterNextProgram.setText(channel.getPendingPrograms().get(2).getTitle());
                break;
        }
    }
}