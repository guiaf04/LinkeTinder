package com.linketinder.dto;

import java.util.Objects;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String cnpj;
    private String country;
    private String cep;
    private String description;

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, String cnpj, String country, String cep, String description) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.country = country;
        this.cep = cep;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(cnpj, that.cnpj) &&
               Objects.equals(country, that.country) &&
               Objects.equals(cep, that.cep) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj, country, cep, description);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", country='" + country + '\'' +
                ", cep='" + cep + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
