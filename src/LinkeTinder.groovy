import groovy.transform.ToString

@ToString
class LinkeTinder {
    ArrayList<Candidato> candidatos = new ArrayList<>()
    ArrayList<Empresa> empresas = new ArrayList<>()

    LinkeTinder() {
        candidatos << new Candidato(name: "Joao", email: "joazinho123@gmail.com",
                cpf: "123456", idade: "18", state: "Ceara",
                cep: "63000000", description: "sou um desenvolvedor backend",
                competencias: [Competencias.Java, Competencias.Groovy])

        candidatos << new Candidato(name: "Maria", email: "maria123@gmail.com",
                cpf: "153521", idade: "22", state: "Sao Paulo",
                cep: "6020001", description: "sou uma desenvolvedora frontend",
                competencias: [Competencias.Javascript, Competencias.Angular])

        candidatos << new Candidato(name: "Pedro", email: "pedrinho123@gmail.com",
                cpf: "40028922", idade: "25", state: "Minas Gerais",
                cep: "45056932", description: "sou um desenvolvedor fullstack",
                competencias: [Competencias.Java, Competencias.Angular])

        candidatos << new Candidato(name: "Joana", email: "joaninha321@gmail.com",
                cpf: "12345698", idade: "30", state: "Ceara",
                cep: "63000000", description: "sou uma desenvolvedora backend senior",
                competencias: [Competencias.Java, Competencias.Groovy, Competencias.Gradle, Competencias.SpringFramework])

        candidatos << new Candidato(name: "Augusto", email: "agostinho@gmail.com",
                cpf: "29402840", idade: "29", state: "Amazonas",
                cep: "3242421", description: "sou um desenvolvedor senior frontend",
                competencias: [Competencias.Javascript, Competencias.Typescript, Competencias.Angular])

        // --------------------------------------------------------------------------------------------------//

        empresas << new Empresa(name: "MangaLu", email: "manga@lu.com",
                CNPJ: "29402840", state: "Amazonas", pais: "Brasil",
                cep: "3242421", description: "sou uma empresa de mangas online",
                competencias: [Competencias.Javascript, Competencias.Typescript, Competencias.Angular])

        empresas << new Empresa(name: "AllMerican", email: "american@all.com",
                CNPJ: "29402340", state: "Washington", pais: "Estados Unidos",
                cep: "3222221", description: "sou uma empresa de e-commerce generalista",
                competencias: [Competencias.Javascript, Competencias.Java, Competencias.Angular])

        empresas << new Empresa(name: "CasasBaianas", email: "bahia@casas.com",
                CNPJ: "1102840", state: "Bahia", pais: "Brasil",
                cep: "2342421", description: "sou uma empresa de delivery de comidas típicas",
                competencias: [Competencias.Groovy, Competencias.Typescript, Competencias.Angular])

        empresas << new Empresa(name: "PontoQuente", email: "agostinho@gmail.com",
                CNPJ: "00002840", state: "Ceara", pais: "Brasil",
                cep: "63000000", description: "sou uma empresa de ar condicionado",
                competencias: [Competencias.Python, Competencias.Typescript, Competencias.Angular])

        empresas << new Empresa(name: "Ihta", email: "i@tah.com",
                CNPJ: "6662840", state: "Sao Paulo", pais: "Brasil",
                cep: "6662421", description: "sou uma empresa de aeronautica",
                competencias: [Competencias.C, Competencias.Typescript, Competencias.Angular])
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
