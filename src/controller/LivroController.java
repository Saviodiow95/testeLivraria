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
import model.dao.LivroDao;
import model.entidades.Autor;
import model.entidades.Categoria;
import model.entidades.Livro;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author savio
 */
public class LivroController implements ControllerInterface {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Livro livroDigitado;
    private Livro livroSelecionado;
    private List<Livro> livrosTabela;

    private List<Categoria> listaCategorias;
    private List<Autor> listaAutores;

    private final LivroDao livroDao;
    
    
    private Categoria categoriaSelecionada;
    
    private Autor autorSelecionado;
    
    

    public LivroController() {
        this.livroDao = new LivroDao();
        livrosTabela = ObservableCollections.observableList(new ArrayList<Livro>());

        listaAutores = ObservableCollections.observableList(new ArrayList<Autor>());
        listaCategorias = ObservableCollections.observableList(new ArrayList<Categoria>());

        novo();
        pesquisar();

    }

    @Override
    public void novo() {
        setLivroDigitado(new Livro());
    }

    @Override
    public void pesquisar() {
        livrosTabela.clear();
        livrosTabela.addAll(livroDao.pesquisar(livroDigitado));

    }

    @Override
    public void salvar() throws ValidacaoException {
        livroDigitado.addAutors(listaAutores);
        livroDigitado.addCategorias(listaCategorias);
        livroDigitado.validar();
        livroDao.salvarAtualizar(livroDigitado);
        novo();
        pesquisar();

    }

    @Override
    public void excluir() {
        livroDao.excluir(livroDigitado);
        novo();
        pesquisar();

    }

    public Livro getLivroDigitado() {
        return livroDigitado;
    }

    public void setLivroDigitado(Livro livroDigitado) {

        Livro oldLivroDigitado = this.livroDigitado;

        this.livroDigitado = livroDigitado;

        propertyChangeSupport.firePropertyChange("livroDigitado", oldLivroDigitado, this.livroDigitado);
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;

        if (livroSelecionado != null) {
            setLivroDigitado(this.livroSelecionado);

        }

    }

    public List<Livro> getLivrosTabela() {
        return livrosTabela;
    }

    public void setLivrosTabela(List<Livro> livrosTabela) {
        this.livrosTabela = livrosTabela;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public void addCategoiria() {
        listaCategorias.add(categoriaSelecionada);
    }

    public void removeCategoria() {
        listaCategorias.remove(categoriaSelecionada);
    }

    public void addAutor() {
        listaAutores.add(autorSelecionado);
    }

    public void removeAutor() {
        listaAutores.remove(autorSelecionado);
    }
    
    
    
    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public Autor getAutorSelecionado() {
        return autorSelecionado;
    }

    public void setAutorSelecionado(Autor autorSelecionado) {
        this.autorSelecionado = autorSelecionado;
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
