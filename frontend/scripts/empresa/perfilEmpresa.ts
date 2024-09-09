// import * as Plotly from 'plotly.js';

const keys2 = Object.keys(localStorage).filter((key) =>
  key.startsWith('candidato:')
);

const candidatos = keys2.map((key) => JSON.parse(localStorage.getItem(key)!));

let index2: number =
  window.localStorage.getItem('i2') != null
    ? parseInt(window.localStorage.getItem('i2')!) - 1
    : 0;

const empresa = window.localStorage.getItem('empresa:' + index2.toString())!;
const empresa2 = JSON.parse(empresa);

const xArray = [
  'Java',
  'Python',
  'Html',
  'Css',
  'JavaScript',
  'TypeScript',
  'Groovy',
  'Graadle',
];

const yArray = [0, 0, 0, 0, 0, 0, 0, 0];
console.log(yArray);

document.getElementById('nome')!.innerHTML += empresa2['nome'];
document.getElementById('pais')!.innerHTML += empresa2['pais'];
document.getElementById('cnpj')!.innerHTML += empresa2['cnpj'];
document.getElementById('estado')!.innerHTML += empresa2['estado'];
document.getElementById('cep')!.innerHTML += empresa2['cep'];
document.getElementById('email')!.innerHTML += empresa2['email'];
document.getElementById('descricao')!.innerHTML += empresa2['descricao'];
document.getElementById('competencias')!.innerHTML += empresa2['competencias'];

candidatos.forEach((elem) => {
  document.getElementById('candidatosDisponiveis')!.innerHTML += `
      <div id = 'vaga: ${(index2 + 1).toString()}'>
          <h4>Candidato ${(index2 + 1).toString()}: </h4>
          <p>Descrição: ${elem['descricao']}</p>
          <p>Competências: ${elem['competencias']}</p>
      </div> 
      <br>
      `;
});

candidatos.forEach((elem) => {
  const str: string = elem['competencias'];
  const splitStr: Array<string> = str.split(', ');
  splitStr.forEach((elemento) => {
    const indice = xArray.indexOf(elemento);
    if (indice != -1) {
      yArray[indice]++;
    }
  });
});

console.log(yArray);
const barColors = ['red', 'green', 'blue', 'orange', 'brown'];

new Chart('myChart', {
  type: 'bar',
  data: {
    labels: xArray,
    datasets: [
      {
        backgroundColor: barColors,
        data: yArray,
      },
    ],
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "Distribuição de habilidades por candidato"
    }
  }
});
