/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;



import util.HibernateUtil;
import java.util.List;
import model.entidades.Livro;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author savio
 */
public class LivroDao {
     public void salvarAtualizar(Livro livro) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            if (livro.getIdLivro()!= null) {
                livro = (Livro) session.merge(livro);
            }
            session.persist(livro);

            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void excluir(Livro livro) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            livro = (Livro) session.merge(livro);

            session.delete(livro);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }

    }

    public List<Livro> pesquisar(Livro livro) {
        List<Livro> livros = null;

        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {

            session = sessionFactory.openSession();

            StringBuilder sql = new StringBuilder("FROM Livro l "
                    + "WHERE 1=1 ");

            if (livro.getIdLivro()!= null) {

                sql.append("AND e.idLivro = " + livro.getIdLivro()+ " ");
            }
            if (livro.getTitulo()!= null && !livro.getTitulo().equals("")) {
                sql.append("AND l.titulo like \'%" + livro.getTitulo()+ "%\' ");
            }

            livros = (List<Livro>) session.createQuery(sql.toString()).list();

        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return livros;
    }
    
    
    
    
    
    
    
    
    
}
