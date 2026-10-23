# language: pt
@ui @widgets
Funcionalidade: Progress Bar
  Como um usuario
  Quero controlar a barra de progresso
  Para interromper, validar o percentual e resetar

  Cenario: Parar antes de 25%, concluir ate 100% e resetar
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Widgets" na pagina inicial
    E clica no submenu "Progress Bar"
    E inicia a progress bar e para antes dos 25%
    Entao o valor da progress bar deve ser menor ou igual a 25%
    Quando inicia novamente e aguarda 100%
    E reseta a progress bar
    Entao a progress bar deve voltar ao inicio
