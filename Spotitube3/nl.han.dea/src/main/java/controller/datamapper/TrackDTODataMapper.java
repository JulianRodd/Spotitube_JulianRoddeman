package controller.datamapper;

import controller.dtos.TrackDTO;
import domain.Lied;
import domain.Track;
import domain.Video;

public class TrackDTODataMapper {
    public Track mapToDomain(TrackDTO trackDTO) {
        Track track;
        if (trackDTO.getAlbum() == null) {
            track = new Video(trackDTO.getId(), trackDTO.getTitle(), trackDTO.getDuration(), trackDTO.isOfflineAvailable(), trackDTO.getPerformer(), trackDTO.getPublicationDate(), trackDTO.getDescription(), trackDTO.getPlaycount());
        } else {
            track = new Lied(trackDTO.getId(), trackDTO.getTitle(), trackDTO.getDuration(), trackDTO.isOfflineAvailable(), trackDTO.getPerformer(), trackDTO.getAlbum());
        }
        return track;
    }

    public TrackDTO mapToDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(track.getAfspeelduur());
        trackDTO.setId(track.getId());
        trackDTO.setOfflineAvailable(track.isOfflineBeschikbaar());
        trackDTO.setPerformer(track.getPerformer());
        trackDTO.setTitle(track.getTitel());
        if (track instanceof Video) {
            trackDTO.setDescription(((Video) track).getBeschrijving());
            trackDTO.setPublicationDate(((Video) track).getPublicatieDatum());
            trackDTO.setPlaycount(((Video) track).getAantalWeergaven());
        } else {
            trackDTO.setAlbum(((Lied) track).getAlbum());
        }
        return trackDTO;
    }
}
