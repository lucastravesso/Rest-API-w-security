INSERT INTO CURSO (nome_curso, tipo_curso) values ('Engenharia', 'exatas'),('ADS','exatas'),('Linguas','humanas');
INSERT INTO PERFIL (id_perfil, nome) values (4, 'ROLE_MASTER'),(3, 'ROLE_OWNER'),(2, 'ROLE_SPEC'), (1, 'ROLE_DEFAULT');
INSERT INTO ALUNO (nome, email, senha, id_curso) values ('Lucas', 'lucas@awd', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 2),('Leonardo', 'leo@awd', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 2),('Pedro', 'pedro@awd', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 1),('Thiago', 'thiago@awd', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 3);
INSERT INTO ALUNO_PERFIS values (1,1);
INSERT INTO ALUNO_PERFIS values (2,2);
INSERT INTO ALUNO_PERFIS values (3,3);
INSERT INTO ALUNO_PERFIS values (4,4);
INSERT INTO AULA (nome_aula, modulo, semestre, id_curso) values ('Calculo', 1, 1, 1),('Calculo', 1, 1, 2),('POO', 1, 1, 2),('Estatistica', 1, 1, 2),('Estatistica', 1, 1, 1),('Portugues', 1, 1, 3),('Ingles', 1, 1, 3),('Espanhol', 1, 1, 3),('Estruturada', 1, 1, 2),('Estruturas', 1, 1, 1);
INSERT INTO MATRICULA (id_aluno, id_aula) values (1,2),(1,3),(1,4),(1,9),(2,2),(2,3),(2,4),(2,9),(3,1),(3,5),(3,10),(4,6),(4,7),(4,8)
