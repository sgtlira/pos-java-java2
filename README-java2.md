# Java II — Tópicos Avançados

Repositório destinado às atividades práticas da disciplina **Java II — Tópicos Avançados**, da Pós-Graduação em Desenvolvimento Java.

O objetivo deste repositório é registrar a evolução dos conhecimentos em Java moderno, desenvolvimento orientado a objetos, boas práticas de projeto, validação, consumo de APIs, programação assíncrona, persistência de dados, testes automatizados e análise de dados.

## 📚 Atividades

### 01 — Framework de Validação

Nesta atividade foi desenvolvido um pequeno **framework de validação utilizando recursos avançados da linguagem Java**.

O projeto explora a criação de validações reutilizáveis por meio de **anotações personalizadas** e processamento via **Reflection**.

#### Principais conceitos implementados

- Anotações personalizadas, como:
  - `@NaoNulo`
  - `@Tamanho`
  - `@Positivo`
- API de Reflection para inspeção de classes, campos e anotações em tempo de execução.
- Criação de um mecanismo genérico de validação.
- Uso de **Generics**.
- Compreensão de **Type Erasure**.
- Uso de **Records** para representar objetos de dados de forma mais concisa.
- Uso de **Sealed Classes**, restringindo quais classes podem participar de uma hierarquia.
- **Pattern Matching**, permitindo realizar verificações e conversões de tipos de maneira mais expressiva.
- Uso de `BigDecimal` para representação de valores monetários.
- Criação de objetos para representar resultados e violações de validação.
- Testes automatizados com Maven/JUnit.

#### Estrutura conceitual

O fluxo principal da solução consiste em:

```text
Objeto
   ↓
Validador
   ↓
Reflection
   ↓
Anotações presentes nos campos
   ↓
Regras de validação
   ↓
Resultado da validação
   ↓
Violações encontradas
```

A atividade demonstra como o Java pode ser utilizado para construir componentes reutilizáveis e extensíveis, reduzindo a necessidade de implementar manualmente as mesmas regras de validação em diferentes classes.

---

### 02 — Consumo de API e Persistência em Arquivo

Nesta atividade foi desenvolvido um **cotador de moedas**, realizando o consumo de uma API externa e posteriormente processando e persistindo os resultados em arquivo.

O projeto trabalha com comunicação HTTP, JSON, programação assíncrona e manipulação de arquivos.

#### Principais conceitos implementados

- Consumo de **API REST externa**.
- Utilização do `HttpClient` da API moderna do Java.
- Requisições HTTP para obtenção de informações de moedas.
- Processamento de respostas em formato JSON.
- Programação assíncrona.
- Uso de `CompletableFuture`.
- Composição de operações assíncronas.
- Tratamento de exceções.
- Leitura de arquivos de entrada.
- Processamento das moedas informadas.
- Geração de arquivo de saída em formato CSV.
- Utilização de `LocalDateTime`.
- Testes automatizados.
- Utilização de **Mockito** para criação de mocks nos testes.
- Gerenciamento do projeto com **Maven**.

Exemplos de moedas utilizadas durante os testes:

```text
BRL
ABC
EUR
GBP
JPY
```

O projeto também trabalha com cenários de erro, como códigos de moedas inválidos e problemas durante a comunicação ou processamento.

#### Fluxo da aplicação

```text
Arquivo de entrada
       ↓
Leitura das moedas
       ↓
Consumo da API REST
       ↓
Processamento da resposta
       ↓
Tratamento de erros
       ↓
Geração do resultado
       ↓
Arquivo CSV
```

A atividade permitiu aplicar na prática conceitos importantes de **integração entre sistemas**, **programação assíncrona** e **persistência em arquivos**.

---

### 03 — Faturamento de Pedidos

Nesta atividade foi desenvolvido um sistema relacionado ao **faturamento de pedidos**, aplicando conceitos de orientação a objetos e boas práticas de desenvolvimento.

Um dos principais objetivos foi trabalhar a organização do código utilizando princípios de projeto que favorecem **manutenção, reutilização e baixo acoplamento**.

#### Principais conceitos trabalhados

- Programação Orientada a Objetos.
- Separação de responsabilidades.
- Encapsulamento.
- Interfaces.
- Polimorfismo.
- Composição.
- Tratamento de diferentes comportamentos por meio de abstrações.
- Aplicação dos princípios **SOLID**.
- Uso de **Pattern Matching** quando aplicável.
- Organização das responsabilidades entre as classes.
- Testes automatizados.
- Gerenciamento das dependências e execução dos testes com Maven.

