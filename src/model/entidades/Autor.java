package model.entidades;
// Generated 14/06/2019 15:29:42 by Hibernate Tools 4.3.1


import exceptions.ValidacaoException;
import java.util.HashSet;
import java.util.Set;

/**
 * Autor generated by hbm2java
 */
public class Autor  implements java.io.Serializable {


     private Integer idAutor;
     private String nome;
     private Set livros = new HashSet(0);

    public Autor() {
    }

	
    public Autor(String nome) {
        this.nome = nome;
    }
    public Autor(String nome, Set livros) {
       this.nome = nome;
       this.livros = livros;
    }
   
    public Integer getIdAutor() {
        return this.idAutor;
    }
    
    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set getLivros() {
        return this.livros;
    }
    
    public void setLivros(Set livros) {
        this.livros = livros;
    }

     @Override
    public String toString() {
        return nome;
    }

    public void validar() throws ValidacaoException {
        if (nome == null || nome.equals("")) {

            throw new ValidacaoException("Campo nome não Preenchido");
        }
    }


}


