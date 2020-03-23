package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import domain.Afspeellijst;
import domain.Track;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AfspeellijstDataMapper {
    @Inject
    public void setTrackDM(TrackDataMapper trackDM) {
        this.trackDM = trackDM;
    }

    private TrackDataMapper trackDM;


    public Afspeellijst mapToDomain(AfspeellijstDTO afspeellijstDTO) {
        Afspeellijst afspeellijst;
        afspeellijst = new Afspeellijst();
        afspeellijst.setId(afspeellijstDTO.getId());
        afspeellijst.setNaam(afspeellijstDTO.getName());
        afspeellijst.setEigenaar("gebruiker0");
        List<Track> domainTracks = new ArrayList<Track>();
        if (afspeellijstDTO.getTracks() != null) {
            for (TrackDTO track : afspeellijstDTO.getTracks()) {
                domainTracks.add(trackDM.mapToDomain(track));
            }
        }
        afspeellijst.setTracks(domainTracks);
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
        for (Track track : afspeellijst.openTracksVoorAfspeellijst()) {
            trackDTOs.add(trackDM.mapToDTO(track));
        }
        afspeellijstDTO.setTracks(trackDTOs);

        return afspeellijstDTO;
    }
}
