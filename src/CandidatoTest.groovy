import org.junit.jupiter.api.Test

import static org.junit.Assert.*

class CandidatoTest {
    Candidato candidato = new Candidato()

    @Test
    void testeAdicionarName() {
        String name = "Fulano"

        candidato.name = name

        assertEquals(name, candidato.name)
    }

    @Test
    void testeAdicionarCPF() {
        String cpf = "40028922"

        candidato.cpf = cpf

        assertEquals(cpf, candidato.cpf)
    }

    @Test
    void testeAdicionarIdade() {
        String idade = "20"

        candidato.idade = idade

        assertEquals(idade, candidato.idade)
    }

    @Test
    void testeAdicionarEmail() {
        String email = "Fulano"

        candidato.email = email

        assertEquals(email, candidato.email)
    }

    @Test
    void testeAdicionarState() {
        String state = "Fulano"

        candidato.state = state

        assertEquals(state, candidato.state)
    }

    @Test
    void testeAdicionarCEP() {
        String cep = "Fulano"

        candidato.cep = cep

        assertEquals(cep, candidato.cep)
    }

    @Test
    void testeAdicionarDescription() {
        String description = "Fulano"

        candidato.description = description

        assertEquals(description, candidato.description)
    }
}
