package service;

import models.Produto;

public interface IProdutoService {
    void cadastrarProduto(Produto p);
    void editarProduto(Produto p);
    void excluirProduto(Produto p);
    Produto getProdutoById(int id);
    Produto[] getProdutos();
}
