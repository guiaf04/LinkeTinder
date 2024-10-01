import * as validate from "../candidato/cadastro.js";
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

let countryValidate: RegExp = /^.*$/g;
let cnpjValidate: RegExp = /\d{2}.\d{3}.\d{3}\/(0001|0002)-\d{2}/g;

let empresaSucess: number = 0;

document.getElementById("formEmpresa")!.onsubmit = function () {
  i2 =
    window.localStorage.getItem("i2") != null
      ? parseInt(window.localStorage.getItem("i2")!)
      : 0;

  event?.preventDefault();

  const e = new Empresa(
    (<HTMLInputElement>document.getElementById("nome")!).value,
    (<HTMLInputElement>document.getElementById("pais")!).value,
    (<HTMLInputElement>document.getElementById("estado")!).value,
    parseInt((<HTMLInputElement>document.getElementById("cep")!).value),
    (<HTMLInputElement>document.getElementById("cnpj")!).value,
    (<HTMLInputElement>document.getElementById("email")!).value,
    (<HTMLInputElement>document.getElementById("descricao")!).value,
    (<HTMLInputElement>document.getElementById("competencias")!).value
  );

  document.getElementById("error")!.innerHTML = "";
  validateForm();

  if (empresaSucess == 0) {
    window.localStorage.setItem("empresa:" + i2, JSON.stringify(e));
    i2++;
    window.localStorage.setItem("i2", i2.toString());
    window.location.assign("perfilEmpresa.html");
  }

  empresaSucess = 0;
};

function validateForm(): void {
  if (
    (<HTMLInputElement>document.getElementById("nome")!).value.match(
      validate.nameValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Nome inválido, cada nome deve iniciar com letra maiúscula</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("pais")!).value.match(
      countryValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Pais inválido, insira apenas números</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("estado")!).value.match(
      validate.stateValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Estado inválido, insira apenas letras</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("cep")!).value.match(
      validate.cepValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>CEP inválido, ele deve ser da forma XXXXX-XXX</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("cnpj")!).value.match(
      cnpjValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>CNPJ inválido, ele deve ser da forma XX.XXX.XXX/0001-XX ou XX.XXX.XXX/0002-XX</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("email")!).value.match(
      validate.emailValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Email inválido, insira um formato válido</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("descricao")!).value.match(
      validate.descricaoValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Descrição inválido, ela não pode ser vazia</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("competencias")!).value.match(
      validate.competenciasValidate
    ) == null
  ) {
    empresaSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Competências inválida, cada competência deve ser separa por vírgula e espaço</p>";
  }
}
