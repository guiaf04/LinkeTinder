import org.junit.jupiter.api.Test

import static org.junit.Assert.*

class EmpresaTest {
    Empresa empresa = new Empresa()

    @Test
    void testeAdicionarName() {
        String name = "Fulano"

        empresa.name = name

        assertEquals(name, empresa.name)
    }

    @Test
    void testeAdicionarCNPJ() {
        String cnpj = "40028922"

        empresa.CNPJ = cnpj

        assertEquals(cnpj, empresa.CNPJ)
    }

    @Test
    void testeAdicionarPais() {
        String pais = "Brasil"

        empresa.pais = pais

        assertEquals(pais, empresa.pais)
    }

    @Test
    void testeAdicionarEmail() {
        String email = "Fulano.com"

        empresa.email = email

        assertEquals(email, empresa.email)
    }

    @Test
    void testeAdicionarState() {
        String state = "Ceara"

        empresa.state = state

        assertEquals(state, empresa.state)
    }

    @Test
    void testeAdicionarCEP() {
        String cep = "03030292"

        empresa.cep = cep

        assertEquals(cep, empresa.cep)
    }

    @Test
    void testeAdicionarDescription() {
        String description = "Fulano produtos"

        empresa.description = description

        assertEquals(description, empresa.description)
    }
}
