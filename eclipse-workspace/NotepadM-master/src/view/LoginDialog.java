package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import operacao.Login;

public class LoginDialog extends JDialog {
  private static final long serialVersionUID = 1L;
  private JTextField tfUsername;
  private JPasswordField pfPassword;
  private JLabel lbUsername;
  private JLabel lbPassword;
  private JButton btnLogin;
  private JButton btnCancel;
  private JButton btnCadastrar;
  private boolean succeeded;
  private int attempts = 0;

  public LoginDialog(Frame parent) {
    super(parent, "Login", true);
    //
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    lbUsername = new JLabel("Username: ");
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    panel.add(lbUsername, cs);

    tfUsername = new JTextField(20);
    cs.gridx = 1;
    cs.gridy = 0;
    cs.gridwidth = 2;
    panel.add(tfUsername, cs);

    lbPassword = new JLabel("Password: ");
    cs.gridx = 0;
    cs.gridy = 1;
    cs.gridwidth = 1;
    panel.add(lbPassword, cs);

    pfPassword = new JPasswordField(20);
    cs.gridx = 1;
    cs.gridy = 1;
    cs.gridwidth = 2;
    panel.add(pfPassword, cs);
    panel.setBorder(new LineBorder(Color.GRAY));

    btnLogin = new JButton("Login");
    btnCancel = new JButton("Cancel");
    btnCadastrar = new JButton("Cadastrar novo...");

    JPanel bp = new JPanel();
    bp.add(btnLogin);
    bp.add(btnCancel);
    bp.add(btnCadastrar);
    
    initListeners();

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(bp, BorderLayout.PAGE_END);

    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
  }

  public void initListeners() {
    btnLogin.addActionListener(e -> {
      if (Login.authenticate(getUsername(), getPassword())) {
        JOptionPane.showMessageDialog(LoginDialog.this, "Hi " + getUsername() + "! You have successfully logged in.",
            "Login", JOptionPane.INFORMATION_MESSAGE);
        succeeded = true;
        dispose();
      } else {
        JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
            JOptionPane.ERROR_MESSAGE);
        // reset username and password
        tfUsername.setText("");
        pfPassword.setText("");
        succeeded = false;
        attempts++;
        if (attempts >= 3) {
          JOptionPane.showMessageDialog(LoginDialog.this,
              attempts + " incorrect attempts. (Waiting for " + attempts + "seconds.", "Login",
              JOptionPane.ERROR_MESSAGE);
          try {
            Thread.sleep(attempts * 1000);
          } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }
      }
    });

    btnCancel.addActionListener(e -> {
      dispose();
    });

    btnCadastrar.addActionListener(e -> {
      CadastroDialog cadastroDlg = new CadastroDialog(null);
      cadastroDlg.setVisible(true);
    });

  }

  public String getUsername() {
    return tfUsername.getText().trim();
  }

  public String getPassword() {
    return new String(pfPassword.getPassword());
  }

  public boolean isSucceeded() {
    return succeeded;
  }
}