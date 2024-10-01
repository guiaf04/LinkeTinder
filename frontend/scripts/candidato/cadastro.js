let i = 0;
class Candidato {
    constructor(nome, idade, estado, cep, cpf, email, descricao, competencias) {
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
        this.cep = cep;
        this.cpf = cpf;
        this.email = email;
        this.descricao = descricao;
        this.competencias = competencias;
    }
    toString() {
        return `
    nome:    ${this.nome},
    idade:   ${this.idade},
    estado:  ${this.estado},
    cep:     ${this.cep},
    cpf:     ${this.cpf},
    email:   ${this.email}
    `;
    }
}
export let nameValidate = /[A-Z|À-Ú][a-z]{2,}\s([A-Z|À-Ú][a-z]{2,}\s)*[A-Z|À-Ú][a-z]{2,}/;
let ageValidate = /^\d{1,2}$/g;
let cpfValidate = /^\d{3}.\d{3}.\d{3}-\d{2}$/g;
export let stateValidate = /^.*$/g;
export let cepValidate = /^\d{5}-\d{3}$/g;
export let emailValidate = /\S+@\w+\.\w{2,6}(\.\w{2})?/g;
export let descricaoValidate = /^.*$/g;
export let competenciasValidate = /\w+[,\s\w+]*,\s\w+/;
let candidateSucess = 0;
function setupDOMInteraction() {
    const formulario = document.getElementById("formCandidato");
    if (formulario) {
        formulario.onsubmit = function () {
            i =
                window.localStorage.getItem("i") != null
                    ? parseInt(window.localStorage.getItem("i"))
                    : 0;
            event === null || event === void 0 ? void 0 : event.preventDefault();
            const c = new Candidato(document.getElementById("nome").value, parseInt(document.getElementById("idade").value), document.getElementById("estado").value, parseInt(document.getElementById("cep").value), document.getElementById("cpf").value, document.getElementById("email").value, document.getElementById("descricao").value, document.getElementById("competencias").value);
            document.getElementById("error").innerHTML = "";
            validateForm();
            if (candidateSucess == 0) {
                window.localStorage.setItem("candidato:" + i, JSON.stringify(c));
                i++;
                window.localStorage.setItem("i", i.toString());
                window.location.assign("perfilCandidato.html");
            }
            candidateSucess = 0;
        };
    }
}
function validateForm() {
    if (document.getElementById("nome").value.match(nameValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Nome inválido, cada nome deve iniciar com letra maiúscula</p>";
    }
    if (document.getElementById("idade").value.match(ageValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Idade inválida, insira apenas números</p>";
    }
    if (document.getElementById("estado").value.match(stateValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Estado inválido, insira apenas letras</p>";
    }
    if (document.getElementById("cep").value.match(cepValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>CEP inválido, ele deve ser da forma XXXXX-XXX</p>";
    }
    if (document.getElementById("cpf").value.match(cpfValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>CPF inválido, ele deve ser da forma XXX.XXX.XXX-XX</p>";
    }
    if (document.getElementById("email").value.match(emailValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Email inválido, insira um formato válido</p>";
    }
    if (document.getElementById("descricao").value.match(descricaoValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Descrição inválido, ela não pode ser vazia</p>";
    }
    if (document.getElementById("competencias").value.match(competenciasValidate) == null) {
        candidateSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Competências inválida, cada competência deve ser separa por vírgula e espaço</p>";
    }
}
// Chamar a função de manipulação do DOM quando for apropriado
document.addEventListener("DOMContentLoaded", () => {
    setupDOMInteraction();
});
