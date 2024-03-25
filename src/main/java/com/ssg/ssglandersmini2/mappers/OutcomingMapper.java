package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.*;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;

import java.util.List;

public interface OutcomingMapper {



    // outcoming 테이블가져오기
    List<Outcoming> selectOutcomingList(PageRequestDTO pageRequestDTO);

    // 총갯수가져오기
    int getCount(PageRequestDTO pageRequestDTO);

    //pid 이용해서 product name 가져오기
    String getProductNameByPid(long pid);

    // pid 이용해서 product 객체 가져오기
    Product getProductByPid(long pid);

    //wid로 창고객체가져오기
    Warehouse getWarehouseByWid(long wid);

    //wid로 창고 종류 가져오기
    String getWarehouseTypeByWid(long wid);


    // 디테일
    // oid로 product가져오기
    Product getProductByOid(long oid);

    // oid로 warehouse가져오기
    Warehouse getWarehouseByOid(long oid);

    // oid로 waybill 가져오기
    Waybill getWaybillByOid(long oid);

    // wbid로 shipping 가져오기
    Shippingcompany getShippingcompanyByWbid(long wbid);

    // oid로 outcoming 가져오기
    Outcoming getOutcomingByOid(long oid);

    //oid와 String value 값으로 배송상태 업데이트 해주기(배송완료) (배송전)
    void updateOutcomingStatusByValue(String value, long oid);

    //oid와 String shipping으로 택배회사 변경하기
    void updateWaybillSidByWbidAndSid(long wbid, long sid);

    //oid 이용해서 삭제하기
    void deleteOutcomingByOid(long oid);

}
