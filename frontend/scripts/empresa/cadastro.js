import * as validate from "../candidato/cadastro.js";
let i2 = 0;
class Empresa {
    constructor(nome, pais, estado, cep, cnpj, email, descricao, competencias) {
        this.nome = nome;
        this.pais = pais;
        this.estado = estado;
        this.cep = cep;
        this.cnpj = cnpj;
        this.email = email;
        this.descricao = descricao;
        this.competencias = competencias;
    }
    toString() {
        return `
      nome:    ${this.nome},
      pais:   ${this.pais},
      estado:  ${this.estado},
      cep:     ${this.cep},
      cnpj:     ${this.cnpj},
      email:   ${this.email},
      descricao:   ${this.descricao},
      competencias:   ${this.competencias}
      `;
    }
}
let countryValidate = /^.*$/g;
let cnpjValidate = /\d{2}.\d{3}.\d{3}\/(0001|0002)-\d{2}/g;
let empresaSucess = 0;
document.getElementById("formEmpresa").onsubmit = function () {
    i2 =
        window.localStorage.getItem("i2") != null
            ? parseInt(window.localStorage.getItem("i2"))
            : 0;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    const e = new Empresa(document.getElementById("nome").value, document.getElementById("pais").value, document.getElementById("estado").value, parseInt(document.getElementById("cep").value), document.getElementById("cnpj").value, document.getElementById("email").value, document.getElementById("descricao").value, document.getElementById("competencias").value);
    document.getElementById("error").innerHTML = "";
    validateForm();
    if (empresaSucess == 0) {
        window.localStorage.setItem("empresa:" + i2, JSON.stringify(e));
        i2++;
        window.localStorage.setItem("i2", i2.toString());
        window.location.assign("perfilEmpresa.html");
    }
    empresaSucess = 0;
};
function validateForm() {
    if (document.getElementById("nome").value.match(validate.nameValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Nome inválido, cada nome deve iniciar com letra maiúscula</p>";
    }
    if (document.getElementById("pais").value.match(countryValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Pais inválido, insira apenas números</p>";
    }
    if (document.getElementById("estado").value.match(validate.stateValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Estado inválido, insira apenas letras</p>";
    }
    if (document.getElementById("cep").value.match(validate.cepValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>CEP inválido, ele deve ser da forma XXXXX-XXX</p>";
    }
    if (document.getElementById("cnpj").value.match(cnpjValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>CNPJ inválido, ele deve ser da forma XX.XXX.XXX/0001-XX ou XX.XXX.XXX/0002-XX</p>";
    }
    if (document.getElementById("email").value.match(validate.emailValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Email inválido, insira um formato válido</p>";
    }
    if (document.getElementById("descricao").value.match(validate.descricaoValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Descrição inválido, ela não pode ser vazia</p>";
    }
    if (document.getElementById("competencias").value.match(validate.competenciasValidate) == null) {
        empresaSucess++;
        document.getElementById("error").innerHTML +=
            "<p>Competências inválida, cada competência deve ser separa por vírgula e espaço</p>";
    }
}
