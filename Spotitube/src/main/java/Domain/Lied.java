package Domain;


import Controller.DTOs.TrackDTO;

public class Lied extends Track {

	private String album;

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Lied(int id, String titel, String url, int afspeelduur, boolean offlineAvailable, String performer, String album) {
		super(id, titel, url, afspeelduur, offlineAvailable, performer);
		this.album = album;
	}



	@Override
	public void speelAf() {

	}

	@Override
	public int getResterendeTijd() {
		return 0;
	}

	@Override
	public void stopSpelen() {

	}
	public TrackDTO mapToDTO() {
		TrackDTO trackDTO = new TrackDTO();

		trackDTO.setDuration(super.getAfspeelduur());
		trackDTO.setId(super.getId());
		trackDTO.setOfflineAvailable(super.isOfflineAvailable());
		trackDTO.setPerformer(super.getPerformer());
		trackDTO.setPlaycount(0);
		trackDTO.setAlbum(album);
		trackDTO.setTitle(super.getTitel());
		return trackDTO;
	}
}
