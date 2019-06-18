/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.ValidacaoException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.CategoriaDao;
import model.entidades.Categoria;
import model.entidades.Editora;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author savio
 */
public class CategoriaController implements ControllerInterface {
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private Categoria categoriaDigitada;
    
    private Categoria categoriaSelecionada;
    
    private List<Categoria> categoriasTabela;
    
    private final CategoriaDao categoriaDao;
    
    public CategoriaController() {
        this.categoriaDao = new CategoriaDao();
        
        categoriasTabela = ObservableCollections.observableList(new ArrayList<Categoria>());
        
        novo();
        pesquisar();
    }
    
    @Override
    public void novo() {
        setCategoriaDigitada(new Categoria());
    }
    
    @Override
    public void pesquisar() {
        categoriasTabela.clear();
        categoriasTabela.addAll(categoriaDao.pesquisar(categoriaDigitada));
    }
    
    @Override
    public void salvar() throws ValidacaoException {
        categoriaDigitada.validar();
        categoriaDao.salvarAtualizar(categoriaDigitada);
        novo();
        pesquisar();
        
    }
    
    @Override
    public void excluir() {
        categoriaDao.excluir(categoriaDigitada);
        novo();
        pesquisar();
        
    }
    
    public Categoria getCategoriaDigitada() {
        return categoriaDigitada;
    }
    
    public void setCategoriaDigitada(Categoria categoriaDigitada) {
        
        Categoria oldCategoriaDigitada = this.categoriaDigitada;
        
        this.categoriaDigitada = categoriaDigitada;
        
        propertyChangeSupport.firePropertyChange("categoriaDigitada", oldCategoriaDigitada, categoriaDigitada);
    }
    
    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }
    
    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
        if(categoriaSelecionada != null){
            setCategoriaDigitada(categoriaSelecionada);
        }
    }
    
    public List<Categoria> getCategoriasTabela() {
        return categoriasTabela;
    }
    
    public void setCategoriasTabela(List<Categoria> categoriasTabela) {
        this.categoriasTabela = categoriasTabela;
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener changeListener) {
        propertyChangeSupport.addPropertyChangeListener(changeListener);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener changeListener) {
        propertyChangeSupport.removePropertyChangeListener(changeListener);
    }
    
}
