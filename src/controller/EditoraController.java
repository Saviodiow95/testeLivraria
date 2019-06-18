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
import model.dao.EditoraDao;
import model.entidades.Editora;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author savio
 */
public final class EditoraController implements ControllerInterface {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Editora editoraDigitada;

    private Editora editoraSelecionada;

    private List<Editora> editorasTabela;

    private final EditoraDao editoraDao;

    public EditoraController() {
        editoraDao = new EditoraDao();
        editorasTabela = ObservableCollections.observableList(new ArrayList<Editora>());

        novo();
        pesquisar();

    }

    @Override
    public void novo() {
        setEditoraDigitada(new Editora());
    }

    @Override
    public void pesquisar() {
        editorasTabela.clear();
        editorasTabela.addAll(editoraDao.pesquisar(editoraDigitada));
    }

    @Override
    public void salvar() throws ValidacaoException {
        editoraDigitada.validar();
        editoraDao.salvarAtualizar(editoraDigitada);
        novo();
        pesquisar();
    }

    @Override
    public void excluir() {
        editoraDao.excluir(editoraDigitada);
        novo();
        pesquisar();
    }

    public Editora getEditoraDigitada() {
        return editoraDigitada;
    }

    public void setEditoraDigitada(Editora editoraDigitada) {

        Editora oldEditoraDigitada = this.editoraDigitada;

        this.editoraDigitada = editoraDigitada;

        propertyChangeSupport.firePropertyChange("editoraDigitada", oldEditoraDigitada, editoraDigitada);

    }

    public Editora getEditoraSelecionada() {
        return editoraSelecionada;
    }

    public void setEditoraSelecionada(Editora editoraSelecionada) {
        this.editoraSelecionada = editoraSelecionada;

        if (this.editoraSelecionada != null) {
            setEditoraDigitada(this.editoraSelecionada);
        }

    }

    public List<Editora> getEditorasTabela() {
        return editorasTabela;
    }

    public void setEditorasTabela(List<Editora> editorasTabela) {
        this.editorasTabela = editorasTabela;
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
