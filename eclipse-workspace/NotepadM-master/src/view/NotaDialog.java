package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import operacao.Login;
import operacao.NotasOp;

public class NotaDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  JTextArea text;
  JButton btnSalvar;
  JButton btnSair;
  private static String nomeNota;

  public NotaDialog(Frame parent, ListaNotasDialog lnd, String conteudo, String nome) {
    super(parent, "Edição de Nota | " + nome, true);
    nomeNota = nome;
    
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    JTextArea text = new JTextArea(30, 30);
    text.setText(conteudo);

    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 20;
    panel.add(text, cs);

    setSize(500, 500);

    btnSalvar = new JButton("Salvar");
    btnSair = new JButton("Sair");

    btnSalvar.addActionListener(e -> {
      try {
        if (!nomeNota.equals("sem nome"))
          NotasOp.atualizar(Login.getLogado(), nomeNota, text.getText());
        else {
          String input = JOptionPane.showInputDialog(null, "Nome da nota:");
          NotasOp.salvar(Login.getLogado(), input, text.getText());
          NotaDialog.this.setTitle("Edição de Nota | " + input);
          nomeNota = input;
        }
      } catch (IOException e1) {
        JOptionPane.showMessageDialog(NotaDialog.this, "Erro ao tentar salvar o arquivo.", "Salvando nota",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    btnSair.addActionListener(e -> {
      dispose();
      ListaNotasDialog listaDlg = new ListaNotasDialog(null);
      listaDlg.setVisible(true);
    });

    JPanel bp = new JPanel();
    bp.add(btnSalvar);
    bp.add(btnSair);

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(bp, BorderLayout.PAGE_END);

    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
  }

}
