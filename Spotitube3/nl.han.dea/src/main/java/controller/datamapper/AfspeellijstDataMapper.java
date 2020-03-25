package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import domain.Afspeellijst;
import domain.Track;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AfspeellijstDataMapper {
    private TrackDataMapper trackDataMapper;
    @Inject
    public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    public Afspeellijst mapToDomain(AfspeellijstDTO afspeellijstDTO) {
        Afspeellijst afspeellijst = new Afspeellijst();
        afspeellijst.setId(afspeellijstDTO.getId());
        afspeellijst.setNaam(afspeellijstDTO.getName());
        if(afspeellijstDTO.getOwner()){
            afspeellijst.setEigenaar("gebruiker0");
        }
        if (afspeellijstDTO.getTracks() != null) {
            List<Track> domainTracks = new ArrayList<Track>();
            for (TrackDTO track : afspeellijstDTO.getTracks()) {
                domainTracks.add(trackDataMapper.mapToDomain(track));
            }
            afspeellijst.setTracks(domainTracks);
        }
        return afspeellijst;
    }

    public AfspeellijstDTO mapToDTO(Afspeellijst afspeellijst) {
        AfspeellijstDTO afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstDTO.setId(afspeellijst.getId());
        if (afspeellijst.getEigenaar() != null) {
            afspeellijstDTO.setOwner(true);
        } else {
            afspeellijstDTO.setOwner(false);
        }
        afspeellijstDTO.setName(afspeellijst.getNaam());
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        List<Track> tracks = afspeellijst.getTracks();
        for (Track track : tracks ) {
            trackDTOs.add(trackDataMapper.mapToDTO(track));
        }
        afspeellijstDTO.setTracks(trackDTOs);

        return afspeellijstDTO;
    }
}
