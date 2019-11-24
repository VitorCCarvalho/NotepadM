package operacao;

import modelo.Usuario;

public class Login {
  
  private static Usuario logado;
  
  public static Usuario getLogado() {
    return logado;
  }

  public static boolean authenticate(String user, 
      String password) {
    for(Usuario u : Cadastro.getCadastrados()) {
      if(u.getEmail().equals(user)) {
        String salt = u.getSenha().getSalt();
        if(PasswordUtil.verifyPassword(password, u.getSenha().getHash(), salt)) {
          logado = u;
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

}
