package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.domain.Shippingcompany;
import com.ssg.ssglandersmini2.domain.Waybill;
import com.ssg.ssglandersmini2.dto.*;

public interface OutcomingService {

    // pageRequest로 창고현황 가져오기
    PageResponseDTO<OutcomingListDTO> getList(PageRequestDTO pageRequestDTO);

    // oid로 디테일 가져오기
    DetailsDTO getDetails(long oid);

    // oid, value(출고후,전)로 업데이트
    void modifyStatus(long value, long oid);

    //oid로 waybill가져오기
    Waybill getWaybill(long oid);

    //wbid로 shippingcompany 가져오기
    Shippingcompany getShippingcompanyByWbid(long wbid);

    //wbid와 sid 받아서 변경
    void modifyWaybillSidByWbidAndSid(long wbid, long sid);

    //oid이용해서 outcoming삭제하기
    void removeOutcomingByOid(long oid);


}
