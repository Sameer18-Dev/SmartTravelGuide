package com.sameer18dev.smarttravelguide;

public class YoutubeVideo {

    private String videoUrl, vdesp, vtitle;

    public YoutubeVideo(String videoUrl, String vdesp, String vtitle) {
        this.videoUrl = videoUrl;
        this.vdesp = vdesp;
        this.vtitle = vtitle;
    }

    public String getVdesp() {
        return vdesp;
    }

    public String getVtitle() {
        return vtitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
