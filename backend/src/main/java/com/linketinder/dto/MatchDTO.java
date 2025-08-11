package com.linketinder.dto;

import java.util.Objects;

public class MatchDTO {

    private Long candidateId;
    private Long jobId;
    private Long employeeId;

    // Constructors
    public MatchDTO() {}

    public MatchDTO(Long candidateId, Long jobId, Long employeeId) {
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.employeeId = employeeId;
    }

    // Getters and Setters
    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return Objects.equals(candidateId, matchDTO.candidateId) &&
               Objects.equals(jobId, matchDTO.jobId) &&
               Objects.equals(employeeId, matchDTO.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, jobId, employeeId);
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "candidateId=" + candidateId +
                ", jobId=" + jobId +
                ", employeeId=" + employeeId +
                '}';
    }
}
