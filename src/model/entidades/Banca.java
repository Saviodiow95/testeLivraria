package model.entidades;
// Generated 14/06/2019 15:29:42 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Banca generated by hbm2java
 */
public class Banca  implements java.io.Serializable {


     private Integer idBanca;
     private String descricao;
     private Set bancaLocals = new HashSet(0);
     private Set emprestimos = new HashSet(0);
     private Set emprestimos_1 = new HashSet(0);
     private Set exemplars = new HashSet(0);

    public Banca() {
    }

	
    public Banca(String descricao) {
        this.descricao = descricao;
    }
    public Banca(String descricao, Set bancaLocals, Set emprestimos, Set emprestimos_1, Set exemplars) {
       this.descricao = descricao;
       this.bancaLocals = bancaLocals;
       this.emprestimos = emprestimos;
       this.emprestimos_1 = emprestimos_1;
       this.exemplars = exemplars;
    }
   
    public Integer getIdBanca() {
        return this.idBanca;
    }
    
    public void setIdBanca(Integer idBanca) {
        this.idBanca = idBanca;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set getBancaLocals() {
        return this.bancaLocals;
    }
    
    public void setBancaLocals(Set bancaLocals) {
        this.bancaLocals = bancaLocals;
    }
    public Set getEmprestimos() {
        return this.emprestimos;
    }
    
    public void setEmprestimos(Set emprestimos) {
        this.emprestimos = emprestimos;
    }
    public Set getEmprestimos_1() {
        return this.emprestimos_1;
    }
    
    public void setEmprestimos_1(Set emprestimos_1) {
        this.emprestimos_1 = emprestimos_1;
    }
    public Set getExemplars() {
        return this.exemplars;
    }
    
    public void setExemplars(Set exemplars) {
        this.exemplars = exemplars;
    }




}

