package view;

import javax.swing.*;
import config.Constantes;

public class ViewPrincipal {
    public static void main(String[] args) {
        String opcao;
        do {
            opcao = JOptionPane.showInputDialog(Constantes.MENU_PRINCIPAL);
            switch (opcao) {
                case "1" -> ViewProduto.menuProduto();
                case "2" -> ViewPedido.menuPedido();
            }
        } while (!"3".equals(opcao));
    }
}