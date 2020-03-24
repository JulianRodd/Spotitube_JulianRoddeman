package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.AfspeellijstenDTO;
import domain.Afspeellijst;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SpotitubeDataMapper {
    private AfspeellijstDataMapper afspeellijstDM;
    private Afspeellijst afspeellijst;
@Inject
    public void setAfseellijst(Afspeellijst afseellijst) {
        this.afspeellijst = afseellijst;
    }

    @Inject
    public void setAfspeellijstDM(AfspeellijstDataMapper afspeellijstDM) {
        this.afspeellijstDM = afspeellijstDM;
    }

    public AfspeellijstenDTO mapToDTO(List<Afspeellijst> afspeellijsten) {
        List<AfspeellijstDTO> afspeellijstDTOs = new ArrayList<AfspeellijstDTO>();
        int lengte = 0;
        for (Afspeellijst afspeellijst : afspeellijsten) {
            afspeellijstDTOs.add(afspeellijstDM.mapToDTO(afspeellijst));
            lengte += this.afspeellijst.berekenAfspeellijstLengte(afspeellijst.getId());
        }
        AfspeellijstenDTO afspeellijstenDTO = new AfspeellijstenDTO();
        afspeellijstenDTO.setPlaylists(afspeellijstDTOs);
        afspeellijstenDTO.setLength(lengte);
        return afspeellijstenDTO;
    }
}
