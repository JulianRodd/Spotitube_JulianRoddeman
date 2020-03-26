package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.AfspeellijstenDTO;
import domain.Afspeellijst;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AfspeellijstenDTODataMapper {
    private AfspeellijstDTODataMapper afspeellijstDTODataMapper;
    private Afspeellijst afspeellijst;

    @Inject
    public void setAfspeellijst(Afspeellijst afseellijst) {
        this.afspeellijst = afseellijst;
    }

    @Inject
    public void setAfspeellijstDTODataMapper(AfspeellijstDTODataMapper afspeellijstDTODataMapper) {
        this.afspeellijstDTODataMapper = afspeellijstDTODataMapper;
    }

    public AfspeellijstenDTO mapToDTO(List<Afspeellijst> afspeellijsten) {
        List<AfspeellijstDTO> afspeellijstDTOs = new ArrayList<AfspeellijstDTO>();
        int lengte = 0;
        for (Afspeellijst afspeellijst : afspeellijsten) {
            afspeellijstDTOs.add(afspeellijstDTODataMapper.mapToDTO(afspeellijst));
            lengte += this.afspeellijst.berekenAfspeellijstLengte(afspeellijst.getId());
        }
        AfspeellijstenDTO afspeellijstenDTO = new AfspeellijstenDTO();
        afspeellijstenDTO.setPlaylists(afspeellijstDTOs);
        afspeellijstenDTO.setLength(lengte);
        return afspeellijstenDTO;
    }
}
