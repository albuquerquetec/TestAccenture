# language: pt
@api
Funcionalidade: Book Store API
  Como um usuario da Book Store
  Quero criar uma conta, autoriza-la e reservar livros
  Para consultar meu perfil com os livros escolhidos

  Cenario: Criar usuario, autorizar, reservar dois livros e consultar detalhes
    Dado que um usuario e criado
    Quando um token de acesso e gerado
    E o usuario e confirmado como autorizado
    E os livros disponiveis sao listados
    E dois livros sao alugados
    Entao os detalhes do usuario devem conter os livros reservados
