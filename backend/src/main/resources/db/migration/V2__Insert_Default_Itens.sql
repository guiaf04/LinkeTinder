
--
-- Data for Name: candidato; Type: TABLE DATA; Schema: public; Owner: gui
--

INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (2, 'Mariana', 'Santos', '1985-03-22', 'mariana.santos@mail.com', '23456789012', 'Brasil', '23456-789', 'Especialista em marketing digital', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (3, 'Pedro', 'Oliveira', '1992-07-09', 'pedro.oliveira@mail.com', '34567890123', 'Brasil', '34567-890', 'Engenheiro de software focado em IA', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (4, 'Ana', 'Costa', '1995-12-30', 'ana.costa@mail.com', '45678901234', 'Brasil', '45678-901', 'Gerente de projetos com foco em inovação', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (1, 'fulano', 'detal', '1990-01-15', 'lucas.silva@mail.com', '12345678901', 'Brasil', '12345-678', 'Desenvolvedor com 10 anos de experiência', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (7, 'Mariazinha', 'Silva', '1985-01-15', 'm.a@ria.com', '12345678901', 'Brasil', '12345-678', 'Desenvolvedora_com_15_anos_de_experiência', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (8, 'Pedro', 'Pasquim', '1990-10-05', 'pe@coal.com', '12555678901', 'Brasil', '12225-678', 'Desenvolvedor com 20 anos de experiência', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (6, 'fulano', 'detal', '1985-01-15', 'jo.ao@zinho.com', '12345678901', 'Brasil', '12345-678', 'Desenvolvedor com 25 anos de experiência', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (9, 'Pedro', 'Pasquim', '1990-10-05', 'pe@coal.com', '12555678901', 'Brasil', '12225-678', 'Desenvolvedor com 20 anos de experiência', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (14, 'Testinho', 'Da Silva', '2010-10-10', 'teste@123.com', '1111111111', 'Terra dos Testes', '11111-111', 'sou um teste', 'teste123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (16, 'a', 'a', '2001-01-01', 'a@a.com', '0291821', 'a', '40028922', 'Fulanin Dev', 'abc123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (17, 'Cicrano', 'Santana', '1985-03-22', 'ci@crando.com', '11122233344', 'Brasil', '23456-789', 'Especialista em marketing digital', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (18, 'Marocs', 'Santos', '1988-03-22', 'mar.santos@mail.com', '23456789422', 'Brasil', '23456-789', 'Especialista em marketing digital', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (21, 'Teste', 'Aplicacao', '1988-03-22', 'teste@app.com', '40028922000', 'Brasil', '23456-789', 'Especialista em marketing digital', 'senha123');
INSERT INTO candidato (id, nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (22, 'Teste', 'Aplicacao', '1988-03-22', 'teste@app.com', '40028922001', 'Brasil', '23456-789', 'Especialista em testes de API 2', 'senha123');


--
-- Data for Name: candidato_competencia; Type: TABLE DATA; Schema: public; Owner: gui
--

INSERT INTO competencia (id, nome) VALUES (1, 'Java');
INSERT INTO competencia (id, nome) VALUES (2, 'Groovy');
INSERT INTO competencia (id, nome) VALUES (3, 'JavaScript');
INSERT INTO competencia (id, nome) VALUES (4, 'Angular');


INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (1, 2, 1);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (2, 2, 2);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (3, 2, 3);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (4, 2, 4);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (9, 8, 1);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (13, 18, 1);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (12, 8, 4);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (11, 8, 3);
INSERT INTO candidato_competencia (id, id_candidato, id_competencia) VALUES (10, 8, 2);

--
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: gui
--

INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (1, 'Tech Innovators', 'contato@techinnovators.com', '11222333444455', 'Brasil', '12345-678', 'Empresa focada em desenvolvimento de software de alta qualidade', 'senha123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (2, 'Marketing Masters', 'contato@marketingmasters.com', '22333444555566', 'Brasil', '23456-789', 'Agência de marketing digital', 'senha123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (3, 'AI Solutions', 'contato@aisolutions.com', '33444555666677', 'Brasil', '34567-890', 'Desenvolvimento de soluções em inteligência artificial', 'senha123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (4, 'Project Innovators', 'contato@projectinnovators.com', '44555666777788', 'Brasil', '45678-901', 'Consultoria especializada em gestão de projetos', 'senha123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (5, 'Creative Design Studio', 'contato@creativedesign.com', '55666777888899', 'Brasil', '56789-012', 'Estúdio de design gráfico e soluções criativas', 'senha123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (7, 'Testes dos Bons', 'teste@empresa.com', '11111121212', 'Terra dos Testes', '11111-111', 'Sou uma empresa teste', 'teste123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (8, 'Empresinha', 'empre@sinha.com', '01010101', 'Brasil', '40028922', 'Empresa teste', 'abc123');
INSERT INTO empresa (id, nome, email, cnpj, pais, cep, descricao, senha) VALUES (9, 'Tech Innovators', 'contato@techinnovators.com', '00112233344445', 'Brasil', '12345-678', 'Empresa focada em desenvolvimento de software de alta qualidade', 'senha123');


--
-- Data for Name: vaga; Type: TABLE DATA; Schema: public; Owner: gui
--

INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (1, 'Desenvolvedor Full Stack', 'Atuar em projetos de desenvolvimento de plataformas web', 'São Paulo', 1);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (2, 'Analista de Sistemas', 'Analisar e propor melhorias em sistemas internos', 'São Paulo', 1);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (3, 'Especialista em Marketing Digital', 'Responsável por campanhas de marketing online', 'Rio de Janeiro', 2);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (4, 'Analista de Mídias Sociais', 'Gerenciamento e planejamento de conteúdos nas redes sociais', 'Rio de Janeiro', 2);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (5, 'Engenheiro de Machine Learning', 'Desenvolver modelos de IA para diferentes clientes', 'São Paulo', 3);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (6, 'Cientista de Dados', 'Análise e interpretação de grandes volumes de dados', 'São Paulo', 3);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (7, 'Gerente de Projetos', 'Gerenciar projetos de TI em empresas inovadoras', 'Curitiba', 4);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (8, 'Coordenador de TI', 'Supervisão de equipes de tecnologia e entrega de projetos', 'Curitiba', 4);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (9, 'Designer UX/UI', 'Criar experiências de usuário e interfaces atraentes', 'São Paulo', 5);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (10, 'Diretor de Arte', 'Responsável pela direção criativa de projetos gráficos', 'São Paulo', 5);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (13, 'Desenvolvedor Full Stack', 'Atuar em projetos de desenvolvimento de plataformas web', 'São Paulo', 3);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (14, 'Desenvolvedor Full Stack', 'Atuar em projetos de desenvolvimento de plataformas web', 'São Paulo', 5);
INSERT INTO vaga (id, nome, descricao, local, id_empresa) VALUES (15, 'Analista de Sistemas', 'Analisar e propor melhorias em sistemas internos', 'São Paulo', 5);
