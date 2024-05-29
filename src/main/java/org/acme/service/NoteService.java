package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.domain.Company;
import org.acme.domain.Note;
import org.acme.dto.inbound.NoteCreationDTO;
import org.acme.dto.outbound.NoteInfoDTO;
import org.acme.repository.CollaboratorRepository;
import org.acme.repository.CompanyRepository;
import org.acme.repository.NoteRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class NoteService {
    @Inject
    CompanyRepository companyRepository;
    @Inject
    CollaboratorRepository collaboratorRepository;
    @Inject
    NoteRepository noteRepository;

    @Transactional
    public Integer createNote(NoteCreationDTO noteCreationDTO){
        Optional<Company> company = this.companyRepository.findById(noteCreationDTO.getCompanyId());
        if (company.isEmpty()) throw new EntityNotFoundException("Company not found!");

        Optional<Collaborator> collaborator = this.collaboratorRepository.findById(noteCreationDTO.getCollaboratorId());
        if (collaborator.isEmpty()) throw new EntityNotFoundException("Collaborator not found!");

        Note note = Note.builder()
                .title(noteCreationDTO.getTitle())
                .description(noteCreationDTO.getDescription())
                .collaborator(collaborator.get())
                .company(company.get())
                .createdTs(Timestamp.from(Instant.now()))
                .changedTs(Timestamp.from(Instant.now()))
                .build();

        this.noteRepository.save(note);
        return note.getNoteId();
    }

    @Transactional
    public List<NoteInfoDTO> getAllNotesByCompany(Integer companyId){
        Optional<Company> company = this.companyRepository.findById(companyId);
        if (company.isEmpty()) throw new EntityNotFoundException("Company not found!");

        List<Note> notes = company.get().getNotes();
        List<NoteInfoDTO> noteInfoDTOS = new ArrayList<>();

        for (Note note : notes){
            NoteInfoDTO noteInfoDTO = NoteInfoDTO.builder()
                    .noteId(note.getNoteId())
                    .collaboratorName(note.getCollaborator().getName())
                    .title(note.getTitle())
                    .description(note.getDescription())
                    .lastUpdated(note.getChangedTs().toString())
                    .build();
            noteInfoDTOS.add(noteInfoDTO);
        }

        return noteInfoDTOS;
    }

    @Transactional
    public String deleteById(Integer id){
        this.noteRepository.deleteById(id);
        return ("Land deleted successfully.");
    }
}
