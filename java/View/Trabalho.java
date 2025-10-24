package View;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import Conexao.Conexao;

import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.TarefaController;
import Controller.UsuarioController;
import Model.Disciplina;
import Model.Nota;
import Model.Tarefa;
import Model.Usuario;
import java.util.Scanner;


public class Trabalho {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioController usuarios = new UsuarioController();
        DisciplinaController disciplinas = new DisciplinaController();
        TarefaController tarefas = new TarefaController();
        NotaController notas = new NotaController();

        while(true) {
            System.out.println("\n=== SISTEMA ESCOLAR MVC ===");
            System.out.println("1. Usuários");
            System.out.println("2. Disciplinas");
            System.out.println("3. Tarefas");
            System.out.println("4. Notas");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine(); 

            switch(op) {
                case 1: menuUsuarios(sc, usuarios); break;
                case 2: menuDisciplinas(sc, disciplinas, usuarios); break;
                case 3: menuTarefas(sc, tarefas, disciplinas, usuarios); break;
                case 4: menuNotas(sc, notas, tarefas); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuUsuarios(Scanner sc, UsuarioController usuarios) {
        while(true) {
            System.out.println("\n--- Menu Usuários ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch(op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Tipo (aluno/professor): ");
                    String tipo = sc.nextLine();
                    Usuario u = usuarios.adicionar(nome, email, tipo);
                    System.out.println("Usuário cadastrado com ID: " + u.getIdUsuario());
                    break;
                case 2:
                    for(Usuario us : usuarios.listar())
                        System.out.println(us.getIdUsuario() + " - " + us.getNome() + " (" + us.getTipo() + ")");
                    break;
                case 3:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Usuario found = usuarios.buscarPorId(id);
                    if(found != null)
                        System.out.println(found.getIdUsuario() + " - " + found.getNome() + " (" + found.getTipo() + ")");
                    else System.out.println("Usuário não encontrado!");
                    break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuDisciplinas(Scanner sc, DisciplinaController disciplinas, UsuarioController usuarios) {
        while(true) {
            System.out.println("\n--- Menu Disciplinas ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch(op) {
                case 1:
                    System.out.print("Nome da disciplina: ");
                    String nome = sc.nextLine();
                    System.out.print("ID do professor: ");
                    int idProf = sc.nextInt();
                    sc.nextLine();
                    Disciplina d = disciplinas.adicionar(nome, idProf, usuarios);
                    if(d != null) System.out.println("Disciplina cadastrada com ID: " + d.getIdDisciplina());
                    else System.out.println("Erro: Professor inválido!");
                    break;
                case 2:
                    for(Disciplina disc : disciplinas.listar())
                        System.out.println(disc.getIdDisciplina() + " - " + disc.getNome() + " (Prof ID: " + disc.getIdProfessor() + ")");
                    break;
                case 3:
                    System.out.print("ID da disciplina: ");
                    int idD = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("ID do professor: ");
                    int novoProf = sc.nextInt();
                    sc.nextLine();
                    if(disciplinas.atualizar(idD, novoNome, novoProf, usuarios))
                        System.out.println("Disciplina atualizada!");
                    else System.out.println("Erro na atualização!");
                    break;
                case 4:
                    System.out.print("ID da disciplina: ");
                    int remId = sc.nextInt();
                    sc.nextLine();
                    if(disciplinas.remover(remId)) System.out.println("Disciplina removida!");
                    else System.out.println("Não encontrada!");
                    break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuTarefas(Scanner sc, TarefaController tarefas,
                                    DisciplinaController disciplinas, UsuarioController usuarios) {
        while(true) {
            System.out.println("\n--- Menu Tarefas ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch(op) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();
                    System.out.print("Prazo: ");
                    String prazo = sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    System.out.print("ID da disciplina: ");
                    int idDisc = sc.nextInt();
                    System.out.print("ID do aluno: ");
                    int idAl = sc.nextInt();
                    sc.nextLine();
                    Tarefa t = tarefas.adicionar(titulo, desc, prazo, status, idDisc, idAl, disciplinas, usuarios);
                    if(t != null) System.out.println("Tarefa cadastrada com ID: " + t.getIdTarefa());
                    else System.out.println("Erro: disciplina ou aluno inválidos!");
                    break;
                case 2:
                    for(Tarefa tar : tarefas.listar())
                        System.out.println(tar.getIdTarefa() + " - " + tar.getTitulo() + " (Aluno ID: " + tar.getIdAluno() + ")");
                    break;
                case 3:
                    System.out.print("ID da tarefa: ");
                    int idT = sc.nextInt(); sc.nextLine();
                    System.out.print("Novo título: ");
                    String newTit = sc.nextLine();
                    System.out.print("Nova descrição: ");
                    String newDesc = sc.nextLine();
                    System.out.print("Novo prazo: ");
                    String newPrazo = sc.nextLine();
                    System.out.print("Novo status: ");
                    String newStat = sc.nextLine();
                    System.out.print("ID da disciplina: ");
                    int newDisc = sc.nextInt();
                    System.out.print("ID do aluno: ");
                    int newAl = sc.nextInt(); sc.nextLine();
                    if(tarefas.atualizar(idT, newTit, newDesc, newPrazo, newStat, newDisc, newAl, disciplinas, usuarios))
                        System.out.println("Tarefa atualizada!");
                    else System.out.println("Erro na atualização!");
                    break;
                case 4:
                    System.out.print("ID da tarefa: ");
                    int remT = sc.nextInt(); sc.nextLine();
                    if(tarefas.remover(remT)) System.out.println("Tarefa removida!");
                    else System.out.println("Não encontrada!");
                    break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuNotas(Scanner sc, NotaController notas, TarefaController tarefas) {
        while(true) {
            System.out.println("\n--- Menu Notas ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch(op) {
                case 1:
                    System.out.print("Valor da nota: ");
                    double val = sc.nextDouble();
                    System.out.print("ID da tarefa: ");
                    int idTarefa = sc.nextInt(); sc.nextLine();
                    Nota n = notas.adicionar(val, idTarefa, tarefas);
                    if(n != null) System.out.println("Nota cadastrada com ID: " + n.getIdNota());
                    else System.out.println("Erro: tarefa inválida ou já possui nota!");
                    break;
                case 2:
                    for(Nota nota : notas.listar())
                        System.out.println(nota.getIdNota() + " - " + nota.getValor() + " (Tarefa ID: " + nota.getIdTarefa() + ")");
                    break;
                case 3:
                    System.out.print("ID da nota: ");
                    int idN = sc.nextInt();
                    System.out.print("Novo valor: ");
                    double novoVal = sc.nextDouble(); sc.nextLine();
                    if(notas.atualizar(idN, novoVal)) System.out.println("Nota atualizada!");
                    else System.out.println("Erro na atualização!");
                    break;
                case 4:
                    System.out.print("ID da nota: ");
                    int remN = sc.nextInt(); sc.nextLine();
                    if(notas.remover(remN)) System.out.println("Nota removida!");
                    else System.out.println("Não encontrada!");
                    break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

}
