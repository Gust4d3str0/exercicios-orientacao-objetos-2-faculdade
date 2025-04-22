package service;

import models.Produto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoServiceList implements IProdutoService{
    private List<Produto> lista = new ArrayList<>();

    @Override
    public void cadastrarProduto(Produto p) {
        if (existeId(p.getId())) {
            JOptionPane.showMessageDialog(null, "ID já cadastrado. Escolha outro ID.");
        } else {
            lista.add(p);
        }
    }

    @Override
    public void editarProduto(Produto p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Produto com ID não encontrado para edição.");
    }

    @Override
    public void excluirProduto(Produto p) {
        lista.removeIf(produto -> produto.getId() == p.getId());
    }

    @Override
    public Produto getProdutoById(int id) {
        for (Produto p : lista) {
            if (p.getId() == id) return p;
        }
        JOptionPane.showMessageDialog(null, "Produto com ID " + id + " não encontrado.");
        return null;
    }

    @Override
    public Produto[] getProdutos() {
        return lista.toArray(new Produto[0]);
    }

    private boolean existeId(int id) {
        return lista.stream().anyMatch(p -> p.getId() == id);
    }
}
