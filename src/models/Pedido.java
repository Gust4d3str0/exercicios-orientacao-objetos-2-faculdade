package models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> itens = new ArrayList<>();
    private String descricao;

    public int getQuantidadeTotal(){
        return itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
    }

    public double getPrecoTotal(){
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================\n");
        sb.append("PEDIDO\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("DESCRIÇÃO: ").append(descricao).append("\n");
        sb.append("TOTAL DE ITENS: ").append(getQuantidadeTotal()).append("\n");
        sb.append("PREÇO TOTAL: R$ ").append(getPrecoTotal()).append("\n");
        sb.append("---------------------------\n");
        for (ItemPedido item : itens) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("===========================\n");
        return sb.toString();
    }
}

