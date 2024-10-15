import groovy.transform.ToString
import model.Candidato
import model.Competencia
import model.Empresa

@ToString
class LinkeTinder {
    ArrayList<Candidato> candidatos = new ArrayList<>()
    ArrayList<Empresa> empresas = new ArrayList<>()

    LinkeTinder() {
        candidatos << new Candidato(name: "Joao", email: "joazinho123@gmail.com",
                cpf: "123456", idade: "18", state: "Ceara",
                cep: "63000000", description: "sou um desenvolvedor backend",
                competencias: [Competencia.Java, Competencia.Groovy])

        candidatos << new Candidato(name: "Maria", email: "maria123@gmail.com",
                cpf: "153521", idade: "22", state: "Sao Paulo",
                cep: "6020001", description: "sou uma desenvolvedora frontend",
                competencias: [Competencia.Javascript, Competencia.Angular])

        candidatos << new Candidato(name: "Pedro", email: "pedrinho123@gmail.com",
                cpf: "40028922", idade: "25", state: "Minas Gerais",
                cep: "45056932", description: "sou um desenvolvedor fullstack",
                competencias: [Competencia.Java, Competencia.Angular])

        candidatos << new Candidato(name: "Joana", email: "joaninha321@gmail.com",
                cpf: "12345698", idade: "30", state: "Ceara",
                cep: "63000000", description: "sou uma desenvolvedora backend senior",
                competencias: [Competencia.Java, Competencia.Groovy, Competencia.Gradle, Competencia.SpringFramework])

        candidatos << new Candidato(name: "Augusto", email: "agostinho@gmail.com",
                cpf: "29402840", idade: "29", state: "Amazonas",
                cep: "3242421", description: "sou um desenvolvedor senior frontend",
                competencias: [Competencia.Javascript, Competencia.Typescript, Competencia.Angular])

        // --------------------------------------------------------------------------------------------------//

        empresas << new Empresa(name: "MangaLu", email: "manga@lu.com",
                CNPJ: "29402840", state: "Amazonas", pais: "Brasil",
                cep: "3242421", description: "sou uma empresa de mangas online",
                competencias: [Competencia.Javascript, Competencia.Typescript, Competencia.Angular])

        empresas << new Empresa(name: "AllMerican", email: "american@all.com",
                CNPJ: "29402340", state: "Washington", pais: "Estados Unidos",
                cep: "3222221", description: "sou uma empresa de e-commerce generalista",
                competencias: [Competencia.Javascript, Competencia.Java, Competencia.Angular])

        empresas << new Empresa(name: "CasasBaianas", email: "bahia@casas.com",
                CNPJ: "1102840", state: "Bahia", pais: "Brasil",
                cep: "2342421", description: "sou uma empresa de delivery de comidas típicas",
                competencias: [Competencia.Groovy, Competencia.Typescript, Competencia.Angular])

        empresas << new Empresa(name: "PontoQuente", email: "agostinho@gmail.com",
                CNPJ: "00002840", state: "Ceara", pais: "Brasil",
                cep: "63000000", description: "sou uma empresa de ar condicionado",
                competencias: [Competencia.Python, Competencia.Typescript, Competencia.Angular])

        empresas << new Empresa(name: "Ihta", email: "i@tah.com",
                CNPJ: "6662840", state: "Sao Paulo", pais: "Brasil",
                cep: "6662421", description: "sou uma empresa de aeronautica",
                competencias: [Competencia.C, Competencia.Typescript, Competencia.Angular])
    }

    String listCandidates(){
        String messageReturn = ""
        for(Candidato c : candidatos)
            messageReturn += c.toString() + "\n"

        return messageReturn
    }

    String listCompanys(){
        String messageReturn = ""
        for(Empresa e : empresas)
            messageReturn += e.toString() + "\n"

        return messageReturn
    }

    boolean addCandidate(Candidato c){
        boolean  success = true
        candidatos.each {it -> if(it.cpf.equalsIgnoreCase(c.cpf)) success = false}

        candidatos.add(c)
        return success
    }

    boolean addEmpresa(Empresa e){
        boolean  success = true

        empresas.each {it -> if(it.CNPJ.equalsIgnoreCase(e.CNPJ)) success = false}
        empresas.add(e)

        return success
    }
}
