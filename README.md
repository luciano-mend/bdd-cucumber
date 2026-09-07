# Automação de Testes BDD com Cucumber e Java

![Java](https://img.shields.io/badge/Java-8-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)
![Cucumber](https://img.shields.io/badge/Cucumber-4.8.1-green.svg)
![Selenium](https://img.shields.io/badge/Selenium-3.4.0-darkgreen.svg)
![JUnit](https://img.shields.io/badge/JUnit-4.12-orange.svg)

Projeto de automação de testes utilizando a metodologia **Behavior-Driven Development (BDD)** com **Cucumber**, **Java 8**, **JUnit 4** e **Selenium WebDriver**. 

Este repositório engloba desde a validação de regras de negócio em nível de unidade (domínio de locadora de filmes) até a automação de testes funcionais ponta a ponta (E2E) em interface web.

---

## 📋 Sumário

- [Visão Geral](#-visão-geral)
- [Tecnologias e Ferramentas](#-tecnologias-e-ferramentas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Funcionalidades e Suítes de Testes](#-funcionalidades-e-suítes-de-testes)
  - [1. Domínio de Aluguel de Filmes (`@unitarios`)](#1-domínio-de-aluguel-de-filmes-unitarios)
  - [2. Automação Web Seu Barriga (`@funcionais`)](#2-automação-web-seu-barriga-funcionais)
- [Recursos do Cucumber Utilizados](#-recursos-do-cucumber-utilizados)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar os Testes](#-como-executar-os-testes)
  - [Via Maven (Linha de Comando)](#via-maven-linha-de-comando)
  - [Via IDE (JUnit Runners)](#via-ide-junit-runners)
- [Relatórios e Artefatos de Teste](#-relatórios-e-artefatos-de-teste)

---

## 🔍 Visão Geral

O projeto foi construído para demonstrar a aplicação prática de especificação executável utilizando a linguagem Gherkin em português (`# language: pt`). 

Os testes cobrem:
- **Testes Unitários de Domínio**: Validação de regras de cálculo de aluguel, prazo de entrega, controle de estoque e programa de pontuação.
- **Testes de Integração e Interface (Web)**: Automação das telas de cadastro de contas do sistema web [Seu Barriga](https://seubarriga.wcaquino.me), incluindo login, inserção de contas e validações de formulário com suporte a screenshots automáticos.

---

## 🛠 Tecnologias e Ferramentas

| Tecnologia / Biblioteca | Versão | Descrição |
| :--- | :---: | :--- |
| **Java SDK** | 8 | Linguagem base do projeto |
| **Maven** | 3.6+ | Gerenciador de dependências e build |
| **Cucumber Java** | 4.8.1 | Framework BDD para escrita e execução de cenários |
| **Cucumber JUnit** | 4.8.1 | Integração do Cucumber com a suíte de execução JUnit |
| **Selenium Java** | 3.4.0 | Automação da navegação e interações Web |
| **Apache Commons IO** | 2.11.0 | Manipulação de arquivos para screenshots |

---

## 📁 Estrutura do Projeto

```text
bdd-cucumber/
├── pom.xml                                  # Configurações do Maven e dependências
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
        │   │   └── RegistryCucumber.java    # Configuração de custom parameter types (Date)
        │   ├── runners/
        │   │   ├── RunnerTest.java          # Runner para suíte @unitarios
        │   │   └── RunnerFuncionatlTest.java# Runner para suíte @funcionais (Web)
        │   └── steps/
        │       ├── AlugarFilmeStpes.java    # Mapeamento dos passos de aluguel
        │       ├── AprenderCucumberSteps.java# Passos básicos de aprendizado
        │       └── InserirContasSteps.java  # Passos funcionais do Seu Barriga (Selenium)
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
- **Hooks de Teardown (`@After`)**:
  - Captura automática de tela (screenshot `.jpg`) ao término de cada cenário funcional e armazena no diretório `target/screenshot/`.
  - Encerramento automático do `WebDriver`.

---

## 💡 Recursos do Cucumber Utilizados

- **Linguagem Pt-BR**: Palavras-chave `Funcionalidade`, `Cenário`, `Esquema do Cenário`, `Contexto`, `Dado`, `Quando`, `Então`, `E`.
- **DataTables**: Mapeamento de objetos complexos diretamente da tabela Gherkin para Java `Map<String, String>`.
- **Custom Parameter Types**: Implementação do `TypeRegistryConfigurer` (`RegistryCucumber.java`) para transformar parâmetros do tipo data no formato `dd/MM/yyyy`.
- **Tags**: Separação de execução entre `@unitarios` e `@funcionais`.
- **Hooks (`@BeforeClass` e `@After`)**: Reset de estado da aplicação web e captura de evidências.

---

## ⚙️ Pré-requisitos

Para compilar e executar este projeto localmente, certifique-se de possuir:

1. **Java JDK 8** ou superior instalado e configurado nas variáveis de ambiente (`JAVA_HOME`).
2. **Apache Maven 3.6+** instalado e configurado (`PATH`).
3. **Google Chrome** e o **ChromeDriver** compatível no `PATH` do sistema (necessário para os testes funcionais web `@funcionais`).

---

## 🚀 Como Executar os Testes

### Via Maven (Linha de Comando)

Para compilar e executar todos os testes:
```bash
mvn clean test
```

Para executar apenas os testes unitários de regras de negócio:
```bash
mvn test -Dcucumber.options="--tags @unitarios"
```

Para executar apenas os testes funcionais Web:
```bash
mvn test -Dcucumber.options="--tags @funcionais"
```

### Via IDE (JUnit Runners)

Você pode rodar os testes diretamente da sua IDE preferida (Eclipse, IntelliJ IDEA, VS Code):
- **Testes Unitários**: Execute a classe [`RunnerTest.java`](file:///c:/Users/Luciano/Documents/Projetos/bdd-cucumber/src/test/java/br/luciano/runners/RunnerTest.java) como **JUnit Test**.
- **Testes Funcionais**: Execute a classe [`RunnerFuncionatlTest.java`](file:///c:/Users/Luciano/Documents/Projetos/bdd-cucumber/src/test/java/br/luciano/runners/RunnerFuncionatlTest.java) como **JUnit Test**.

---

## 📊 Relatórios e Artefatos de Teste

Após a execução dos testes, os relatórios e evidências são gerados no diretório `target/`:

- **Relatório HTML**: `target/report-html/index.html`
- **Relatório JSON**: `target/report.json`
- **Screenshots (Evidências Web)**: `target/screenshot/`
