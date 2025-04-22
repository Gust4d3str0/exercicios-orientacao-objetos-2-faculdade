package service;

import models.Pedido;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoServiceList implements IPedidoService{
    private List<Pedido> lista = new ArrayList<>();

    @Override
    public void cadastrarPedido(Pedido p) {
        if (existeId(p.getId())) {
            JOptionPane.showMessageDialog(null, "ID já cadastrado. Escolha outro ID.");
        } else {
            lista.add(p);
        }
    }

    @Override
    public void editarPedido(Pedido p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Pedido com ID não encontrado para edição.");
    }

    @Override
    public void excluirPedido(Pedido p) {
        lista.removeIf(ped -> ped.getId() == p.getId());
    }

    @Override
    public Pedido getPedidoById(int id) {
        for (Pedido p : lista) {
            if (p.getId() == id) return p;
        }
        JOptionPane.showMessageDialog(null, "Pedido com ID " + id + " não encontrado.");
        return null;
    }

    @Override
    public Pedido[] getPedidos() {
        return lista.toArray(new Pedido[0]);
    }

    private boolean existeId(int id) {
        return lista.stream().anyMatch(p -> p.getId() == id);
    }
}

