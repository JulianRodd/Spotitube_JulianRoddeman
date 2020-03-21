package Controller.DataMappers;

import Controller.DTOs.TrackDTO;
import Domain.Lied;
import Domain.Track;
import Domain.Video;

public class TrackDataMapper {
    public Track mapToDomain(TrackDTO trackDTO) {
        Track track;
        if(trackDTO.getAlbum() == null) {
            track = new Video(trackDTO.getId(),trackDTO.getTitle(),null,trackDTO.getDuration(),trackDTO.isOfflineAvailable(),trackDTO.getPerformer(), trackDTO.getPublicationDate(),trackDTO.getDescription());
        }else{
            track = new Lied(trackDTO.getId(),trackDTO.getTitle(),null,trackDTO.getDuration(),trackDTO.isOfflineAvailable(),trackDTO.getPerformer(),trackDTO.getAlbum());
        }
        return track;
    }
    public TrackDTO mapToDTO(Track track){
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(track.getAfspeelduur());
        trackDTO.setId(track.getId());
        trackDTO.setOfflineAvailable(track.isOfflineBeschikbaar());
        trackDTO.setPerformer(track.getPerformer());
        trackDTO.setPlaycount(0);
        trackDTO.setTitle(track.getTitel());
        if(track instanceof Video) {
            trackDTO.setDescription(((Video) track).getBeschrijving());
            trackDTO.setPublicationDate(((Video) track).getPublicatieDatum());
        }else{
            trackDTO.setAlbum(((Lied)track).getAlbum());
        }
        return trackDTO;
    }
}
