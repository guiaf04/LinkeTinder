"use strict";
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
document.getElementById('formEmpresa').onsubmit = function () {
    i2 =
        window.localStorage.getItem('i2') != null
            ? parseInt(window.localStorage.getItem('i2'))
            : 0;
    event === null || event === void 0 ? void 0 : event.preventDefault();
    const e = new Empresa(document.getElementById('nome').value, document.getElementById('pais').value, document.getElementById('estado').value, parseInt(document.getElementById('cep').value), document.getElementById('cnpj').value, document.getElementById('email').value, document.getElementById('descricao').value, document.getElementById('competencias').value);
    window.localStorage.setItem('empresa:' + i2, JSON.stringify(e));
    i2++;
    window.localStorage.setItem('i2', i2.toString());
    window.location.assign('perfilEmpresa.html');
};
