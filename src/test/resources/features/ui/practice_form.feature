# language: pt
@ui @forms
Funcionalidade: Practice Form
  Como um candidato
  Quero preencher o Practice Form
  Para validar o envio e o popup de confirmacao

  Cenario: Preencher o formulario com dados aleatorios e fechar o popup
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Forms" na pagina inicial
    E clica no submenu "Practice Form"
    E preenche todo o formulario com valores aleatorios
    E faz upload de um arquivo txt
    E submete o formulario
    Entao um popup deve ser exibido apos o submit
    E o popup e fechado
