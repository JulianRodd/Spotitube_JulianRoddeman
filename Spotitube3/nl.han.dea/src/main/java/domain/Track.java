package domain;

public abstract class Track {
    private int id;
    private String titel;
    private int afspeelduur;
    private boolean offlineBeschikbaar;
    private String performer;

    public Track(int id, String titel, int afspeelduur, boolean offlineBeschikbaar, String performer) {
        this.id = id;
        this.titel = titel;
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
