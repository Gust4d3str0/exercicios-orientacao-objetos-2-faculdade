package view;

import config.Constantes;
import models.Produto;
import service.IProdutoService;
import service.ProdutoServiceList;

import javax.swing.*;

public class ViewProduto {
    private static IProdutoService produtoService = new ProdutoServiceList();

    public static void menuProduto() {
        String opcao;
        do {
            opcao = JOptionPane.showInputDialog(Constantes.MENU_PRODUTO);
            if (opcao == null) break;
            switch (opcao) {
                case "1" -> inserir();
                case "2" -> alterar();
                case "3" -> excluir();
                case "4" -> pesquisarPorId();
                case "5" -> listarTodos();
            }
        } while (!"6".equals(opcao));
    }

    private static void inserir() {
        try {
            String strId = JOptionPane.showInputDialog("ID:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);

            String tipo = JOptionPane.showInputDialog("Tipo:");
            if (tipo == null || tipo.isBlank()) throw new Exception("Tipo é obrigatório.");

            String descricao = JOptionPane.showInputDialog("Descrição:");
            if (descricao == null) descricao = "";

            String strPreco = JOptionPane.showInputDialog("Preço:");
            if (strPreco == null) return;
            double preco = Double.parseDouble(strPreco);
            if (preco <= 0) throw new Exception("Preço deve ser maior que zero.");

            Produto p = new Produto(id, tipo, descricao, preco);
            produtoService.cadastrarProduto(p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Use números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void alterar() {
        try {
            String strId = JOptionPane.showInputDialog("ID do produto a alterar:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);

            String tipo = JOptionPane.showInputDialog("Novo Tipo:");
            if (tipo == null || tipo.isBlank()) throw new Exception("Tipo é obrigatório.");

            String descricao = JOptionPane.showInputDialog("Nova Descrição:");
            if (descricao == null) descricao = "";

            String strPreco = JOptionPane.showInputDialog("Novo Preço:");
            if (strPreco == null) return;
            double preco = Double.parseDouble(strPreco);
            if (preco <= 0) throw new Exception("Preço deve ser maior que zero.");

            Produto p = new Produto(id, tipo, descricao, preco);
            produtoService.editarProduto(p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Use números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void excluir() {
        try {
            String strId = JOptionPane.showInputDialog("ID do produto a excluir:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);
            Produto p = new Produto();
            p.setId(id);
            produtoService.excluirProduto(p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void pesquisarPorId() {
        try {
            String strId = JOptionPane.showInputDialog("ID do produto:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);
            Produto p = produtoService.getProdutoById(id);
            if (p != null) JOptionPane.showMessageDialog(null, p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void listarTodos() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- PRODUTOS --- ");
        for (Produto p : produtoService.getProdutos()) {
            if (p != null) sb.append(p);

        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static IProdutoService getProdutoService() {
        return produtoService;
    }
}
