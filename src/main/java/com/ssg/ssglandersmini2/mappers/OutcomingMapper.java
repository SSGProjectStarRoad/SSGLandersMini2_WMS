package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.*;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;

public interface OutcomingMapper {



    // outcoming 테이블가져오기
    List<Outcoming> selectOutcomingList(PageRequestDTO pageRequestDTO);

    List<Outcoming> selectOutcomingNoApprovalList(PageRequestDTO pageRequestDTO);

    // 총갯수가져오기
    int getCount(PageRequestDTO pageRequestDTO);

    int getApprovalCount(PageRequestDTO pageRequestDTO);

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

    // 운송장등록

    // destination, date, sid 받아서 waybill 등록
    void insertWaybill(String destination,String date, long sid);

    // 가장 최근 wbid 가져오기
    long selectWaybillWbidLast();

    // oid 이용해서 outcoming에 wbid 넣기
    void insertOutcomingWbidByOid(long oid, long wbid);

    //oid 이용해서 '승인완료'로 바꾸기
    void updateOutcomingApprovalByOid(long oid);

    // 출고등록 !!

    //상품명 name 받아서 pid 반환
    long selectProductPidByName(String name);

    //창고 이름 wname 받아서 wid 반환
    long selectWarehouseWidByWname(String wname);

    // 출고 요청, pid, wid, quantity, now(), 배송전, 승인대기, null
    void insertOutcoming(long pid, long wid, long quantity);

    // oid로 status가져오기
    String selectOutcomingApprovalByOid(long oid);

}
