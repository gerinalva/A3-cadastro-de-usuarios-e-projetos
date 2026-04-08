package cadastroDeUsuariosEProjetos;

import java.time.LocalDate;

public class Projeto {

    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
    private String status; // planejado, em andamento, concluído, cancelado
    private Usuario gerente;

    // CONSTRUTOR
    public Projeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataFimPrevista, String status, Usuario gerente) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = status;
        this.gerente = gerente;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Projeto: " + nome +
               " | Status: " + status +
               " | Gerente: " + (gerente != null ? gerente.getNomeCompleto() : "Sem gerente");
    }
}