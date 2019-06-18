/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import util.HibernateUtil;
import java.util.List;
import model.entidades.Editora;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author savio
 */
public class EditoraDao {

    public void salvarAtualizar(Editora editora) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            if (editora.getIdEditora() != null) {
                editora = (Editora) session.merge(editora);
            }
            session.persist(editora);

            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void excluir(Editora editora) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            editora = (Editora) session.merge(editora);

            session.delete(editora);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }

    }

    public List<Editora> pesquisar(Editora editora) {
        List<Editora> editoras = null;

        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {

            session = sessionFactory.openSession();

            StringBuilder sql = new StringBuilder("FROM Editora e "
                    + "WHERE 1=1 ");

            if (editora.getIdEditora() != null) {

                sql.append("AND e.idEditora = " + editora.getIdEditora() + " ");
            }
            if (editora.getNome() != null && !editora.getNome().equals("")) {
                sql.append("AND e.nome like \'%" + editora.getNome() + "%\' ");
            }

            editoras = (List<Editora>) session.createQuery(sql.toString()).list();

        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return editoras;
    }

}
