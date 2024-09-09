let i: number = 0;

class Candidato {
  constructor(
    public nome: string,
    public idade: number,
    public estado: string,
    public cep: number,
    public cpf: string,
    public email: string,
    public descricao: string,
    public competencias: string
  ) {}

  toString(): string {
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

document.getElementById('teste')!.onclick = function () {
  window.location.replace('perfilCandidato.html');
};
const formulario = document.getElementById('formCandidato');
formulario!.onsubmit = function () {
  i =
    window.localStorage.getItem('i') != null
      ? parseInt(window.localStorage.getItem('i')!)
      : 0;
  event?.preventDefault();
  const c = new Candidato(
    (<HTMLInputElement>document.getElementById('nome')!).value,
    parseInt((<HTMLInputElement>document.getElementById('idade')!).value),
    (<HTMLInputElement>document.getElementById('estado')!).value,
    parseInt((<HTMLInputElement>document.getElementById('cep')!).value),
    (<HTMLInputElement>document.getElementById('cpf')!).value,
    (<HTMLInputElement>document.getElementById('email')!).value,
    (<HTMLInputElement>document.getElementById('descricao')!).value,
    (<HTMLInputElement>document.getElementById('competencias')!).value
  );

  window.localStorage.setItem('candidato:' + i, JSON.stringify(c));
  i++;
  window.localStorage.setItem('i', i.toString());
  window.location.assign('perfilCandidato.html');
};
