package view;

import javax.swing.*;
import models.*;
import service.*;
import config.Constantes;
import java.util.*;

public class ViewPedido {
    private static IPedidoService pedidoService = new PedidoServiceList();

    public static void menuPedido() {
        String opcao;
        do {
            opcao = JOptionPane.showInputDialog(Constantes.MENU_PEDIDO);
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
        Pedido pedido = criarPedido();
        if (pedido != null) pedidoService.cadastrarPedido(pedido);
    }

    private static void alterar() {
        try {
            String strId = JOptionPane.showInputDialog("ID do pedido a alterar:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);
            Pedido pedido = criarPedido();
            if (pedido != null) {
                pedido.setId(id);
                pedidoService.editarPedido(pedido);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void excluir() {
        try {
            String strId = JOptionPane.showInputDialog("ID do pedido a excluir:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);
            Pedido p = new Pedido();
            p.setId(id);
            pedidoService.excluirPedido(p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void pesquisarPorId() {
        try {
            String strId = JOptionPane.showInputDialog("ID do pedido:");
            if (strId == null) return;
            int id = Integer.parseInt(strId);
            Pedido p = pedidoService.getPedidoById(id);
            if (p != null) JOptionPane.showMessageDialog(null, p);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    private static void listarTodos() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- PEDIDOS ---\n");
        for (Pedido p : pedidoService.getPedidos()) {
            if (p != null) sb.append(p);
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static Pedido criarPedido() {
        try {
            String strId = JOptionPane.showInputDialog("ID do Pedido:");
            if (strId == null) return null;
            int id = Integer.parseInt(strId);

            String descricao = JOptionPane.showInputDialog("Descrição do Pedido:");
            if (descricao == null) descricao = "";

            List<ItemPedido> itens = new ArrayList<>();
            Produto[] produtos = ViewProduto.getProdutoService().getProdutos();

            while (true) {
                StringBuilder sb = new StringBuilder();
                sb.append("Escolha um produto:\n");
                for (Produto p : produtos) {
                    if (p != null) sb.append(p.getId()).append(" - ").append(p.getDescricao()).append(" (R$ ").append(p.getPreco()).append(")\n");
                }

                String inputId = JOptionPane.showInputDialog(sb + "Digite o ID do produto ou -1 para finalizar:");
                if (inputId == null || inputId.equals("-1")) break;

                try {
                    int idProduto = Integer.parseInt(inputId);
                    Produto produto = ViewProduto.getProdutoService().getProdutoById(idProduto);
                    if (produto == null) continue;

                    String qtdStr = JOptionPane.showInputDialog("Quantidade:");
                    if (qtdStr == null) continue;
                    int quantidade = Integer.parseInt(qtdStr);
                    if (quantidade <= 0) throw new Exception("Quantidade deve ser maior que zero.");

                    itens.add(new ItemPedido(produto, quantidade));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            Pedido pedido = new Pedido();
            pedido.setId(id);
            pedido.setDescricao(descricao);
            pedido.setItens(itens);
            return pedido;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido para pedido.");
            return null;
        }
    }
}
