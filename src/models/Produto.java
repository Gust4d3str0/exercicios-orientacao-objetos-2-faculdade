package models;

import java.util.Objects;

public class Produto {
    private int id;
    private String tipo;
    private String descricao;
    private double preco;

    public Produto(int id, String tipo, String descricao, double preco) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }
    public Produto() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id && Double.compare(preco, produto.preco) == 0 && Objects.equals(tipo, produto.tipo) && Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, descricao, preco);
    }

    @Override
    public String toString() {
        return "\nProduto: " + " | ID: " + id + " | TIPO: " + tipo + " | DESCRIÇÃO: " + descricao + " | PREÇO: " + preco + " |\n";
    }
}
