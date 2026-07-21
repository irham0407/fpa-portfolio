package com.portfolio.fpa.service.coaser;

import com.portfolio.fpa.dto.coadto.CoaRequest;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.repository.CoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoaService {

    @Autowired
    private CoaRepository coaRepository;

    // 1. Simpan 1 COA
    public CoaRequest createCoa(CoaRequest request) {
        if (coaRepository.existsByCoaCode(request.getCoaCode())) {
            return null;
        }
        Coa coa = Coa.builder()
                .coaCode(request.getCoaCode())
                .coaName(request.getCoaName())
                .accountType(request.getAccountType())
                .build();

        Coa saved = coaRepository.save(coa);
        return mapToRequest(saved);
    }

    // 2. Simpan Banyak COA (Bulk - Skip Duplikat)
    public List<CoaRequest> createBulkCoas(List<CoaRequest> requests) {
        List<Coa> coasToSave = new ArrayList<>();

        for (CoaRequest req : requests) {
            if (!coaRepository.existsByCoaCode(req.getCoaCode())) {
                Coa coa = Coa.builder()
                        .coaCode(req.getCoaCode())
                        .coaName(req.getCoaName())
                        .accountType(req.getAccountType())
                        .build();
                coasToSave.add(coa);
            }
        }

        if (coasToSave.isEmpty()) {
            return new ArrayList<>();
        }

        List<Coa> savedList = coaRepository.saveAll(coasToSave);
        return savedList.stream().map(this::mapToRequest).collect(Collectors.toList());
    }

    // 3. Ambil Semua COA
    public List<CoaRequest> getAllCoas() {
        return coaRepository.findAll().stream()
                .map(this::mapToRequest)
                .collect(Collectors.toList());
    }

    private CoaRequest mapToRequest(Coa coa) {
        return CoaRequest.builder()
                .coaCode(coa.getCoaCode())
                .coaName(coa.getCoaName())
                .accountType(coa.getAccountType())
                .build();
    }
}
