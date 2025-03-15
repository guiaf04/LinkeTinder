package com.linketinder.dto

import groovy.transform.ToString

@ToString
class EmployeeDTO {

    Long id
    String name
    String cnpj
    String country
    String cep
    String description

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        EmployeeDTO that = (EmployeeDTO) o

        if (cep != that.cep) return false
        if (cnpj != that.cnpj) return false
        if (country != that.country) return false
        if (description != that.description) return false
        if (id != that.id) return false
        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0)
        result = 31 * result + (country != null ? country.hashCode() : 0)
        result = 31 * result + (cep != null ? cep.hashCode() : 0)
        result = 31 * result + (description != null ? description.hashCode() : 0)
        return result
    }
}
