package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.domain.Outcoming;
import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.MonthlyDTO;
import com.ssg.ssglandersmini2.dto.WarehouseCityDTO;
import com.ssg.ssglandersmini2.mappers.OverallMapper;
import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OverallServiceImpl implements OverallService {

    private final OverallMapper overallMapper;

    @Override
    public Optional<Integer> getIncomingCount() {
        return Optional.of(overallMapper.getIncomingCount());
    }

    @Override
    public Optional<Integer> getOutcomingCount() {
        return Optional.of(overallMapper.getOutComingCount());
    }

    @Override
    public Optional<Integer> getIncomingBeforeCount() {
        return Optional.of(overallMapper.getIncomingBeforeCount());
    }

    @Override
    public Optional<Integer> getIncomingArriveCount() {
        return Optional.of(overallMapper.getIncomingArriveCount());
    }

    @Override
    public Optional<Integer> getOutcomingBeforeCount() {
        return Optional.of(overallMapper.getOutcomingBeforeCount());
    }

    @Override
    public Optional<Integer> getOutcomingArriveCount() {
        return Optional.of(overallMapper.getOutcomingArriveCount());
    }

    @Override
    public Optional<Integer> getNotApprovalCount() {
        return Optional.of(overallMapper.getNotApprovalIncomingCount() + overallMapper.getNotApprovalOutcomingCount());
    }

    @Override
    public Optional<Integer> getTotalUsingCapacity() {
        return Optional.of(overallMapper.getTotalUsingCapacity());
    }

    @Override
    public List<WarehouseCityDTO> getWarehouseCityTotal() {
        List<Warehouse> cityVOList = overallMapper.getWarehouseList();
        Map<String, WarehouseCityDTO> cityDTOMap = new HashMap<>();
        for (Warehouse warehouse : cityVOList) {
            String[] parts = warehouse.getAddress().split("\\s+");
            for (String part : parts) {
                String cityKey = part.substring(0, 2);
                WarehouseCityDTO dto = cityDTOMap.get(cityKey);
                substringAddress(warehouse, dto, cityKey, cityDTOMap);
                break;
//                if (part.endsWith("시")) {
//                    String cityKey = part.substring(0, 2);
//                    WarehouseCityDTO dto = cityDTOMap.get(cityKey);
//                    substringAddress(warehouse, dto, cityKey, cityDTOMap);
//                    break;
//                }
//                else if (part.endsWith("도")) {
//                    String cityKey = part;
//                    WarehouseCityDTO dto = cityDTOMap.get(cityKey);
//                    substringAddress(warehouse, dto, cityKey, cityDTOMap);
//                    break;
//                }
            }
        }
        return cityDTOMap.values().stream()
                .sorted(Comparator.comparing(WarehouseCityDTO::getCity))
                .collect(Collectors.toList());
    }


    private void substringAddress(Warehouse warehouse, WarehouseCityDTO dto, String cityKey, Map<String, WarehouseCityDTO> cityDTOMap) {
        if (dto == null) {
            // 해당 city에 대한 DTO가 아직 없는 경우 새로 생성
            dto = WarehouseCityDTO.builder()
                    .city(cityKey)
                    .totalcapacity(warehouse.getCapacity())
                    .totalusingcapacity(warehouse.getUsingcapacity())
                    .build();
            cityDTOMap.put(cityKey, dto);
        } else {
            // 이미 존재하는 경우, capacity와 usingcapacity를 업데이트
            dto.setTotalcapacity(dto.getTotalcapacity() + warehouse.getCapacity());
            dto.setTotalusingcapacity(dto.getTotalusingcapacity() + warehouse.getUsingcapacity());
        }
    }

    @Override
    public MonthlyDTO getMonthlyInOut() {
        Map<Integer, MonthlyDTO> yearlyData = new HashMap<>();

        // 입고 목록 처리
        List<Incoming> incomingList = overallMapper.getIncomingList();
        for (Incoming incoming : incomingList) {
            LocalDate date = incoming.getDate();
            int year = date.getYear();
            int month = date.getMonthValue() - 1;
            MonthlyDTO dto = yearlyData.computeIfAbsent(year, y -> new MonthlyDTO(year, new int[12], new int[12]));
            dto.getIncoming()[month]++;
        }
//
//        검사: yearlyData 맵에 year라는 키로 저장된 MonthlyDTO 객체가 있는지 검사합니다.
//
//                만약 있다면, 그 객체의 참조를 반환합니다.
//                만약 없다면, 새 MonthlyDTO 객체를 생성하고, 이를 yearlyData 맵에 year라는 키로 저장한 후, 그 객체의 참조를 반환합니다.
//                반환된 참조 사용: computeIfAbsent 메소드로부터 반환된 MonthlyDTO 객체의 참조(dto)를 사용하여, 해당 객체의 incoming 배열에서
//        특정 월(month 인덱스)의 값을 1 증가시킵니다.
//
//                여기서 중요한 점은 dto가 yearlyData 맵에 저장된 객체의 "참조"라는 것입니다. 따라서, dto를 통해 객체를 변경하면,
//                이 변경 사항은 yearlyData 맵에 저장된 원본 객체에도 반영됩니다. 객체가 "참조에 의해" 작동한다는 이 개념은 Java의 핵심
//        원리 중 하나입니다.

        // 출고 목록 처리
        List<Outcoming> outcomingList = overallMapper.getOutcomingList();
        for (Outcoming outcoming : outcomingList) {
            LocalDate date = outcoming.getDate();
            int year = date.getYear();
            int month = date.getMonthValue() - 1;
            MonthlyDTO dto = yearlyData.computeIfAbsent(year, y -> new MonthlyDTO(year, new int[12], new int[12]));
            dto.getOutcoming()[month]++;
        }

        // 여기서는 가장 최근 년도의 데이터를 반환합니다.
        // 실제 요구 사항에 따라 여러 년도의 데이터를 처리하는 방식을 결정해야 합니다.
        return yearlyData.values().stream()
                .max(Comparator.comparingInt(MonthlyDTO::getYear))
                .orElse(new MonthlyDTO(Year.now().getValue(), new int[12], new int[12]));
    }

}
