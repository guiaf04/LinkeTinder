package model

class Empresa extends Pessoa{
    String CNPJ, pais

    @Override
    String toString() {
        return "model.Empresa{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", cep='" + cep + '\'' +
                ", description='" + description + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", pais='" + pais + '\'' +
                ", competencias="    + competencias +
                '}'
    }
}
