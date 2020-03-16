package Datasource.DTOs;

public class TrackDTO {
    private String titel;
    private String performer;
    private String url;
    private int afspeelduur;

    public TrackDTO(String titel, String performer, String url, int afspeelduur, boolean offlineAvailable) {
        this.titel = titel;
        this.performer = performer;
        this.url = url;
        this.afspeelduur = afspeelduur;
        this.offlineAvailable = offlineAvailable;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAfspeelduur() {
        return afspeelduur;
    }

    public void setAfspeelduur(int afspeelduur) {
        this.afspeelduur = afspeelduur;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    private boolean offlineAvailable;
}
