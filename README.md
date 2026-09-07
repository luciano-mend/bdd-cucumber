# Automação de Testes BDD com Cucumber 7, Java 21 e Selenium 4

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)
![Cucumber](https://img.shields.io/badge/Cucumber-7.18.0-green.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.18.1-darkgreen.svg)
![JUnit 5](https://img.shields.io/badge/JUnit-5.10.2-orange.svg)

Projeto de automação de testes utilizando a metodologia **Behavior-Driven Development (BDD)** com **Cucumber 7**, **Java 21 (LTS)**, **JUnit 5 (Jupiter / Platform Engine)** e **Selenium WebDriver 4**. 

Este repositório engloba desde a validação de regras de negócio em nível de unidade (domínio de locadora de filmes) até a automação de testes funcionais ponta a ponta (E2E) em interface web.

---

## 📋 Sumário

- [Visão Geral](#-visão-geral)
- [Tecnologias e Ferramentas](#-tecnologias-e-ferramentas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Funcionalidades e Suítes de Testes](#-funcionalidades-e-suítes-de-testes)
  - [1. Domínio de Aluguel de Filmes (`@unitarios`)](#1-domínio-de-aluguel-de-filmes-unitarios)
  - [2. Automação Web Seu Barriga (`@funcionais`)](#2-automação-web-seu-barriga-funcionais)
- [Recursos do Cucumber e Selenium Utilizados](#-recursos-do-cucumber-e-selenium-utilizados)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar os Testes](#-como-executar-os-testes)
  - [Via Maven (Linha de Comando)](#via-maven-linha-de-comando)
  - [Via IDE (JUnit 5 Runners)](#via-ide-junit-5-runners)
- [Relatórios e Artefatos de Teste](#-relatórios-e-artefatos-de-teste)

---

## 🔍 Visão Geral

O projeto foi construído para demonstrar a aplicação prática de especificação executável utilizando a linguagem Gherkin em português (`# language: pt`) atualizado para os padrões modernos da JVM.

Os testes cobrem:
- **Testes Unitários de Domínio**: Validação de regras de cálculo de aluguel, prazo de entrega, controle de estoque e programa de pontuação.
- **Testes de Integração e Interface (Web)**: Automação das telas de cadastro de contas do sistema web [Seu Barriga](https://seubarriga.wcaquino.me), incluindo login, inserção de contas e validações de formulário com suporte a screenshots automáticos e gerenciamento autônomo do browser via **Selenium Manager**.

---

## 🛠 Tecnologias e Ferramentas

| Tecnologia / Biblioteca | Versão | Descrição |
| :--- | :---: | :--- |
| **Java SDK** | 21 | Linguagem base do projeto (LTS) |
| **Maven** | 3.6+ | Gerenciador de dependências e build |
| **Cucumber Java** | 7.18.0 | Framework BDD para escrita e execução de cenários |
| **Cucumber JUnit Engine** | 7.18.0 | Engine de execução do Cucumber no JUnit 5 Platform |
| **JUnit 5 (Jupiter & Suite)** | 5.10.2 / 1.10.2 | Suíte moderna de testes e runner `@Suite` |
| **Selenium Java** | 4.18.1 | Automação Web com Selenium Manager nativo |
| **Apache Commons IO** | 2.11.0 | Manipulação de arquivos para screenshots |

---

## 📁 Estrutura do Projeto

```text
bdd-cucumber/
├── pom.xml                                  # Configurações do Maven e dependências (Java 21, JUnit 5)
├── README.md                                # Documentação do projeto
├── .gitignore                               # Regras de exclusão do controle de versão
└── src/
    ├── main/java/br/luciano/                # Código de Produção (Domínio)
    │   ├── entidades/
    │   │   ├── Filme.java                   # Modelo do filme (estoque, preço)
    │   │   ├── NotaAluguel.java             # Comprovante (preço, data entrega, pontos)
    │   │   └── TipoAluguel.java             # Enum (COMUM, EXTENDIDO, SEMANAL)
    │   ├── servicos/
    │   │   └── AluguelService.java          # Regra de negócio de aluguel
    │   └── utils/
    │       └── DateUtils.java               # Manipulação e cálculo de datas
    │
    └── test/                                # Código de Testes
        ├── java/br/luciano/
        │   ├── config/
        │   │   └── RegistryCucumber.java    # Custom Parameter Types (@ParameterType do Cucumber 7)
        │   ├── runners/
        │   │   ├── RunnerTest.java          # Runner JUnit 5 Suite para suíte @unitarios
        │   │   └── RunnerFuncionatlTest.java# Runner JUnit 5 Suite para suíte @funcionais (Web)
        │   └── steps/
        │       ├── AlugarFilmeStpes.java    # Step definitions de aluguel
        │       ├── AprenderCucumberSteps.java# Step definitions de aprendizado
        │       └── InserirContasSteps.java  # Step definitions Web Seu Barriga (Selenium 4)
        │
        └── resources/features/             # Especificações BDD (Gherkin pt-BR)
            ├── alugar_filme.feature         # Cenários de aluguel de filme
            ├── aprender_cucumber.feature    # Cenários de sintaxe Gherkin
            └── inserir_conta.feature        # Cenários funcionais de formulário web
```

---

## 🧪 Funcionalidades e Suítes de Testes

### 1. Domínio de Aluguel de Filmes (`@unitarios`)
- **Aluguel Simples**: Verifica dedução de estoque e prazo padrão de entrega (1 dia).
- **Validação de Estoque**: Impede aluguel quando o estoque é 0.
- **Tipos de Aluguel (Esquema do Cenário)**:
  - **Comum**: Preço normal, entrega em 1 dia, 1 ponto.
  - **Estendido**: Preço dobrado, entrega em 3 dias, 2 pontos.
  - **Semanal**: Preço triplicado, entrega em 7 dias, 3 pontos.

### 2. Automação Web Seu Barriga (`@funcionais`)
- **Navegação e Autenticação**: Realiza login no sistema com credenciais de teste.
- **Adição de Contas**: Valida os cenários de:
  - Cadastro de conta com sucesso.
  - Tentativa de cadastro sem nome da conta (mensagem de erro).
  - Tentativa de cadastro de conta duplicada.
- **Selenium Manager**: Download e inicialização transparente do `chromedriver` sem necessidade de driver binário pré-instalado.
- **Hooks de Teardown (`@After`)**:
  - Captura automática de tela (screenshot `.jpg`) ao término de cada cenário funcional e armazena em `target/screenshot/`.
  - Encerramento automático do `WebDriver`.

---

## 💡 Recursos do Cucumber e Selenium Utilizados

- **Linguagem Pt-BR**: Palavras-chave `Funcionalidade`, `Cenário`, `Esquema do Cenário`, `Contexto`, `Dado`, `Quando`, `Então`, `E`.
- **DataTables**: Mapeamento de objetos complexos diretamente da tabela Gherkin para Java `Map<String, String>`.
- **Custom Parameter Types**: Uso da anotação `@ParameterType` (`RegistryCucumber.java`) para transformar parâmetros de data no formato `dd/MM/yyyy`.
- **JUnit 5 `@Suite`**: Configuração declarativa dos runners via `@IncludeEngines("cucumber")` e `@ConfigurationParameter`.
- **Selenium Manager**: Gerenciamento automático de drivers para o navegador Google Chrome.

---

## ⚙️ Pré-requisitos

Para compilar e executar este projeto localmente:

1. **Java JDK 21** ou superior instalado e configurado nas variáveis de ambiente (`JAVA_HOME`).
2. **Apache Maven 3.6+** instalado e configurado (`PATH`).
3. **Google Chrome** instalado no sistema (o `chromedriver` é baixado e gerenciado automaticamente pelo Selenium 4).

---

## 🚀 Como Executar os Testes

### Via Maven (Linha de Comando)

Para compilar e executar todos os testes (Unitários e Funcionais Web):
```bash
mvn clean test
```

Para executar apenas a suíte de testes unitários de regras de negócio:
```bash
mvn test -Dcucumber.filter.tags="@unitarios and not @ignore"
```

Para executar apenas a suíte de testes funcionais Web:
```bash
mvn test -Dcucumber.filter.tags="@funcionais"
```

### Via IDE (JUnit 5 Runners)

Você pode rodar os testes diretamente da sua IDE preferida (Eclipse, IntelliJ IDEA, VS Code):
- **Testes Unitários**: Execute a classe [`RunnerTest.java`](file:///c:/Users/Luciano/Documents/Projetos/bdd-cucumber/src/test/java/br/luciano/runners/RunnerTest.java) como **JUnit Test**.
- **Testes Funcionais**: Execute a classe [`RunnerFuncionatlTest.java`](file:///c:/Users/Luciano/Documents/Projetos/bdd-cucumber/src/test/java/br/luciano/runners/RunnerFuncionatlTest.java) como **JUnit Test**.

---

## 📊 Relatórios e Artefatos de Teste

Após a execução dos testes, os relatórios e evidências são gerados no diretório `target/`:

- **Relatório HTML**: `target/report-html.html`
- **Relatório JSON**: `target/report.json`
- **Screenshots (Evidências Web)**: `target/screenshot/`
