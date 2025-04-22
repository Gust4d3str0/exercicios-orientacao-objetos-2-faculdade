package service;

import models.Pedido;

public interface IPedidoService {
    void cadastrarPedido(Pedido p);
    void editarPedido(Pedido p);
    void excluirPedido(Pedido p);
    Pedido getPedidoById(int id);
    Pedido[] getPedidos();
}
