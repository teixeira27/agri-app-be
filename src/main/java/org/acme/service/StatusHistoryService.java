package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Culture;
import org.acme.domain.StatusHistory;
import org.acme.dto.inbound.StatusHistoryCreationDTO;
import org.acme.dto.outbound.StatusHistoryInfoDTO;
import org.acme.repository.CultureRepository;
import org.acme.repository.StatusHistoryRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StatusHistoryService {

    @Inject
    CultureRepository cultureRepository;
    @Inject
    StatusHistoryRepository statusHistoryRepository;

    @Transactional
    public Integer createStatusHistory(StatusHistoryCreationDTO statusHistoryCreationDTO) {
        Optional<Culture> culture = this.cultureRepository.findById(statusHistoryCreationDTO.getCultureId());
        if (culture.isEmpty()) throw new EntityNotFoundException("Culture not found!");

        StatusHistory statusHistory = StatusHistory.builder()
                .date(Instant.now())
                .description(statusHistoryCreationDTO.getDescription())
                .image(statusHistoryCreationDTO.getImage()).build();
        this.statusHistoryRepository.save(statusHistory);
        return statusHistory.getStatusHistoryId();
    }

    public List<StatusHistoryInfoDTO> getAllStatusHistoryByCulture(Integer cultureId) {
        List<StatusHistory> statusHistories = this.statusHistoryRepository.findByCulture_CultureId(cultureId);
        List<StatusHistoryInfoDTO> statusHistoryInfoDTOS = new ArrayList<>();

        for (StatusHistory statusHistory : statusHistories) {
            StatusHistoryInfoDTO statusHistoryInfoDTO = StatusHistoryInfoDTO.builder()
                    .statusHistoryId(statusHistory.getStatusHistoryId())
                    .date(statusHistory.getDate())
                    .description(statusHistory.getDescription())
                    .image(statusHistory.getImage())
                    .build();
            statusHistoryInfoDTOS.add(statusHistoryInfoDTO);
        }
        return statusHistoryInfoDTOS;
    }

}
