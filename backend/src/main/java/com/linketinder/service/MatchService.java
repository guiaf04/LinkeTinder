package com.linketinder.service;

import com.linketinder.dto.MatchDTO;
import com.linketinder.dto.UnmatchedCandidateDTO;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.exception.UnauthorizedOperation;
import com.linketinder.model.Candidate;
import com.linketinder.model.Job;
import com.linketinder.model.Match;
import com.linketinder.repository.CandidateRepository;
import com.linketinder.repository.CandidateSkillRepository;
import com.linketinder.repository.JobRepository;
import com.linketinder.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.linketinder.mapper.ObjectMapper.parseListObject;

@Service
public class MatchService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final MatchRepository matchRepository;
    private final CandidateSkillRepository candidateSkillRepository;

    public MatchService(
            final CandidateRepository candidateRepository,
            final JobRepository jobRepository,
            final MatchRepository matchRepository,
            final CandidateSkillRepository candidateSkillRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.matchRepository = matchRepository;
        this.candidateSkillRepository = candidateSkillRepository;
    }

    public Match createMatch(final MatchDTO matchDTO) {
        final Candidate candidate = candidateRepository
                .findById(matchDTO.getCandidateId())
                .orElseThrow(() -> new EntityNotFound("Candidate not found"));

        final Job job = jobRepository.findById(matchDTO.getJobId())
                .orElseThrow(() -> new EntityNotFound("Job not found"));

        final Match match = new Match();
        match.setCandidate(candidate);
        match.setJob(job);
        match.setCandidateLiked(false);
        match.setEmployerLiked(false);
        match.setMatch(false);

        return matchRepository.save(match);
    }

    public Match findMatch(final MatchDTO matchDTO) {
        return matchRepository
                .findByCandidateIdAndJobId(matchDTO.getCandidateId(), matchDTO.getJobId())
                .orElseThrow(() -> new EntityNotFound("Match not found"));
    }

    public void candidateLike(final MatchDTO matchDTO) {
        final Match match = matchRepository
                .findByCandidateIdAndJobId(matchDTO.getCandidateId(), matchDTO.getJobId())
                .orElseThrow(() -> new EntityNotFound("Match not found"));

        match.setCandidateLiked(true);
        matchRepository.save(match);
    }

    public void employeeLike(final MatchDTO matchDTO) {
        final Job job = jobRepository.findById(matchDTO.getJobId())
                .orElseThrow(() -> new EntityNotFound("Job not found"));

        if (!job.getIdEmployee().equals(matchDTO.getEmployeeId())) {
            throw new UnauthorizedOperation("Employee can't like this job");
        }

        final Match match = matchRepository
                .findByCandidateIdAndJobId(matchDTO.getCandidateId(), matchDTO.getJobId())
                .orElseThrow(() -> new EntityNotFound("Match not found"));

        match.setEmployerLiked(true);

        if (match.isCandidateLiked()) {
            match.setMatch(true);
        }

        matchRepository.save(match);
    }

    public List<UnmatchedCandidateDTO> findPotentialMatches(final Long employeeId) {
        final List<Job> jobs = jobRepository.findAllByIdEmployee(employeeId);
        final List<Long> jobIds = jobs.stream()
                .map(Job::getId)
                .collect(Collectors.toList());

        final List<Candidate> candidates = matchRepository.findAllCandidateByJobIdIn(jobIds);

        final List<UnmatchedCandidateDTO> candidateDTOS = parseListObject(candidates, UnmatchedCandidateDTO.class);

        candidateDTOS.forEach(candidate ->
            candidate.setSkills(candidateSkillRepository.findAllSkillByCandidateId(candidate.getId()))
        );

        return candidateDTOS;
    }
}
