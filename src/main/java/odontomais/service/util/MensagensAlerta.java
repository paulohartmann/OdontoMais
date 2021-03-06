package odontomais.service.util;

import javax.swing.*;
import java.awt.*;

/*
 * Author: phlab
 * Date: 07/02/18
 */
public class MensagensAlerta {

    //MENSAGENS INFO

    public static void msgCadastroOK(Component s){
        JOptionPane.showMessageDialog(s, "Cadastro realizado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgPagamentoOK(Component s){
        JOptionPane.showMessageDialog(s, "Pagamento realizado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgCamposObrigatorios(Component s){
        JOptionPane.showMessageDialog(s, "Confira os campos obrigatórios do formulário!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgCadastroAtualizado(Component s){
        JOptionPane.showMessageDialog(s, "Cadastro atualizado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgNadaSelecionadoTabela(Component s){
        JOptionPane.showMessageDialog(s, "Primeiro selecione uma linha na tabela!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }


    //MENSAGENS ERRO

    public static void msgErroCadastro(Component s){
        JOptionPane.showMessageDialog(s, "Erro ao cadastrar, confira os dados digitados", "Erro", JOptionPane.WARNING_MESSAGE);
    }

    public static void msgCadastroExistente(Component s){
        JOptionPane.showMessageDialog(s, "Esse cadastro já existe, confira no menu pesquisar", "Erro", JOptionPane.WARNING_MESSAGE);
    }


}
