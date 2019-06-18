/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.ValidacaoException;
import java.beans.PropertyChangeListener;

/**
 *
 * @author savio
 */
public interface ControllerInterface {
    
    public void novo();
    public void pesquisar();
    public void salvar() throws ValidacaoException;
    public void excluir();
    public void addPropertyChangeListener(PropertyChangeListener changeListener);
    public void removePropertyChangeListener(PropertyChangeListener changeListener);
    
}
