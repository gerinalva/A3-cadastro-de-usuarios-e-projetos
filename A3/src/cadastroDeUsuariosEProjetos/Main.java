package cadastroDeUsuariosEProjetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Usuario> usuarios = new ArrayList<>();
    static List<Projeto> projetos = new ArrayList<>();
    static List<Equipe> equipes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar projeto");
            System.out.println("3 - Cadastrar equipe");
            System.out.println("4 - Listar usuários");
            System.out.println("5 - Listar projetos");
            System.out.println("6 - Listar equipes");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario(sc);
                    break;
                case 2:
                    cadastrarProjeto(sc);
                    break;
                case 3:
                    cadastrarEquipe(sc);
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    listarProjetos();
                    break;
                case 6:
                    listarEquipes();
                    break;
                default:
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }

    // ================= CADASTRAR USUARIO =================
    public static void cadastrarUsuario(Scanner sc) {

        System.out.println("Nome:");
        String nome = sc.nextLine();

        System.out.println("CPF:");
        String cpf = sc.nextLine();

        System.out.println("Email:");
        String email = sc.nextLine();

        System.out.println("Cargo:");
        String cargo = sc.nextLine();

        System.out.println("Login:");
        String login = sc.nextLine();

        System.out.println("Senha:");
        String senha = sc.nextLine();

        System.out.println("Perfil (administrador/gerente/colaborador):");
        String perfil = sc.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email, cargo, login, senha, perfil);
        usuarios.add(usuario);

        System.out.println("✅ Usuário cadastrado com sucesso!");
    }

    // ================= CADASTRAR PROJETO =================
    public static void cadastrarProjeto(Scanner sc) {

        if (usuarios.isEmpty()) {
            System.out.println("⚠ Cadastre pelo menos um usuário antes.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Nome do projeto:");
        String nome = sc.nextLine();

        System.out.println("Descrição:");
        String descricao = sc.nextLine();

        System.out.println("Data início (DD-MM-AAAA):");
        LocalDate inicio = LocalDate.parse(sc.nextLine(), formatter);

        System.out.println("Data fim prevista (DD-MM-AAAA):");
        LocalDate fim = LocalDate.parse(sc.nextLine(), formatter);

        System.out.println("Status (planejado/em andamento/concluído/cancelado):");
        String status = sc.nextLine();

        List<Usuario> gerentes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getPerfil().equalsIgnoreCase("gerente")) {
                gerentes.add(u);
            }
        }

        if (gerentes.isEmpty()) {
            System.out.println("⚠ Nenhum gerente cadastrado.");
            return;
        }

        System.out.println("\nEscolha o gerente:");
        for (int i = 0; i < gerentes.size(); i++) {
            System.out.println(i + " - " + gerentes.get(i));
        }

        int escolha = sc.nextInt();
        sc.nextLine();

        Usuario gerente = gerentes.get(escolha);

        Projeto projeto = new Projeto(nome, descricao, inicio, fim, status, gerente);
        projetos.add(projeto);

        System.out.println("✅ Projeto cadastrado com sucesso!");
    }

    // ================= CADASTRAR EQUIPE =================
    public static void cadastrarEquipe(Scanner sc) {

        if (usuarios.isEmpty()) {
            System.out.println("⚠ Cadastre usuários antes de criar equipe.");
            return;
        }

        System.out.println("Nome da equipe:");
        String nome = sc.nextLine();

        System.out.println("Descrição da equipe:");
        String descricao = sc.nextLine();

        Equipe equipe = new Equipe(nome, descricao);

        String continuar;

        // ================= ADICIONAR MEMBROS =================
        do {
            System.out.println("\nEscolha um membro para adicionar:");

            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(i + " - " + usuarios.get(i));
            }

            int escolha = sc.nextInt();
            sc.nextLine();

            if (escolha >= 0 && escolha < usuarios.size()) {
                equipe.adicionarMembro(usuarios.get(escolha));
                System.out.println("✅ Membro adicionado.");
            } else {
                System.out.println("⚠ Índice inválido.");
            }

            System.out.println("Deseja adicionar outro membro? (s/n)");
            continuar = sc.nextLine();

        } while (continuar.equalsIgnoreCase("s"));

        // ================= ADICIONAR PROJETOS =================
        if (!projetos.isEmpty()) {

            do {
                System.out.println("\nEscolha um projeto para associar:");

                for (int i = 0; i < projetos.size(); i++) {
                    System.out.println(i + " - " + projetos.get(i));
                }

                int escolhaProjeto = sc.nextInt();
                sc.nextLine();

                if (escolhaProjeto >= 0 && escolhaProjeto < projetos.size()) {
                    equipe.adicionarProjeto(projetos.get(escolhaProjeto));
                    System.out.println("✅ Projeto adicionado.");
                } else {
                    System.out.println("⚠ Índice inválido.");
                }

                System.out.println("Deseja adicionar outro projeto? (s/n)");
                continuar = sc.nextLine();

            } while (continuar.equalsIgnoreCase("s"));
        }

        equipes.add(equipe);

        System.out.println("✅ Equipe cadastrada com sucesso!");
    }

    // ================= LISTAR USUARIOS =================
    public static void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS ---");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // ================= LISTAR PROJETOS =================
    public static void listarProjetos() {
        System.out.println("\n--- PROJETOS ---");

        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Projeto p : projetos) {
            System.out.println("Projeto: " + p.getNome()
                    + " | Status: " + p.getStatus()
                    + " | Início: " + p.getDataInicio().format(formatter)
                    + " | Fim: " + p.getDataFimPrevista().format(formatter)
                    + " | Gerente: " + p.getGerente().getNomeCompleto());
        }
    }

    // ================= LISTAR EQUIPES =================
    public static void listarEquipes() {
        System.out.println("\n--- EQUIPES ---");

        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
            return;
        }

        for (Equipe e : equipes) {
            System.out.println(e);
        }
    }
}