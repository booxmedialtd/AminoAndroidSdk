package com.aminocom.sdk.model.client.channel;

public class FastForwardState {
    private boolean fastForwardPvr;
    private boolean fastForwardCatchUp;
    private boolean fastForwardStartOver;

    public boolean isFastForwardPvr() {
        return fastForwardPvr;
    }

    public void setFastForwardPvr(boolean fastForwardPvr) {
        this.fastForwardPvr = fastForwardPvr;
    }

    public boolean isFastForwardCatchUp() {
        return fastForwardCatchUp;
    }

    public void setFastForwardCatchUp(boolean fastForwardCatchUp) {
        this.fastForwardCatchUp = fastForwardCatchUp;
    }

    public boolean isFastForwardStartOver() {
        return fastForwardStartOver;
    }

    public void setFastForwardStartOver(boolean fastForwardStartOver) {
        this.fastForwardStartOver = fastForwardStartOver;
    }
}