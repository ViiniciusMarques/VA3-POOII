/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import models.Contato;

/**
 *
 * @author vinic
 */
public class ContatoController {
    private static EntityManagerFactory emf;
    
    private static EntityManager getEntityManager(){
        if(emf == null || !emf.isOpen()){
            emf = Persistence.createEntityManagerFactory("Agenda");
        }
        return emf.createEntityManager();
    }
    
    public void Cadastrar(Contato contato){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(contato);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public void Atualizar(Contato contato){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(contato);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public  List<Contato> Listar(){
        EntityManager em = getEntityManager();
        List<Contato> contatos = em.createQuery("from Contato c").getResultList();
        em.close();
        emf.close();
        return contatos;
    }
    
    public  List<Contato> ListarPorNome(String nome){
        EntityManager em = getEntityManager();
        Query consulta = em.createQuery("from Contato c where nome like :nome");
        consulta.setParameter("nome", "%"+nome+"%");
        
        List<Contato> contatos = consulta.getResultList();
        
        em.close();
        emf.close();
        return contatos;
    }
    
    public Contato Consultar(int id){
        EntityManager em = getEntityManager();
        Contato contato = em.find(Contato.class, id);
        em.close();
        emf.close();
        return contato;
    }
    
    public Contato ConsultarPorNome(String nome){
        EntityManager em = getEntityManager();
        Query consulta = em.createQuery("from Contato c where nome = :nome");
        consulta.setParameter("nome", nome);
        
        Contato contato = (Contato) consulta.getSingleResult();
        
        em.close();
        emf.close();
        return contato;
    }
     
     public void Remover(int id){
        EntityManager em = getEntityManager();
        Contato contato = em.find(Contato.class,id);
        em.getTransaction().begin();
        em.remove(contato);
        em.getTransaction().commit();
        em.close();
        emf.close();
     }
}
