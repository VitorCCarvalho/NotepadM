package operacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Senha;
import modelo.Usuario;

public class Cadastro {

  private static String nomeArquivo = "cadastro.ser";
  private static List<Usuario> cadastrados = carregarCadastro();

  public static List<Usuario> getCadastrados() {
    if (cadastrados == null)
      cadastrados = carregarCadastro();
    return cadastrados;
  }

  private static List<Usuario> carregarCadastro() {
    List<Usuario> cadastro = new ArrayList<Usuario>();
    try {
      File f = new File(nomeArquivo);
      if (!f.exists()) 
        return cadastro;
      BufferedReader csvReader = new BufferedReader(new FileReader(nomeArquivo));
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] dados = row.split(",");
        cadastro.add(new Usuario(dados[0], dados[1], 
            new Senha(dados[2], dados[3])));
      }
      csvReader.close();

    } catch (IOException ex) {
      System.out.println("IOException lançada");
    }
    return cadastro;
  }

  public static void salvarCadastro() {
    try {
      File f = new File(nomeArquivo);
      if (!f.exists())
        f.createNewFile();
      FileWriter csvWriter = new FileWriter(nomeArquivo);
      for (Usuario u : cadastrados) {
        csvWriter.append(u.getNome() + ",");
        csvWriter.append(u.getEmail() + ",");
        csvWriter.append(u.getSenha().getHash() + ",");
        csvWriter.append(u.getSenha().getSalt() + "\n");        
      }
      csvWriter.close();
    } catch (IOException ex) {
      System.out.println("IOException lançada.");
      ex.printStackTrace();
    }
  }

  public static void cadastrarNovo(String nome, String email, String senha) {
    String salt = PasswordUtil.generateSalt(512).get();
    cadastrados.add(new Usuario(nome, email, 
        new Senha(PasswordUtil.hashPassword(senha, salt).get(), salt)));
    salvarCadastro();
  }
  
  public static void alterar(String nome, String email, String senha) {
    Usuario existente = null;
    for(Usuario u : cadastrados)
      if(u.getEmail().equals(email))
        existente = u;
    cadastrados.remove(existente);
    cadastrarNovo(nome, email, senha);
  }

  public static boolean existe(String email) {
    for(Usuario u : cadastrados)
      if(u.getEmail().equals(email))
        return true;
    return false;
  }

}
