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

export let nameValidate: RegExp =
  /[A-Z|À-Ú][a-z]{2,}\s([A-Z|À-Ú][a-z]{2,}\s)*[A-Z|À-Ú][a-z]{2,}/;
let ageValidate: RegExp = /^\d{1,2}$/g;
let cpfValidate: RegExp = /^\d{3}.\d{3}.\d{3}-\d{2}$/g;
export let stateValidate: RegExp = /^.*$/g;
export let cepValidate: RegExp = /^\d{5}-\d{3}$/g;
export let emailValidate: RegExp = /\S+@\w+\.\w{2,6}(\.\w{2})?/g;
export let descricaoValidate: RegExp = /^.*$/g;
export let competenciasValidate: RegExp = /\w+[,\s\w+]*,\s\w+/;

let candidateSucess: number = 0;

function setupDOMInteraction() {
  const formulario = document.getElementById("formCandidato");
  if (formulario) {
    formulario!.onsubmit = function () {
      i =
        window.localStorage.getItem("i") != null
          ? parseInt(window.localStorage.getItem("i")!)
          : 0;

      event?.preventDefault();

      const c = new Candidato(
        (<HTMLInputElement>document.getElementById("nome")!).value,
        parseInt((<HTMLInputElement>document.getElementById("idade")!).value),
        (<HTMLInputElement>document.getElementById("estado")!).value,
        parseInt((<HTMLInputElement>document.getElementById("cep")!).value),
        (<HTMLInputElement>document.getElementById("cpf")!).value,
        (<HTMLInputElement>document.getElementById("email")!).value,
        (<HTMLInputElement>document.getElementById("descricao")!).value,
        (<HTMLInputElement>document.getElementById("competencias")!).value
      );

      document.getElementById("error")!.innerHTML = "";
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
function validateForm(): void {
  if (
    (<HTMLInputElement>document.getElementById("nome")!).value.match(
      nameValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Nome inválido, cada nome deve iniciar com letra maiúscula</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("idade")!).value.match(
      ageValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Idade inválida, insira apenas números</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("estado")!).value.match(
      stateValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Estado inválido, insira apenas letras</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("cep")!).value.match(
      cepValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>CEP inválido, ele deve ser da forma XXXXX-XXX</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("cpf")!).value.match(
      cpfValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>CPF inválido, ele deve ser da forma XXX.XXX.XXX-XX</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("email")!).value.match(
      emailValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Email inválido, insira um formato válido</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("descricao")!).value.match(
      descricaoValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Descrição inválido, ela não pode ser vazia</p>";
  }
  if (
    (<HTMLInputElement>document.getElementById("competencias")!).value.match(
      competenciasValidate
    ) == null
  ) {
    candidateSucess++;
    document.getElementById("error")!.innerHTML +=
      "<p>Competências inválida, cada competência deve ser separa por vírgula e espaço</p>";
  }
}

// Chamar a função de manipulação do DOM quando for apropriado
document.addEventListener("DOMContentLoaded", () => {
  setupDOMInteraction();
});
