import groovy.transform.ToString

@ToString
class Empresa extends Pessoa{
    String CNPJ, pais,name, email, state, cep, description
    ArrayList<Competencias> competencias = new ArrayList<>()

}
