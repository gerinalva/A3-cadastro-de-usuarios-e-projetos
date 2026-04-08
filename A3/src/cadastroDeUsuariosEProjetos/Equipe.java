package cadastroDeUsuariosEProjetos;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private List<Projeto> projetos;

    // CONSTRUTOR
    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    // ADICIONAR MEMBRO SEM DUPLICAR
    public void adicionarMembro(Usuario usuario) {
        if (!membros.contains(usuario)) {
            membros.add(usuario);
        } else {
            System.out.println("⚠ Usuário já está na equipe.");
        }
    }

    // ADICIONAR PROJETO SEM DUPLICAR
    public void adicionarProjeto(Projeto projeto) {
        if (!projetos.contains(projeto)) {
            projetos.add(projeto);
        } else {
            System.out.println("⚠ Projeto já está vinculado à equipe.");
        }
    }

    // REMOVER MEMBRO
    public void removerMembro(Usuario usuario) {
        membros.remove(usuario);
    }

    // REMOVER PROJETO
    public void removerProjeto(Projeto projeto) {
        projetos.remove(projeto);
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Equipe: " + nome +
               " | Descrição: " + descricao +
               " | Membros: " + membros.size() +
               " | Projetos: " + projetos.size();
    }
}