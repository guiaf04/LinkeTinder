"use strict";
const keys = Object.keys(localStorage).filter((key) => key.startsWith('empresa:'));
const empresas = keys.map((key) => JSON.parse(localStorage.getItem(key)));
let index = window.localStorage.getItem('i') != null
    ? parseInt(window.localStorage.getItem('i')) - 1
    : 0;
const candidato = window.localStorage.getItem('candidato:' + index.toString());
const candidato2 = JSON.parse(candidato);
document.getElementById('nome').innerHTML += candidato2['nome'];
document.getElementById('idade').innerHTML += candidato2['idade'];
document.getElementById('cpf').innerHTML += candidato2['cpf'];
document.getElementById('estado').innerHTML += candidato2['estado'];
document.getElementById('cep').innerHTML += candidato2['cep'];
document.getElementById('email').innerHTML += candidato2['email'];
document.getElementById('descricao').innerHTML += candidato2['descricao'];
document.getElementById('competencias').innerHTML += candidato2['competencias'];
empresas.forEach((elem) => {
    document.getElementById('vagasDisponiveis').innerHTML += `
    <div id = 'vaga: ${index.toString()}'>
        <h4>Empresa ${(index + 1).toString()}: </h4>
        <p>Descrição: ${elem['descricao']}</p>
        <p>Competências: ${elem['competencias']}</p>
    </div> 
    <br>
    `;
});
// candidatos.forEach((elem) => {
//   document.getElementById('vagasDisponiveis')!.innerHTML += `
//     <div id = 'vaga: ${index.toString()}'>
//         <p>Nome: ${elem['nome']}</p>
//         <p>Idade: ${elem['idade']}</p>
//         <p>CPF: ${elem['cpf']}</p>
//         <p>Estado: ${elem['estado']}</p>
//         <p>Cep: ${elem['cep']}</p>
//         <p>Email: ${elem['email']}</p>
//         <p>Descrição: ${elem['descricao']}</p>
//         <p>Competências: ${elem['competencias']}</p>
//     </div> 
//     <br>
//     `;
// });
