package models;

public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido() {}

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "Item Pedido: | PRODUTO: " + produto + " | Quantidade: " + quantidade + " | Preço unitario: " + produto.getPreco() + " | Preço Total: " + getSubtotal() + " |";
    }
}
