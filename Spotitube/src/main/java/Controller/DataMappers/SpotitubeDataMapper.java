package Controller.DataMappers;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.AfspeellijstenDTO;
import Domain.Afspeellijst;
import java.util.ArrayList;
import java.util.List;

public class SpotitubeDataMapper {
AfspeellijstDataMapper afspeellijstDM = new AfspeellijstDataMapper();



    public AfspeellijstenDTO mapToDTO(List<Afspeellijst> afspeellijsten) {
        List<AfspeellijstDTO> afspeellijstDTOs = new ArrayList<AfspeellijstDTO>();
        int lengte = 0;
        for (Afspeellijst afspeellijst : afspeellijsten) {
            afspeellijstDTOs.add(afspeellijstDM.mapToDTO(afspeellijst));
            lengte += afspeellijst.berekenAfspeellijstLengte();
        }
        AfspeellijstenDTO afspeellijstenDTO = new AfspeellijstenDTO();
        afspeellijstenDTO.setPlaylists(afspeellijstDTOs);
        afspeellijstenDTO.setLength(lengte);
        return afspeellijstenDTO;
    }
}
