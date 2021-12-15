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
import models.Telefone;

/**
 *
 * @author vinic
 */
public class TelefoneController {
    private static EntityManagerFactory emf;
    
    private static EntityManager getEntityManager(){
        if(emf == null || !emf.isOpen()){
            emf = Persistence.createEntityManagerFactory("Agenda");
        }
        return emf.createEntityManager();
    }
    
    public void Cadastrar(Telefone telefone){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(telefone);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public void Atualizar(Telefone telefone){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(telefone);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public  List<Telefone> Listar(){
        EntityManager em = getEntityManager();
        List<Telefone> telefones = em.createQuery("from Telefone t").getResultList();
        em.close();
        emf.close();
        return telefones;
    }
    
    public Telefone Consultar(int id){
        EntityManager em = getEntityManager();
        Telefone telefone = em.find(Telefone.class, id);
        em.close();
        emf.close();
        return telefone;
    }
    
    public Telefone ConsultarPorNumero(String numero){
        EntityManager em = getEntityManager();
        Query consulta = em.createQuery("from Telefone t where numero = :numero");
        consulta.setParameter("numero", numero);
        
        Telefone telefone = (Telefone) consulta.getSingleResult();
        
        em.close();
        emf.close();
        return telefone;
    }
     
     public void Remover(int id){
        EntityManager em = getEntityManager();
        Telefone telefone = em.find(Telefone.class,id);
        em.getTransaction().begin();
        em.remove(telefone);
        em.getTransaction().commit();
        em.close();
        emf.close();
     }
}
