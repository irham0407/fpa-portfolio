package com.portfolio.fpa.service.coaser;

import com.portfolio.fpa.dto.coadto.CoaRequest;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.repository.CoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoaService {

    @Autowired
    private CoaRepository coaRepository;

    // Simpan 1 COA
    public CoaRequest createCoa(CoaRequest request) {
        Coa coa = Coa.builder()
                .coaCode(request.getCoaCode())
                .coaName(request.getCoaName())
                .accountType(request.getAccountType())
                .build();

        Coa saved = coaRepository.save(coa);
        return mapToRequest(saved);
    }

    // Simpan Banyak COA Sekaligus (Bulk)
    public List<CoaRequest> createBulkCoas(List<CoaRequest> requests) {
        List<Coa> coas = requests.stream()
                .map(req -> Coa.builder()
                        .coaCode(req.getCoaCode())
                        .coaName(req.getCoaName())
                        .accountType(req.getAccountType())
                        .build())
                .collect(Collectors.toList());

        List<Coa> savedList = coaRepository.saveAll(coas);
        return savedList.stream().map(this::mapToRequest).collect(Collectors.toList());
    }

    // Ambil Semua COA
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
