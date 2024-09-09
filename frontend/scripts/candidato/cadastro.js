"use strict";
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
document.getElementById('teste').onclick = function () {
    window.location.replace('perfilCandidato.html');
};
const formulario = document.getElementById('formCandidato');
formulario.onsubmit = function () {
    i =
        window.localStorage.getItem('i') != null
            ? parseInt(window.localStorage.getItem('i'))
            : 0;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    const c = new Candidato(document.getElementById('nome').value, parseInt(document.getElementById('idade').value), document.getElementById('estado').value, parseInt(document.getElementById('cep').value), document.getElementById('cpf').value, document.getElementById('email').value, document.getElementById('descricao').value, document.getElementById('competencias').value);
    window.localStorage.setItem('candidato:' + i, JSON.stringify(c));
    i++;
    window.localStorage.setItem('i', i.toString());
    window.location.assign('perfilCandidato.html');
};