### SOLID

A atividade contribuiu para compreender e aplicar os cinco princípios SOLID:

| Princípio | Conceito |
|---|---|
| **S — Single Responsibility Principle** | Uma classe deve possuir uma responsabilidade bem definida. |
| **O — Open/Closed Principle** | O código deve estar aberto para extensão e fechado para modificações desnecessárias. |
| **L — Liskov Substitution Principle** | Subtipos devem poder substituir seus tipos base sem quebrar o comportamento esperado. |
| **I — Interface Segregation Principle** | Interfaces devem ser específicas e não obrigar classes a implementar métodos que não utilizam. |
| **D — Dependency Inversion Principle** | O código deve depender de abstrações, e não diretamente de implementações concretas. |

A aplicação desses princípios torna o código mais preparado para receber novas regras de negócio sem exigir alterações excessivas nas funcionalidades existentes.

---

### 04 — Análise de Dataset

A quarta atividade trabalhou a **análise de um conjunto de dados (dataset)** utilizando Java.

O objetivo foi aplicar os recursos da linguagem para leitura, transformação, filtragem e análise das informações presentes no conjunto de dados.

#### Principais conceitos trabalhados

- Leitura e processamento de dados.
- Manipulação de coleções.
- Uso de Streams.
- Operações de `filter`, `map`, `sorted`, `collect` e agregações.
- Organização e transformação dos dados.
- Consultas sobre o dataset.
- Tratamento dos resultados.
- Programação funcional utilizando recursos da API de Streams.
- Uso de recursos modernos do Java para tornar o processamento mais expressivo.

A atividade demonstra como recursos da linguagem podem ser combinados para transformar um conjunto de dados bruto em informações úteis para análise.

---

## 🧠 Principais conceitos estudados

Ao longo das quatro atividades foram trabalhados diversos recursos importantes do Java moderno:

### Java moderno

- Pattern Matching
- Records
- Sealed Classes
- Generics
- Type Erasure
- Reflection
- Annotations
- `BigDecimal`
- `LocalDateTime`
- Streams
- Programação funcional
- `CompletableFuture`

### Desenvolvimento de aplicações

- Programação Orientada a Objetos
- SOLID
- Interfaces
- Abstração
- Encapsulamento
- Polimorfismo
- Separação de responsabilidades
- Baixo acoplamento
- Tratamento de exceções

### Integração e persistência

- HTTP
- API REST
- `HttpClient`
- JSON
- Arquivos
- CSV
- Processamento de dados

### Testes

- JUnit
- Mockito
- Testes automatizados
- Mocks
- Validação de cenários de sucesso e erro

### Ferramentas

- Java
- Maven
- IntelliJ IDEA
- Git
- GitHub

---

## 🗂️ Organização do repositório

```text
pos-java-java2/
│
├── 01-Framework-Validacao/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── 02-Consumo-API-Persistencia/
│   ├── entrada/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── 03-Faturamento-Pedidos/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── 04-Analise-Dataset/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── .gitignore
└── README.md
```

Cada atividade possui seu próprio projeto Maven, permitindo que os exercícios sejam executados e testados de maneira independente.

---

## ▶️ Como executar

Cada atividade pode ser aberta individualmente no **IntelliJ IDEA** como um projeto Maven.

Também é possível utilizar o Maven Wrapper presente nos projetos:

### Windows

```bash
mvnw.cmd test
```

Para executar os testes.

Para compilar:

```bash
mvnw.cmd clean package
```

Os diretórios `target/` e arquivos específicos da IDE não são versionados no Git, conforme definido no `.gitignore`.

---

## 🎯 Objetivo acadêmico

Este repositório representa a evolução prática dos conhecimentos adquiridos na disciplina **Java II — Tópicos Avançados**.

Além de apresentar as soluções das atividades, o projeto busca demonstrar a aplicação prática de conceitos como **Java moderno, orientação a objetos, SOLID, Reflection, Generics, Pattern Matching, consumo de APIs REST, programação assíncrona, testes automatizados e análise de dados**.

O repositório também faz parte do portfólio acadêmico desenvolvido ao longo da Pós-Graduação em Desenvolvimento Java.
