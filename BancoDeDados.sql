CREATE DATABASE IF NOT EXISTS runner_project CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE runner_project;

CREATE TABLE IF NOT EXISTS usuarios (
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
tipo ENUM('aluno','professor') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS disciplinas (
id_disciplina INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
id_professor INT NOT NULL,
CONSTRAINT fk_disciplinas_professor FOREIGN KEY (id_professor)
REFERENCES usuarios(id_usuario)
ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS tarefas (
id_tarefa INT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
descricao TEXT,
prazo VARCHAR(100),
status VARCHAR(50),
id_disciplina INT NOT NULL,
id_aluno INT NOT NULL,
CONSTRAINT fk_tarefas_disciplina FOREIGN KEY (id_disciplina)
REFERENCES disciplinas(id_disciplina)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_tarefas_aluno FOREIGN KEY (id_aluno)
REFERENCES usuarios(id_usuario)
ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS notas (
id_nota INT AUTO_INCREMENT PRIMARY KEY,
valor DOUBLE NOT NULL,
id_tarefa INT NOT NULL UNIQUE,
CONSTRAINT fk_notas_tarefa FOREIGN KEY (id_tarefa)
REFERENCES tarefas(id_tarefa)
ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_tarefas_disciplina ON tarefas(id_disciplina);
CREATE INDEX idx_tarefas_aluno ON tarefas(id_aluno);

INSERT INTO usuarios (nome, email, tipo) VALUES
('José', 'jose@gmail.com', 'professor'),
('Ana', 'ana@gmail.com', 'aluno');


INSERT INTO disciplinas (nome, id_professor) VALUES
('Algoritmos', 1);


INSERT INTO tarefas (titulo, descricao, prazo, status, id_disciplina, id_aluno) VALUES
('Entrega 1', 'Primeira tarefa', '2025-11-01', 'pendente', 1, 2);


INSERT INTO notas (valor, id_tarefa) VALUES
(8.5, 1);
