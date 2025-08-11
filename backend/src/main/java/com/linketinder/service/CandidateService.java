package com.linketinder.service;

import com.linketinder.dto.CandidateDTO;
import com.linketinder.dto.UnmatchedCandidateDTO;
import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Candidate;
import com.linketinder.repository.CandidateRepository;
import com.linketinder.repository.CandidateSkillRepository;
import com.linketinder.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.linketinder.mapper.ObjectMapper.parseObject;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final MatchRepository matchRepository;
    private final CandidateSkillRepository candidateSkillRepository;

    public CandidateService(
            final CandidateRepository candidateRepository,
            final MatchRepository matchRepository,
            final CandidateSkillRepository candidateSkillRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.matchRepository = matchRepository;
        this.candidateSkillRepository = candidateSkillRepository;
    }

    public CandidateDTO addCandidate(final Candidate candidato) {
        if (candidateRepository.getElementByCpf(candidato.getCpf()) != null) {
            throw new DuplicateEntity("Bad request on candidate body!");
        }

        return parseObject(candidateRepository.save(candidato), CandidateDTO.class);
    }

    public List<UnmatchedCandidateDTO> listCandidates(final Long employeeId) {
        final List<Candidate> candidates = candidateRepository.findAll();
        final List<UnmatchedCandidateDTO> candidatesDTOS = new ArrayList<>();

        for (final Candidate candidate : candidates) {
            UnmatchedCandidateDTO candidateDTO;

            if (matchRepository.existsByCandidateIdAndEmployeeId(candidate.getId(), employeeId)) {
                candidateDTO = parseObject(candidate, CandidateDTO.class);
            } else {
                candidateDTO = parseObject(candidate, UnmatchedCandidateDTO.class);
            }

            candidateDTO.setSkills(candidateSkillRepository.findAllSkillByCandidateId(candidate.getId()));
            candidatesDTOS.add(candidateDTO);
        }

        return candidatesDTOS;
    }

    public CandidateDTO editCandidate(final Candidate candidato) {
        final Candidate edit = candidateRepository.getElementByCpf(candidato.getCpf());
        if (edit == null) {
            throw new EntityNotFound("No records for this CPF!");
        }

        edit.setFirstName(candidato.getFirstName());
        edit.setLastName(candidato.getLastName());
        edit.setBirthday(candidato.getBirthday());
        edit.setEmail(candidato.getEmail());
        edit.setCountry(candidato.getCountry());
        edit.setCep(candidato.getCep());
        edit.setPersonalDescription(candidato.getPersonalDescription());
        edit.setPassword(candidato.getPassword());

        return parseObject(candidateRepository.save(edit), CandidateDTO.class);
    }

    public void deleteCandidate(final String cpf) {
        final Candidate candidato = candidateRepository.getElementByCpf(cpf);
        if (candidato == null) {
            throw new EntityNotFound("No records for this CPF!");
        }

        candidateRepository.delete(candidato);
    }
}
