# LinkeTinder - Backend API

## 📋 Sobre o Projeto

O **LinkeTinder** é uma plataforma inovadora de recrutamento que conecta candidatos e empresas de forma eficiente e inteligente. O projeto funciona como um "Tinder" para o mercado de trabalho, onde candidatos e empresas podem se conectar baseado em compatibilidade de competências, localização e interesses mútuos.

### 🎯 Objetivo Principal

Facilitar o processo de recrutamento através de um sistema de matching inteligente que considera:
- **Competências técnicas** dos candidatos e requisitos das vagas
- **Localização geográfica** para facilitar o trabalho presencial ou híbrido
- **Preferências mútuas** através de um sistema de "curtidas" bidirecionais
- **Perfis detalhados** tanto de candidatos quanto de empresas

### 💡 Conceito e Inovação

O LinkeTinder revoluciona o mercado de recrutamento ao:
- **Democratizar oportunidades**: Candidatos podem descobrir vagas baseadas em suas competências
- **Otimizar tempo**: Empresas encontram candidatos pré-qualificados rapidamente
- **Reduzir fricção**: Interface simples e intuitiva similar a apps de relacionamento
- **Aumentar assertividade**: Sistema de matching baseado em algoritmos de compatibilidade

## 🏗️ Arquitetura e Tecnologias

### Stack Tecnológico
- **Java 17+** - Linguagem principal (migrado do Groovy)
- **Spring Boot 3.x** - Framework principal
- **Spring Security** - Autenticação e autorização
- **JWT** - Gerenciamento de tokens
- **PostgreSQL** - Banco de dados relacional
- **Flyway** - Controle de versão do banco de dados
- **Gradle** - Gerenciamento de dependências e build
- **Spock Framework** - Testes (mantido em Groovy para flexibilidade)

### Padrões Arquiteturais
- **REST API** - Comunicação cliente-servidor
- **MVC (Model-View-Controller)** - Organização do código
- **Repository Pattern** - Abstração da camada de dados
- **DTO Pattern** - Transferência de dados
- **Service Layer** - Lógica de negócio
- **Exception Handling** - Tratamento centralizado de erros

