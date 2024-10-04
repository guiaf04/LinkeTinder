--Criação das tabelas
CREATE TABLE "candidato" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "nome" varchar(50) NOT NULL,
  "sobrenome" varchar(100) NOT NULL,
  "data_nascimento" date NOT NULL,
  "email" varchar(100) NOT NULL,
  "cpf" varchar(20) NOT NULL,
  "pais" varchar(100) NOT NULL,
  "cep" varchar(20) NOT NULL,
  "descricao_pessoal" varchar(255) NOT NULL,
  "senha" varchar(40) NOT NULL
);

CREATE TABLE "empresa" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "nome" varchar(100) NOT NULL,
  "email" varchar(100) NOT NULL,
  "cnpj" varchar(20) NOT NULL,
  "pais" varchar(100) NOT NULL,
  "cep" varchar(20) NOT NULL,
  "descricao" varchar(255) NOT NULL,
  "senha" varchar(40) NOT NULL
);

CREATE TABLE "competencia" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "nome" varchar(100) NOT NULL
);

CREATE TABLE "vaga" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "nome" varchar(255) NOT NULL,
  "descricao" varchar(255) NOT NULL,
  "local" varchar(255) NOT NULL,
  "id_empresa" int
);

CREATE TABLE "candidato_competencia" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "id_candidato" int NOT NULL,
  "id_competencia" int NOT NULL
);

CREATE TABLE "vaga_competencia" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "id_vaga" int NOT NULL,
  "id_competencia" int NOT NULL
);

CREATE TABLE "candidato_vaga" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "id_candidato" int,
  "id_vafa" int
);

--Inserção das foreign key
ALTER TABLE "vaga" ADD FOREIGN KEY ("id_empresa") REFERENCES "empresa" ("id");

ALTER TABLE "candidato_competencia" ADD FOREIGN KEY ("id_candidato") REFERENCES "candidato" ("id");

ALTER TABLE "candidato_competencia" ADD FOREIGN KEY ("id_competencia") REFERENCES "competencia" ("id");

ALTER TABLE "vaga_competencia" ADD FOREIGN KEY ("id_vaga") REFERENCES "vaga" ("id");

ALTER TABLE "vaga_competencia" ADD FOREIGN KEY ("id_competencia") REFERENCES "competencia" ("id");

ALTER TABLE "candidato_vaga" ADD FOREIGN KEY ("id_candidato") REFERENCES "candidato" ("id");

ALTER TABLE "candidato_vaga" ADD FOREIGN KEY ("id_vafa") REFERENCES "vaga" ("id");

-- Inserindo candidatos fictícios
INSERT INTO "candidato" (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES
('Lucas', 'Silva', '1990-01-15', 'lucas.silva@mail.com', '12345678901', 'Brasil', '12345-678', 'Desenvolvedor com 10 anos de experiência', 'senha123'),
('Mariana', 'Santos', '1985-03-22', 'mariana.santos@mail.com', '23456789012', 'Brasil', '23456-789', 'Especialista em marketing digital', 'senha123'),
('Pedro', 'Oliveira', '1992-07-09', 'pedro.oliveira@mail.com', '34567890123', 'Brasil', '34567-890', 'Engenheiro de software focado em IA', 'senha123'),
('Ana', 'Costa', '1995-12-30', 'ana.costa@mail.com', '45678901234', 'Brasil', '45678-901', 'Gerente de projetos com foco em inovação', 'senha123'),
('Rafael', 'Moraes', '1988-11-05', 'rafael.moraes@mail.com', '56789012345', 'Brasil', '56789-012', 'Designer gráfico e UX/UI', 'senha123');

-- Inserindo empresas fictícias
INSERT INTO "empresa" (nome, email, cnpj, pais, cep, descricao, senha) VALUES
('Tech Innovators', 'contato@techinnovators.com', '11222333444455', 'Brasil', '12345-678', 'Empresa focada em desenvolvimento de software de alta qualidade', 'senha123'),
('Marketing Masters', 'contato@marketingmasters.com', '22333444555566', 'Brasil', '23456-789', 'Agência de marketing digital', 'senha123'),
('AI Solutions', 'contato@aisolutions.com', '33444555666677', 'Brasil', '34567-890', 'Desenvolvimento de soluções em inteligência artificial', 'senha123'),
('Project Innovators', 'contato@projectinnovators.com', '44555666777788', 'Brasil', '45678-901', 'Consultoria especializada em gestão de projetos', 'senha123'),
('Creative Design Studio', 'contato@creativedesign.com', '55666777888899', 'Brasil', '56789-012', 'Estúdio de design gráfico e soluções criativas', 'senha123');

-- Inserindo vagas fictícias
-- Vagas da Tech Innovators
INSERT INTO "vaga" (nome, descricao, local, id_empresa) VALUES
('Desenvolvedor Full Stack', 'Atuar em projetos de desenvolvimento de plataformas web', 'São Paulo', 1),
('Analista de Sistemas', 'Analisar e propor melhorias em sistemas internos', 'São Paulo', 1);

-- Vagas da Marketing Masters
INSERT INTO "vaga" (nome, descricao, local, id_empresa) VALUES
('Especialista em Marketing Digital', 'Responsável por campanhas de marketing online', 'Rio de Janeiro', 2),
('Analista de Mídias Sociais', 'Gerenciamento e planejamento de conteúdos nas redes sociais', 'Rio de Janeiro', 2);

-- Vagas da AI Solutions
INSERT INTO "vaga" (nome, descricao, local, id_empresa) VALUES
('Engenheiro de Machine Learning', 'Desenvolver modelos de IA para diferentes clientes', 'São Paulo', 3),
('Cientista de Dados', 'Análise e interpretação de grandes volumes de dados', 'São Paulo', 3);

-- Vagas da Project Innovators
INSERT INTO "vaga" (nome, descricao, local, id_empresa) VALUES
('Gerente de Projetos', 'Gerenciar projetos de TI em empresas inovadoras', 'Curitiba', 4),
('Coordenador de TI', 'Supervisão de equipes de tecnologia e entrega de projetos', 'Curitiba', 4);

-- Vagas da Creative Design Studio
INSERT INTO "vaga" (nome, descricao, local, id_empresa) VALUES
('Designer UX/UI', 'Criar experiências de usuário e interfaces atraentes', 'São Paulo', 5),
('Diretor de Arte', 'Responsável pela direção criativa de projetos gráficos', 'São Paulo', 5);

