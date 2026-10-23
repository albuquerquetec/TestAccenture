# language: pt
@ui @interactions
Funcionalidade: Sortable
  Como um usuario
  Quero reordenar a lista Sortable
  Para deixar os elementos em ordem crescente

  Cenario: Ordenar elementos da lista com drag and drop
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Interactions" na pagina inicial
    E clica no submenu "Sortable"
    E ordena os elementos da lista em ordem crescente
    Entao os elementos devem estar na ordem crescente
