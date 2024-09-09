let i2: number = 0;

class Empresa {
  constructor(
    public nome: string,
    public pais: string,
    public estado: string,
    public cep: number,
    public cnpj: string,
    public email: string,
    public descricao: string,
    public competencias: string
  ) {}

  toString(): string {
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

document.getElementById('formEmpresa')!.onsubmit = function () {
  i2 =
    window.localStorage.getItem('i2') != null
      ? parseInt(window.localStorage.getItem('i2')!)
      : 0;
  event?.preventDefault();
  const e = new Empresa(
    (<HTMLInputElement>document.getElementById('nome')!).value,
    (<HTMLInputElement>document.getElementById('pais')!).value,
    (<HTMLInputElement>document.getElementById('estado')!).value,
    parseInt((<HTMLInputElement>document.getElementById('cep')!).value),
    (<HTMLInputElement>document.getElementById('cnpj')!).value,
    (<HTMLInputElement>document.getElementById('email')!).value,
    (<HTMLInputElement>document.getElementById('descricao')!).value,
    (<HTMLInputElement>document.getElementById('competencias')!).value
  );

  window.localStorage.setItem('empresa:' + i2, JSON.stringify(e));
  i2++;
  window.localStorage.setItem('i2', i2.toString());
  window.location.assign('perfilEmpresa.html');
};