## 📁 Estrutura do Projeto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── linketinder/
│   │   │           ├── LinketinderApplication.java     # Classe principal
│   │   │           ├── config/                         # Configurações
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/                     # Controladores REST
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── CandidateController.java
│   │   │           │   ├── EmployeeController.java
│   │   │           │   ├── JobController.java
│   │   │           │   ├── SkillsController.java
│   │   │           │   └── MatchController.java
│   │   │           ├── dto/                           # Data Transfer Objects
│   │   │           │   ├── CandidateDTO.java
│   │   │           │   ├── EmployeeDTO.java
│   │   │           │   ├── MatchDTO.java
│   │   │           │   └── TokenDTO.java
│   │   │           ├── exception/                     # Tratamento de exceções
│   │   │           │   ├── CustomEntityResponseHandler.java
│   │   │           │   ├── EntityNotFound.java
│   │   │           │   └── DuplicateEntity.java
│   │   │           ├── mapper/                        # Mapeamento DTO/Entity
│   │   │           │   ├── CandidateMapper.java
│   │   │           │   ├── EmployeeMapper.java
│   │   │           │   └── MatchMapper.java
│   │   │           ├── model/                         # Entidades JPA
│   │   │           │   ├── User.java
│   │   │           │   ├── Candidate.java
│   │   │           │   ├── Employee.java
│   │   │           │   ├── Job.java
│   │   │           │   ├── Skill.java
│   │   │           │   ├── Match.java
│   │   │           │   ├── CandidateSkill.java
│   │   │           │   └── JobSkill.java
│   │   │           ├── repository/                    # Repositórios JPA
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── CandidateRepository.java
│   │   │           │   ├── EmployeeRepository.java
│   │   │           │   ├── JobRepository.java
│   │   │           │   ├── SkillRepository.java
│   │   │           │   └── MatchRepository.java
│   │   │           ├── security/                      # Segurança e JWT
│   │   │           │   ├── JwtTokenProvider.java
│   │   │           │   └── JwtTokenFilter.java
│   │   │           └── service/                       # Lógica de negócio
│   │   │               ├── AuthService.java
│   │   │               ├── CandidateService.java
│   │   │               ├── EmployeeService.java
│   │   │               ├── JobService.java
│   │   │               ├── SkillsService.java
│   │   │               ├── MatchService.java
│   │   │               └── UserService.java
│   │   └── resources/
│   │       ├── application.properties                 # Configurações da aplicação
│   │       └── db/
│   │           └── migration/                         # Scripts Flyway
│   │               ├── V1__Create_initial_tables.sql
│   │               └── V2__Add_indexes.sql
│   └── test/
│       └── groovy/                                    # Testes (mantidos em Groovy)
│           └── com/
│               └── linketinder/
│                   ├── controller/                    # Testes dos controladores
│                   ├── service/                       # Testes dos serviços
│                   └── dto/                          # Testes dos DTOs
├── build.gradle                                       # Configuração do Gradle
├── gradlew                                           # Gradle Wrapper
└── README.md                                         # Este arquivo
```

## 🗄️ Modelo de Dados

### Entidades Principais

#### 👤 User
Entidade base para autenticação e autorização.
```java
- id: Long (PK)
- email: String (UNIQUE)
- password: String (encrypted)
- userType: UserType (CANDIDATE, EMPLOYEE)
- createdAt: LocalDateTime
```

#### 🧑‍💼 Candidate
Perfil de candidatos à procura de oportunidades.
```java
- id: Long (PK)
- user: User (FK)
- firstName: String
- lastName: String
- birthDate: LocalDate
- education: String
- description: String
- cep: String
- skills: List<CandidateSkill>
```

#### 🏢 Employee
Perfil de empresas que oferecem vagas.
```java
- id: Long (PK)
- user: User (FK)
- companyName: String
- cnpj: String
- description: String
- cep: String
- jobs: List<Job>
```

#### 💼 Job
Vagas oferecidas pelas empresas.
```java
- id: Long (PK)
- employee: Employee (FK)
- title: String
- description: String
- location: String
- requirements: List<JobSkill>
```

#### 🎯 Skill
Competências técnicas e habilidades.
```java
- id: Long (PK)
- name: String (UNIQUE)
- description: String
```

#### 💝 Match
Sistema de conexões entre candidatos e vagas.
```java
- id: Long (PK)
- candidate: Candidate (FK)
- job: Job (FK)
- candidateLike: Boolean
- employeeLike: Boolean
- matchDate: LocalDateTime
```

## 🔄 Fluxo de Funcionamento

### 1. **Cadastro e Autenticação**
- Usuários se registram como Candidato ou Empresa
- Sistema de autenticação JWT
- Perfis detalhados com competências e localização

### 2. **Descoberta de Oportunidades**
- Candidatos visualizam vagas compatíveis com suas competências
- Empresas visualizam candidatos qualificados para suas vagas
- Algoritmo de matching baseado em:
  - Compatibilidade de competências
  - Proximidade geográfica
  - Critérios específicos da vaga

### 3. **Sistema de Curtidas**
- Candidatos "curtem" vagas de interesse
- Empresas "curtem" candidatos adequados
- Match acontece quando há curtida mútua

### 4. **Conexão Estabelecida**
- Após match, ambas as partes podem se comunicar
- Histórico de matches para acompanhamento
- Métricas de compatibilidade

## 🚀 Principais Funcionalidades

### Para Candidatos
- ✅ Cadastro completo de perfil
- ✅ Gerenciamento de competências
- ✅ Descoberta de vagas compatíveis
- ✅ Sistema de curtidas em vagas
- ✅ Visualização de matches
- ✅ Histórico de candidaturas

### Para Empresas
- ✅ Cadastro de perfil corporativo
- ✅ Criação e gerenciamento de vagas
- ✅ Definição de competências obrigatórias
- ✅ Descoberta de candidatos qualificados
- ✅ Sistema de curtidas em candidatos
- ✅ Gestão de matches e candidaturas

### Sistema Geral
- ✅ Autenticação JWT segura
- ✅ API REST completa
- ✅ Validação de dados
- ✅ Tratamento de exceções
- ✅ Logs e monitoramento
- ✅ Testes automatizados

## 🛠️ Como Executar

### Pré-requisitos
- Java 17 ou superior
- PostgreSQL 12+
- Gradle 7.x

### Configuração do Banco de Dados
```sql
CREATE DATABASE linketinder;
CREATE USER linketinder_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE linketinder TO linketinder_user;
```

### Configuração da Aplicação
Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/linketinder
spring.datasource.username=linketinder_user
spring.datasource.password=your_password
jwt.secret=your_jwt_secret_key
```

