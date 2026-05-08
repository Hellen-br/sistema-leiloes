package model;

public class Produto {

    private int    id;
    private String nome;
    private int    valor;
    private String status;

    // ---- Construtores ----
    public Produto() {}

    public Produto(String nome, int valor, String status) {
        this.nome   = nome;
        this.valor  = valor;
        this.status = status;
    }

    // ---- Getters e Setters ----
    public int getId()              { return id; }
    public void setId(int id)       { this.id = id; }

    public String getNome()              { return nome; }
    public void setNome(String nome)     { this.nome = nome; }

    public int getValor()               { return valor; }
    public void setValor(int valor)     { this.valor = valor; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }

    @Override
    public String toString() {
        return nome + " — R$ " + valor + " [" + status + "]";
    }
}
