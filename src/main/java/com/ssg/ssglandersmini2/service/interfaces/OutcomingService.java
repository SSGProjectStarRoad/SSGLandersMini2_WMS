package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.*;

public interface OutcomingService {

    PageResponseDTO<OutcomingListDTO> getList(PageRequestDTO pageRequestDTO);

    DetailsDTO getDetails(long oid);

//    DetailsNotDTO getDtailsNotDTO(long oid);


}