### Executando a Aplicação
```bash
# Clone o repositório
git clone <repository-url>
cd LinkeTinder/backend

# Execute a aplicação
./gradlew bootRun
```

### Executando Testes
```bash
# Todos os testes
./gradlew test

# Testes específicos
./gradlew test --tests "*CandidateService*"
```

## 📚 API Endpoints

### Autenticação
- `POST /auth/signin` - Login de usuário
- `POST /auth/signup` - Cadastro de usuário

### Candidatos
- `GET /api/candidates` - Listar candidatos
- `POST /api/candidates` - Criar candidato
- `GET /api/candidates/{id}` - Buscar candidato
- `PUT /api/candidates/{id}` - Atualizar candidato
- `DELETE /api/candidates/{id}` - Remover candidato

### Empresas
- `GET /api/employees` - Listar empresas
- `POST /api/employees` - Criar empresa
- `GET /api/employees/{id}` - Buscar empresa
- `PUT /api/employees/{id}` - Atualizar empresa
- `DELETE /api/employees/{id}` - Remover empresa

### Vagas
- `GET /api/jobs` - Listar vagas
- `POST /api/jobs` - Criar vaga
- `GET /api/jobs/{id}` - Buscar vaga
- `PUT /api/jobs/{id}` - Atualizar vaga
- `DELETE /api/jobs/{id}` - Remover vaga

### Competências
- `GET /api/skills` - Listar competências
- `POST /api/skills` - Criar competência
- `GET /api/skills/{id}` - Buscar competência

### Matches
- `GET /api/matches` - Listar matches
- `POST /api/matches` - Criar match
- `GET /api/matches/candidate/{id}` - Matches do candidato
- `GET /api/matches/job/{id}` - Matches da vaga

## 🧪 Estratégia de Testes

### Testes Unitários
- **Spock Framework** (Groovy) para flexibilidade e expressividade
- Cobertura de todos os services e controllers
- Mocks para isolamento de dependências
- Testes de validação de dados

### Testes de Integração
- Testes de API com banco H2 em memória
- Validação de fluxos completos
- Testes de autenticação e autorização

## 🔒 Segurança

### Autenticação
- **JWT (JSON Web Tokens)** para autenticação stateless
- Tokens com expiração configurável
- Refresh tokens para renovação segura

### Autorização
- **Role-based access control** (CANDIDATE, EMPLOYEE)
- Validação de propriedade de recursos
- Proteção contra acesso não autorizado

### Validação
- Validação de entrada em todos os endpoints
- Sanitização de dados para prevenir ataques
- Tratamento seguro de exceções

## 🚧 Roadmap

### Versão 2.0
- [ ] Sistema de chat integrado
- [ ] Notificações push
- [ ] Algoritmo de matching com IA
- [ ] Integração com LinkedIn
- [ ] Dashboard analítico

### Versão 3.0
- [ ] Sistema de avaliações
- [ ] Integração com calendário
- [ ] Processo de entrevista online
- [ ] Métricas avançadas de RH

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👥 Equipe

- **Desenvolvedor Principal**: [Seu Nome]
- **Arquiteto de Software**: [Nome]
- **QA Engineer**: [Nome]

---

*LinkeTinder - Conectando talentos com oportunidades* 🚀
