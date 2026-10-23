# language: pt
@ui @windows
Funcionalidade: Browser Windows
  Como um usuario
  Quero abrir uma nova janela
  Para validar o conteudo exibido

  Cenario: Abrir nova janela e validar a mensagem
    Dado que o usuario acessa o site DemoQA
    Quando escolhe a opcao "Alerts, Frame & Windows" na pagina inicial
    E clica no submenu "Browser Windows"
    E clica no botao New Window
    Entao uma nova janela deve ser aberta com a mensagem "This is a sample page"
    E a nova janela e fechada
