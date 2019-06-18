/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import util.HibernateUtil;
import java.util.List;
import model.entidades.Categoria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;




/**
 *
 * @author savio
 */
public class CategoriaDao {
    
    public void salvarAtualizar(Categoria categoria) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            if (categoria.getIdCategoria()!= null) {
                categoria = (Categoria) session.merge(categoria);
            }
            session.persist(categoria);

            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void excluir(Categoria categoria) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            categoria = (Categoria) session.merge(categoria);

            session.delete(categoria);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            session.close();
        }

    }

    public List<Categoria> pesquisar(Categoria categria) {
        List<Categoria> categorias = null;

        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {

            session = sessionFactory.openSession();

            StringBuilder sql = new StringBuilder("FROM Categoria c "
                    + "WHERE 1=1 ");

            if (categria.getIdCategoria()!= null) {

                sql.append("AND c.idCategoria = " + categria.getIdCategoria()+ " ");
            }
            if (categria.getDescricao()!= null && !categria.getDescricao().equals("")) {
                sql.append("AND c.descricao like \'%" + categria.getDescricao()+ "%\' ");
            }

            categorias = (List<Categoria>) session.createQuery(sql.toString()).list();

        } catch (HibernateException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categorias;
    }
    
    
    
}
