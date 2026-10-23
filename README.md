# Desafio QA Automation – Accenture

Automação do desafio técnico da Accenture em **Java**, com:

- **Parte 1 (API):** RestAssured
- **Parte 2 (UI):** Selenium WebDriver + **Page Object**
- **BDD:** Cucumber (features em português)
- Execução contínua de todos os passos com uma única suíte Maven

## Pré-requisitos

- JDK 11 ou superior (testado com JDK 15)
- Maven 3.9+ (opcional se usar o wrapper `mvnw` / `mvnw.cmd`)
- Google Chrome

## Como executar

No Windows, use `mvnw.cmd`. No Linux/macOS, use `./mvnw`.

Todos os testes (API + UI):

```bash
mvnw.cmd test
```

Somente API:

```bash
mvnw.cmd test -Dtest=ApiTestRunner
```

Somente UI:

```bash
mvnw.cmd test -Dtest=UiTestRunner
```

UI em modo headless:

```bash
mvnw.cmd test -Dtest=UiTestRunner -Dheadless=true
```

O relatório HTML é gerado em `target/cucumber-report.html`.

## Cobertura

### Parte 1 – Book Store API

Fluxo único e contínuo:

1. Criar usuário (`POST /Account/v1/User`)
2. Gerar token (`POST /Account/v1/GenerateToken`)
3. Confirmar autorização (`POST /Account/v1/Authorized`)
4. Listar livros (`GET /BookStore/v1/Books`)
5. Alugar dois livros (`POST /BookStore/v1/Books`)
6. Consultar detalhes do usuário com os livros (`GET /Account/v1/User/{userID}`)

### Parte 2 – Frontend

| Teste | Fluxo |
| --- | --- |
| 1 | Forms → Practice Form → preenchimento aleatório + upload `.txt` → submit → popup → fechar |
| 2 | Alerts, Frame & Windows → Browser Windows → New Window → validar mensagem → fechar |
| 3 | Elements → Web Tables → criar → editar → deletar |
| Bônus | Criar 12 registros dinamicamente via Cucumber e deletar todos |
| 4 | Widgets → Progress Bar → parar ≤ 25% → concluir 100% → reset |
| 5 | Interactions → Sortable → drag and drop em ordem crescente |

O arquivo de upload está versionado em `src/test/resources/upload/sample.txt`.

## Estrutura

```
src/test/java/com/desafio/accenture/qa
  api/          clientes RestAssured, modelos e steps
  ui/pages/     Page Objects
  ui/steps/     step definitions da UI
  hooks/        abertura e fechamento do browser
  runners/      TestRunner, ApiTestRunner, UiTestRunner
src/test/resources/features
  api/          bookstore.feature
  ui/           cenários de interface
```
