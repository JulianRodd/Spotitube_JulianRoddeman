package Controller.DataMappers;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.TrackDTO;
import Domain.Afspeellijst;
import Domain.Track;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AfspeellijstDataMapper {
    private TrackDataMapper trackDM = new TrackDataMapper();


    public Afspeellijst mapToDomain(AfspeellijstDTO afspeellijstDTO) {
        Afspeellijst afspeellijst;
            afspeellijst = new Afspeellijst(afspeellijstDTO.getId(), afspeellijstDTO.getName(), "gebruiker0");
        List<Track> domainTracks = new ArrayList<Track>();
        if(afspeellijstDTO.getTracks()!= null) {
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
        for (Track track : afspeellijst.getTracks()) {
            trackDTOs.add(trackDM.mapToDTO(track));
        }
        afspeellijstDTO.setTracks(trackDTOs);

        return afspeellijstDTO;
    }
}
