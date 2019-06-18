/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.List;
import model.dao.LivroDao;
import model.entidades.Livro;
import org.hibernate.HibernateException;

/**
 *
 * @author savio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        LivroDao livroDao = new LivroDao();

        List<Livro> livros = livroDao.pesquisar(new Livro());

        for (Livro livro : livros) {

            try {

                System.out.println("Titulo: " + livro);
                System.out.println("Editora: " + livro.getEditora().getNome());
            } catch (HibernateException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

}
