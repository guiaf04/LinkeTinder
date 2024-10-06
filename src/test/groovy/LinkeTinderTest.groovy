import org.junit.Test

import static org.junit.Assert.*

class LinkeTinderTest {
    LinkeTinder linkeTinder = new LinkeTinder()

    @Test
    void addCandidateTestWithUniqueCPF() {
        Candidato c = new Candidato(name: "Fulano", email: "fulaninho123@gmail.com",
                cpf: "82375956216", idade: "45", state: "Sao Paulo",
                cep: "6020001", description: "sou uma desenvolvedora frontend",
                competencias: [Competencias.Javascript, Competencias.Angular])

        boolean success = linkeTinder.addCandidate(c)

        assertTrue(success)
    }

    @Test
    void addCandidateTestWithDuplicateCPF() {
        Candidato c = new Candidato(name: "Fulanos", email: "fulani3123@gmail.com",
                cpf: "123456", idade: "21", state: "Sao Paulo",
                cep: "6020431", description: "sou um desenvolvedor frontend",
                competencias: [Competencias.Javascript, Competencias.Angular])

        boolean success = linkeTinder.addCandidate(c)

        assertFalse(success)
    }

    @Test
    void addEmpresaTestWithUniqueCNPJ() {
        Empresa e = new Empresa(name: "AllMerican", email: "american@all.com",
                CNPJ: "447297392", state: "Washington", pais: "Estados Unidos",
                cep: "3222221", description: "sou uma empresa de e-commerce generalista",
                competencias: [Competencias.Javascript, Competencias.Java, Competencias.Angular])

        boolean success = linkeTinder.addEmpresa(e)

        assertTrue(success)
    }

    @Test
    void addEmpresaTestWithDuplicateCNPJ() {
        Empresa e = new Empresa(name: "Teste", email: "teste@all.com",
                CNPJ: "29402340", state: "Washington", pais: "Chile",
                cep: "2452342", description: "sou uma empresa de e-commerce generalista",
                competencias: [Competencias.Javascript, Competencias.Java, Competencias.Angular])

        boolean success = linkeTinder.addEmpresa(e)

        assertFalse(success)
    }
}
