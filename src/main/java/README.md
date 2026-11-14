# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

## Sobre o projeto

Este projeto consiste em um sistema de gerenciamento de funcionários e cálculo de folha de pagamento desenvolvido em Java.

O sistema permite cadastrar, consultar, listar e remover funcionários, além de calcular a remuneração individual, o valor total da folha de pagamento e gerar relatórios com as informações dos funcionários cadastrados.

O projeto foi desenvolvido utilizando conceitos de Programação Orientada a Objetos (POO), como encapsulamento, herança, abstração, polimorfismo e interfaces, além de tratamento de exceções e testes automatizados com JUnit 5.

## Funcionalidades

- Cadastro de funcionários.
- Consulta de funcionários por ID, CPF ou nome.
- Listagem de funcionários cadastrados.
- Remoção de funcionários.
- Validação de dados dos funcionários.
- Identificação de funcionários duplicados.
- Cálculo de remuneração de acordo com o tipo de funcionário.
- Cálculo do valor total da folha de pagamento.
- Geração de relatórios com informações dos funcionários.

### Tipos de funcionários

O sistema trabalha com três tipos de funcionários:

| Tipo | Descrição |
|---|---|
| CLT | Funcionários com salário-base e benefícios. |
| PJ | Funcionários cuja remuneração é calculada com base no valor e na quantidade de projetos. |
| Comissionado | Funcionários cuja remuneração considera o salário-base e o percentual de comissão sobre as vendas. |

Cada tipo de funcionário possui sua própria implementação do método de cálculo de remuneração.

## Tecnologias utilizadas

- **Java 21:** linguagem de programação.
- **Maven:** gerenciamento de dependências e construção do projeto.
- **JUnit 5:** desenvolvimento e execução de testes automatizados.
- **IntelliJ IDEA:** ambiente de desenvolvimento.
- **Git e GitHub:** controle de versão e armazenamento do código-fonte.

## Estrutura do projeto

O projeto utiliza uma arquitetura em camadas, separando as responsabilidades de cada parte do sistema.

```text
EmployeeManagement/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── model/
│   │       │   ├── Funcionario.java
│   │       │   ├── FuncionarioCLT.java
│   │       │   ├── FuncionarioPJ.java
│   │       │   ├── FuncionarioComissionado.java
│   │       │   ├── Identificavel.java
│   │       │   └── Tributavel.java
│   │       │
│   │       ├── repository/
│   │       │   └── FuncionarioRepository.java
│   │       │
│   │       ├── service/
│   │       │   ├── FuncionarioService.java
│   │       │   └── FolhaPagamentoService.java
│   │       │
│   │       ├── view/
│   │       │   └── FuncionarioView.java
│   │       │
│   │       ├── exception/
│   │       │   ├── FuncionarioNaoEncontradoException.java
│   │       │   └── FuncionarioJaCadastradoException.java
│   │       │
│   │       └── Main.java
│   │
│   └── test/
│       └── java/
│           ├── model/
│           ├── repository/
│           ├── service/
│           └── exception/
│
├── pom.xml
└── README.md
```

### Responsabilidades das camadas

**Model**

Contém as classes que representam os funcionários, suas características e regras específicas de remuneração.

**Repository**

Responsável pelo armazenamento, recuperação, consulta e remoção dos funcionários. O armazenamento é realizado em memória utilizando `HashMap`.

**Service**

Contém a lógica de negócio e coordena as operações do sistema.

- `FuncionarioService`: responsável pelo cadastro, consulta, listagem e remoção de funcionários.
- `FolhaPagamentoService`: responsável pelo cálculo da folha de pagamento e geração de relatórios.

**View**

Responsável pela interação com o usuário por meio de um menu no terminal, recebendo informações e apresentando os resultados das operações.

**Exception**

Contém as exceções personalizadas utilizadas para representar situações excepcionais, como funcionários não encontrados ou já cadastrados.

## Como executar o projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- JDK 21 ou superior.
- Maven.
- Git (opcional, para clonar o repositório).

### 1. Clonar o repositório

```bash
git clone https://github.com/londerorichard/EmployeeManagement.git
```

### 2. Acessar a pasta do projeto

```bash
cd EmployeeManagement
```

### 3. Compilar e executar os testes

```bash
mvn clean test
```

### 4. Executar o sistema

Após compilar o projeto, execute a classe `Main.java` pela sua IDE.

Também é possível executar pelo terminal, utilizando:

```bash
mvn compile
java -cp target/classes Main
```

## Como executar os testes

O projeto utiliza JUnit 5 para a implementação de testes automatizados.

Para executar todos os testes pelo Maven, utilize:

```bash
mvn test
```

Para limpar os arquivos compilados anteriormente e executar todos os testes novamente:

```bash
mvn clean test
```

Os testes verificam o comportamento das classes, incluindo:

- Inicialização e validação dos funcionários.
- Cálculo de remuneração para cada tipo de funcionário.
- Cadastro e identificação de funcionários duplicados.
- Consulta, armazenamento, listagem e remoção de funcionários.
- Cálculo da folha de pagamento.
- Geração de relatórios.
- Lançamento de exceções personalizadas.

## Testes e desenvolvimento (TDD)

O desenvolvimento utiliza a abordagem **Test-Driven Development (TDD)**, seguindo o ciclo:

1. **Red:** criação de um teste que inicialmente falha.
2. **Green:** implementação do código necessário para passar no teste.
3. **Refactor:** melhoria e organização do código, mantendo os testes funcionando.

Os testes são organizados em classes correspondentes às classes de produção, utilizando JUnit 5.

## Armazenamento de dados

Os funcionários são armazenados em memória utilizando uma estrutura `HashMap<Integer, Funcionario>`, na qual o ID do funcionário é utilizado como chave.

Os dados não são persistidos em um banco de dados. Portanto, os funcionários cadastrados são perdidos quando a aplicação é encerrada.

## Controle de versão

O projeto utiliza Git para registrar o desenvolvimento progressivo da aplicação.

Os commits são organizados de acordo com as funcionalidades implementadas, correções, testes e melhorias realizadas durante o desenvolvimento.

## Autor

Desenvolvido como atividade prática avaliativa da disciplina de Introdução a Programação Orientada a Objetos.