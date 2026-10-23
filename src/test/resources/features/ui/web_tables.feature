# language: pt
@ui @elements
Funcionalidade: Web Tables
  Como um usuario
  Quero gerenciar registros na tabela
  Para criar, editar e excluir dados

  Cenario: Criar, editar e deletar um registro
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Elements" na pagina inicial
    E clica no submenu "Web Tables"
    E cria um novo registro
    E edita o novo registro criado
    Entao o registro editado deve estar visivel
    E o novo registro e deletado

  @bonus
  Cenario: Criar 12 registros dinamicamente e deletar todos
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Elements" na pagina inicial
    E clica no submenu "Web Tables"
    E 12 novos registros sao criados de forma dinamica
    Entao todos os novos registros criados sao deletados
