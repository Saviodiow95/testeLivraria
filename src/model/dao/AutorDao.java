/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entidades.Autor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author savio
 */
public class AutorDao {

    public void salvarAtualizar(Autor autor) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            if (autor.getIdAutor() != null) {
                autor = (Autor) session.merge(autor);
            }
            session.persist(autor);

            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void excluir(Autor autor) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            autor = (Autor) session.merge(autor);

            session.delete(autor);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }

    }

    public List<Autor> pesquisar(Autor autor) {
        List<Autor> autores = null;

        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {

            session = sessionFactory.openSession();

            StringBuilder sql = new StringBuilder("FROM Autor a "
                    + "WHERE 1=1 ");

            if (autor.getIdAutor() != null) {

                sql.append("AND a.idAutor = " + autor.getIdAutor() + " ");
            }
            if (autor.getNome() != null && !autor.getNome().equals("")) {
                sql.append("AND a.nome like \'%" + autor.getNome() + "%\' ");
            }

            autores = (List<Autor>) session.createQuery(sql.toString()).list();

        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return autores;
    }

}
