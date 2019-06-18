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
import model.dao.AutorDao;
import model.entidades.Autor;

import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author savio
 */
public class AutorController implements ControllerInterface {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Autor autorDigitado;

    private Autor autorSelecionado;

    private List<Autor> autoresTabela;

    private final AutorDao autorDao;

    public AutorController() {
        this.autorDao = new AutorDao();
        autoresTabela = ObservableCollections.observableList(new ArrayList<Autor>());
        novo();
        pesquisar();

    }

    @Override
    public void novo() {
        setAutorDigitado(new Autor());
    }

    @Override
    public void pesquisar() {
        autoresTabela.clear();
        autoresTabela.addAll(autorDao.pesquisar(autorDigitado));
    }

    @Override
    public void salvar() throws ValidacaoException {
        autorDigitado.validar();
        autorDao.salvarAtualizar(autorDigitado);
        novo();
        pesquisar();
    }

    @Override
    public void excluir() {
        autorDao.excluir(autorDigitado);
        novo();
        pesquisar();
    }

    public Autor getAutorDigitado() {
        return autorDigitado;
    }

    public void setAutorDigitado(Autor autorDigitado) {
        Autor oldAutorDigitado = this.autorDigitado;
        
        this.autorDigitado = autorDigitado;
        
        propertyChangeSupport.firePropertyChange("autorDigitado", oldAutorDigitado, autorDigitado);
        
    }

    public Autor getAutorSelecionado() {
        return autorSelecionado;
    }

    public void setAutorSelecionado(Autor autorSelecionado) {
        this.autorSelecionado = autorSelecionado;
        
        if (this.autorSelecionado != null) {
            setAutorDigitado(this.autorSelecionado);
        }
    }

    public List<Autor> getAutoresTabela() {
        return autoresTabela;
    }

    public void setAutoresTabela(List<Autor> autoresTabela) {
        this.autoresTabela = autoresTabela;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener changeListener){
        propertyChangeSupport.addPropertyChangeListener(changeListener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener changeListener){
        propertyChangeSupport.removePropertyChangeListener(changeListener);
    }
    
    
}
