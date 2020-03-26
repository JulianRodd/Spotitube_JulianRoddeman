package domain;

import datasource.daos.TrackDAO;

import javax.inject.Inject;

public abstract class Track {
    private int id;
    private String titel;
    private String url;
    private int afspeelduur;
    private int resterendeTrackTijd;
    private boolean offlineBeschikbaar;
    private String performer;

    public Track(int id, String titel, String url, int afspeelduur, boolean offlineBeschikbaar, String performer) {
        this.id = id;
        this.titel = titel;
        this.url = url;
        this.afspeelduur = afspeelduur;
        this.offlineBeschikbaar = offlineBeschikbaar;
        this.performer = performer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitel() {
        return titel;
    }
    public String getUrl() {
        return url;
    }
    public int getAfspeelduur() {
        return afspeelduur;
    }
    public boolean isOfflineBeschikbaar() {
        return offlineBeschikbaar;
    }
    public String getPerformer() {
        return performer;
    }

}
