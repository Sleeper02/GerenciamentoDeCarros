# Gerenciamento de Carros

Um sistema web desenvolvido em **Spring Boot** para estudar e aplicar conceitos modernos de desenvolvimento Java. O projeto implementa um gerenciador completo de carros, clientes e marcas com APIs REST, interface web e banco de dados.

---

## 📋 Tabela de Conteúdos

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Estrutura](#-arquitetura-e-estrutura)
- [Funcionalidades](#-funcionalidades)
- [Como Funciona](#-como-funciona)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Endpoints da API](#-endpoints-da-api)
- [Conceitos de Aprendizagem](#-conceitos-de-aprendizagem)

---

## Visão Geral

Este projeto é um **Sistema de Gerenciamento de Carros** que permite:
- Gerenciar **Clientes** (nome, CPF, email, telefone, endereço)
- Cadastrar **Carros** (modelo, placa, imagem)
- Administrar **Marcas** de veículos
- Visualizar relacionamentos entre clientes e veículos

O projeto possui tanto uma **API REST** quanto uma **Interface Web** com Thymeleaf para interação.

---

## 🛠 Tecnologias Utilizadas

### Backend
| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.4 | Framework web e configuração automática |
| **Spring Data JPA** | - | Acesso e mapeamento de dados |
| **Spring Boot Web MVC** | - | Construção de APIs REST e Controllers da interface web (via dependência `spring-boot-starter-webmvc`) |
| **Hibernate** | - | ORM (Object-Relational Mapping) |
| **H2 Database** | - | Banco de dados em memória |
| **Flyway** | - | Migrações de banco de dados |
| **Lombok** | - | Redução de boilerplate com anotações |
| **Validation** | - | Validação de dados de entrada |
| **SpringDoc OpenAPI** | 3.0.2 | Documentação automática de API (Swagger) |

### Frontend
| Tecnologia | Propósito |
|-----------|----------|
| **Thymeleaf** | Template engine HTML |
| **CSS** | Estilização das páginas |

### Build
| Ferramenta | Propósito |
|-----------|----------|
| **Maven** | Gerenciador de dependências e build |

---

## 🏗 Arquitetura e Estrutura

O projeto segue o padrão **MVC (Model-View-Controller)** com separação clara de responsabilidades:

```
src/main/java/Algx/GerenciamentoDeCarros/
├── GerenciamentoDeCarrosApplication.java    # Classe principal da aplicação
├── RegraNegocioException.java               # Exceção customizada
│
├── Carros/                                  # Módulo de Carros
│   ├── CarroModel.java                      # Entidade (JPA)
│   ├── CarroDTO.java                        # Data Transfer Object
│   ├── CarroMapper.java                     # Conversão Model ↔ DTO
│   ├── CarroRepository.java                 # Persistência
│   ├── CarroService.java                    # Lógica de negócio
│   ├── CarroController.java                 # API REST
│   └── CarroControllerUi.java               # Controller para web
│
├── Clientes/                                # Módulo de Clientes
│   ├── ClienteModel.java                    # Entidade (JPA)
│   ├── ClienteDTO.java                      # Data Transfer Object
│   ├── ClienteMapper.java                   # Conversão Model ↔ DTO
│   ├── ClienteRepository.java               # Persistência
│   ├── ClienteService.java                  # Lógica de negócio
│   ├── ClienteController.java               # API REST
│   └── ClienteControllerUi.java             # Controller para web
│
└── Marca/                                   # Módulo de Marcas
    ├── MarcaModel.java                      # Entidade (JPA)
    ├── MarcaDTO.java                        # Data Transfer Object
    ├── MarcaMapper.java                     # Conversão Model ↔ DTO
    ├── MarcaRepository.java                 # Persistência
    ├── MarcaService.java                    # Lógica de negócio
    ├── MarcaController.java                 # API REST
    └── MarcaControllerUi.java               # Controller para web

src/main/resources/
├── application.properties                   # Configurações da aplicação
├── static/css/
│   └── style.css                            # Estilos CSS
├── templates/                               # Templates Thymeleaf
│   ├── adicionarCarro.html
│   ├── adicionarCliente.html
│   ├── adicionarMarca.html
│   ├── alterarCarro.html
│   ├── alterarCliente.html
│   ├── alterarMarca.html
│   ├── listarCarro.html
│   ├── listarCliente.html
│   └── listarMarca.html
└── db/migrations/                           # Scripts Flyway
```

### Padrão de Camadas

```
┌─────────────────────────────────────────┐
│         UI / Frontend (Thymeleaf)        │
├─────────────────────────────────────────┤
│  Controllers (CarroControllerUi, etc)   │  ← Recebem requisições HTTP
├─────────────────────────────────────────┤
│  Services (CarroService, etc)           │  ← Lógica de negócio
├─────────────────────────────────────────┤
│  Repositories (CarroRepository, etc)    │  ← Acesso ao banco de dados
├─────────────────────────────────────────┤
│  Database (H2)                          │  ← Persistência
└─────────────────────────────────────────┘
```

---

## 💡 Funcionalidades

### Gerenciamento de Carros
- ✅ Criar novo carro
- ✅ Listar todos os carros
- ✅ Atualizar informações do carro
- ✅ Deletar carro
- ✅ Vincular marca ao carro
- ✅ Vincular cliente ao carro
- ✅ Upload de imagem do carro

### Gerenciamento de Clientes
- ✅ Criar novo cliente
- ✅ Listar todos os clientes
- ✅ Atualizar dados do cliente
- ✅ Deletar cliente
- ✅ Validação de CPF e email únicos

### Gerenciamento de Marcas
- ✅ Criar nova marca
- ✅ Listar todas as marcas
- ✅ Atualizar marca
- ✅ Deletar marca (se não tiver carros associados)

### API REST
- ✅ Endpoints JSON para todas as operações
- ✅ Documentação automática via Swagger/OpenAPI
- ✅ Validação de dados

### Interface Web
- ✅ Páginas HTML com Thymeleaf
- ✅ Formulários interativos
- ✅ Listagens com design responsivo
- ✅ Estilos CSS customizados

---

## 🔄 Como Funciona

### Fluxo de Requisição

```
1. Usuário acessa página via navegador
   ↓
2. CarroControllerUi recebe a requisição
   ↓
3. CarroService executa lógica de negócio
   ↓
4. CarroRepository persiste/recupera dados do banco
   ↓
5. Banco H2 armazena os dados
   ↓
6. Resposta volta com dados ou confirmação
   ↓
7. Thymeleaf renderiza HTML com os dados
```

### Relacionamento entre Entidades

```
┌──────────────────────┐
│      MARCA           │
│ (tb_marcas)          │
├──────────────────────┤
│ id (PK)              │
│ nome (UNIQUE)        │
└──────────────────────┘
         ▲
         │ 1:N
         │
┌──────────────────────┐
│      CARRO           │
│ (tb_carros)          │
├──────────────────────┤
│ id (PK)              │
│ modelo               │
│ placa (UNIQUE)       │
│ marca_id (FK)        │◄────┐
│ imgUrl               │     │
└──────────────────────┘     │
         │                   │
         │ 1:1               │
         ▼                   │
┌──────────────────────┐     │
│     CLIENTE          │     │
│ (tb_clientes)        │     │
├──────────────────────┤     │
│ id (PK)              │     │
│ nome                 │     │
│ cpf (UNIQUE)         │     │
│ email (UNIQUE)       │     │
│ telefone             │     │
│ endereco             │     │
│ carro_id (FK)        │─────┘
└──────────────────────┘
```

**Relacionamentos:**
- **Marca 1:N Carro** - Uma marca pode ter vários carros
- **Carro 1:1 Cliente** - Um carro pertence a um cliente (cliente pode ter apenas um carro)

---

## 📋 Pré-requisitos

- **Java 17** ou superior instalado
- **Maven 3.6+** (ou usar o Maven Wrapper incluído)
- **IDE** (VS Code, IntelliJ IDEA, Eclipse) - opcional
- Navegador web moderno

### Verificar Instalação

```bash
java -version
mvn -version
```

---

## 🚀 Como Executar

### 1️⃣ Clonar ou abrir o projeto

```bash
cd GerenciamentoDeCarros
```

### 2️⃣ Compilar o projeto

**No Windows:**
```bash
mvnw.cmd clean install
```

**No Linux/Mac:**
```bash
./mvnw clean install
```

**Com Maven instalado globalmente:**
```bash
mvn clean install
```

### 3️⃣ Executar a aplicação

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

**Com Maven instalado:**
```bash
mvn spring-boot:run
```

### 4️⃣ Acessar a aplicação

- **Interface Web:** http://localhost:8080
- **API REST:** http://localhost:8080/api
- **Documentação Swagger:** http://localhost:8080/swagger-ui.html
- **Console H2:** http://localhost:8080/h2-console

### 5️⃣ Credenciais do Banco de Dados (H2)

O projeto está configurado para buscar os dados de conexão através de variáveis de ambiente em vez de utilizar credenciais estáticas no código. Antes de executar, certifique-se de configurar (ou injetar) as seguintes variáveis:
- **URL JDBC:** `${DATABASE_URL}` (ex: `jdbc:h2:mem:testdb` para uso local)
- **Usuário:** `${DATABASE_USERNAME}`
- **Senha:** `${DATABASE_PASSWORD}`

---

## 📡 Endpoints da API

### 🚗 Carros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/carros` | Listar todos os carros |
| `GET` | `/api/carros/{id}` | Obter carro por ID |
| `POST` | `/api/carros` | Criar novo carro |
| `PUT` | `/api/carros/{id}` | Atualizar carro |
| `DELETE` | `/api/carros/{id}` | Deletar carro |

### 👥 Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/clientes` | Listar todos os clientes |
| `GET` | `/api/clientes/{id}` | Obter cliente por ID |
| `POST` | `/api/clientes` | Criar novo cliente |
| `PUT` | `/api/clientes/{id}` | Atualizar cliente |
| `DELETE` | `/api/clientes/{id}` | Deletar cliente |

### 🏷️ Marcas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/marcas` | Listar todas as marcas |
| `GET` | `/api/marcas/{id}` | Obter marca por ID |
| `POST` | `/api/marcas` | Criar nova marca |
| `PUT` | `/api/marcas/{id}` | Atualizar marca |
| `DELETE` | `/api/marcas/{id}` | Deletar marca |

### Exemplo de Requisição (cURL)

```bash
# Criar nova marca
curl -X POST http://localhost:8080/api/marcas \
  -H "Content-Type: application/json" \
  -d '{"nome":"Toyota"}'

# Listar todos os carros
curl http://localhost:8080/api/carros

# Criar novo cliente
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"João Silva",
    "cpf":"12345678901",
    "email":"joao@email.com",
    "telefone":"11999999999",
    "endereco":"Rua A, 123"
  }'
```

---

## 📚 Conceitos de Aprendizagem

Este projeto foi desenvolvido para consolidar conhecimentos em:

### 🎯 Spring Boot
- Configuração automática do Spring
- Criação de projetos com Spring Initializr
- Maven para gerenciamento de dependências
- Executar e debugar aplicações

### 🗄️ Banco de Dados
- **JPA/Hibernate** - Mapeamento objeto-relacional
- **Relacionamentos** - OneToMany, OneToOne
- **H2 Database** - Banco de dados em memória
- **Flyway** - Versionamento de schema
- **Validação** - Constraints e anotações

### 🌐 Spring Web
- Controllers REST com `@RestController`
- Controllers Thymeleaf com `@Controller`
- Mapeamento de rotas com `@RequestMapping`, `@GetMapping`, etc.
- DTOs e Mappers para transferência de dados
- Injeção de dependência

### ✔️ Validação de Dados
- `@Valid` para validação
- Anotações `@NotNull`, `@NotBlank`, `@Email`
- Tratamento de exceções
- Exceções customizadas (`RegraNegocioException`)

### 🎨 Frontend
- **Thymeleaf** - Template engine dinâmico
- Integração com Spring MVC
- Formulários e binding de dados
- Iteração e condicionalismo em templates

### 📦 Padrões de Design
- **DTO Pattern** (Data Transfer Object)
- **Mapper Pattern** (Conversão de dados)
- **Repository Pattern** (Abstração de persistência)
- **Service Pattern** (Separação de lógica)
- **MVC Pattern** (Modelo-Visão-Controlador)

### 📖 Lombok
- Redução de boilerplate com anotações
- `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`
- `@ToString`, `@EqualsAndHashCode`
- `@Getter` e `@Setter`

### 🔗 SpringDoc OpenAPI
- Documentação automática de APIs
- Geração de Swagger UI
- Anotações para descrever endpoints

---

## 📝 Notas Importantes

- **Banco de dados:** O H2 cria um banco em memória que é resetado quando a aplicação encerra
- **Gerenciamento de Schema (Atenção):** Atualmente, a aplicação utiliza uma abordagem mista para a estrutura do banco. O Flyway está ativado para aplicar migrações a partir da pasta `db/migrations`, porém o Hibernate também está configurado para atualizar o schema automaticamente (`spring.jpa.hibernate.ddl-auto=update`). Para ambientes de produção no futuro, recomenda-se desativar o update do Hibernate para evitar conflitos com os scripts do Flyway.
- **Lombok:** Reduz significativamente o código boilerplate em Classes Model
- **DTOs:** Separam a representação interna do dado da transferência via API
- **Validação:** Ocorre tanto no nível de Controller (Spring Validation) quanto no Service (regras de negócio)

---

## 🎓 Reflexões e Melhorias Futuras

Este projeto é uma excelente base para:
- ✨ Adicionar autenticação e autorização (Spring Security)
- ✨ Implementar testes unitários e de integração (JUnit, Mockito)
- ✨ Migrar para banco de dados relacional (PostgreSQL, MySQL)
- ✨ Criar camada de cache (Redis)
- ✨ Implementar paginação e filtros
- ✨ Adicionar logs estruturados
- ✨ Containerizar com Docker
- ✨ Implementar CI/CD com GitHub Actions

---

## 📌 Autor

Projeto desenvolvido como estudo prático do curso **Java 10x** - Gerência de Carros com Spring Boot.

---

## 📄 Licença

Este projeto é fornecido como material educacional.

---

**Última atualização:** Abril 2026
