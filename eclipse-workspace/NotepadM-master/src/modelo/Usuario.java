package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {
  
  private List<Nota> notas = new ArrayList<Nota>();
  private String nome;
  private String email;
  private Senha senha;
  
  public Usuario(String nome, String email, Senha senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Senha getSenha() {
    return senha;
  }
  public void setSenha(Senha senha) {
    this.senha = senha;
  }
  
  public void adicionaNota(Nota n) {
    notas.add(n);
  }
  
  public void removeNota(Nota n) {
    notas.remove(n);
  }
  
  public List<Nota> getNotas() {
    return notas;
  }

  @Override
  public int compareTo(Usuario outro) {
    if(this.email.equals(outro.email))
      return 0;
    return 1;
  }
  
  @Override
  public boolean equals(Object outro) {
    if(this.email.equals(((Usuario)outro).email))
      return true;
    return false;
  }
  public void limpaNotas() {
    notas.clear();
  }

}
