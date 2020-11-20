/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.jpa;

import co.edu.usbbog.paoe.controller.jpa.exceptions.IllegalOrphanException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.NonexistentEntityException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.usbbog.paoe.model.Asesoria;
import java.util.ArrayList;
import java.util.List;
import co.edu.usbbog.paoe.model.Facultad;
import co.edu.usbbog.paoe.model.Psicologo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Nicolas
 */
public class PsicologoJpaController implements Serializable {

    public PsicologoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Psicologo psicologo) throws PreexistingEntityException, Exception {
        if (psicologo.getAsesoriaList() == null) {
            psicologo.setAsesoriaList(new ArrayList<Asesoria>());
        }
        if (psicologo.getFacultadList() == null) {
            psicologo.setFacultadList(new ArrayList<Facultad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asesoria> attachedAsesoriaList = new ArrayList<Asesoria>();
            for (Asesoria asesoriaListAsesoriaToAttach : psicologo.getAsesoriaList()) {
                asesoriaListAsesoriaToAttach = em.getReference(asesoriaListAsesoriaToAttach.getClass(), asesoriaListAsesoriaToAttach.getNumAsesoria());
                attachedAsesoriaList.add(asesoriaListAsesoriaToAttach);
            }
            psicologo.setAsesoriaList(attachedAsesoriaList);
            List<Facultad> attachedFacultadList = new ArrayList<Facultad>();
            for (Facultad facultadListFacultadToAttach : psicologo.getFacultadList()) {
                facultadListFacultadToAttach = em.getReference(facultadListFacultadToAttach.getClass(), facultadListFacultadToAttach.getNomFacultad());
                attachedFacultadList.add(facultadListFacultadToAttach);
            }
            psicologo.setFacultadList(attachedFacultadList);
            em.persist(psicologo);
            for (Asesoria asesoriaListAsesoria : psicologo.getAsesoriaList()) {
                Psicologo oldPsicologoOfAsesoriaListAsesoria = asesoriaListAsesoria.getPsicologo();
                asesoriaListAsesoria.setPsicologo(psicologo);
                asesoriaListAsesoria = em.merge(asesoriaListAsesoria);
                if (oldPsicologoOfAsesoriaListAsesoria != null) {
                    oldPsicologoOfAsesoriaListAsesoria.getAsesoriaList().remove(asesoriaListAsesoria);
                    oldPsicologoOfAsesoriaListAsesoria = em.merge(oldPsicologoOfAsesoriaListAsesoria);
                }
            }
            for (Facultad facultadListFacultad : psicologo.getFacultadList()) {
                Psicologo oldPsicologoOfFacultadListFacultad = facultadListFacultad.getPsicologo();
                facultadListFacultad.setPsicologo(psicologo);
                facultadListFacultad = em.merge(facultadListFacultad);
                if (oldPsicologoOfFacultadListFacultad != null) {
                    oldPsicologoOfFacultadListFacultad.getFacultadList().remove(facultadListFacultad);
                    oldPsicologoOfFacultadListFacultad = em.merge(oldPsicologoOfFacultadListFacultad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPsicologo(psicologo.getCedula()) != null) {
                throw new PreexistingEntityException("Psicologo " + psicologo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Psicologo psicologo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Psicologo persistentPsicologo = em.find(Psicologo.class, psicologo.getCedula());
            List<Asesoria> asesoriaListOld = persistentPsicologo.getAsesoriaList();
            List<Asesoria> asesoriaListNew = psicologo.getAsesoriaList();
            List<Facultad> facultadListOld = persistentPsicologo.getFacultadList();
            List<Facultad> facultadListNew = psicologo.getFacultadList();
            List<String> illegalOrphanMessages = null;
            for (Asesoria asesoriaListOldAsesoria : asesoriaListOld) {
                if (!asesoriaListNew.contains(asesoriaListOldAsesoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asesoria " + asesoriaListOldAsesoria + " since its psicologo field is not nullable.");
                }
            }
            for (Facultad facultadListOldFacultad : facultadListOld) {
                if (!facultadListNew.contains(facultadListOldFacultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facultad " + facultadListOldFacultad + " since its psicologo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asesoria> attachedAsesoriaListNew = new ArrayList<Asesoria>();
            for (Asesoria asesoriaListNewAsesoriaToAttach : asesoriaListNew) {
                asesoriaListNewAsesoriaToAttach = em.getReference(asesoriaListNewAsesoriaToAttach.getClass(), asesoriaListNewAsesoriaToAttach.getNumAsesoria());
                attachedAsesoriaListNew.add(asesoriaListNewAsesoriaToAttach);
            }
            asesoriaListNew = attachedAsesoriaListNew;
            psicologo.setAsesoriaList(asesoriaListNew);
            List<Facultad> attachedFacultadListNew = new ArrayList<Facultad>();
            for (Facultad facultadListNewFacultadToAttach : facultadListNew) {
                facultadListNewFacultadToAttach = em.getReference(facultadListNewFacultadToAttach.getClass(), facultadListNewFacultadToAttach.getNomFacultad());
                attachedFacultadListNew.add(facultadListNewFacultadToAttach);
            }
            facultadListNew = attachedFacultadListNew;
            psicologo.setFacultadList(facultadListNew);
            psicologo = em.merge(psicologo);
            for (Asesoria asesoriaListNewAsesoria : asesoriaListNew) {
                if (!asesoriaListOld.contains(asesoriaListNewAsesoria)) {
                    Psicologo oldPsicologoOfAsesoriaListNewAsesoria = asesoriaListNewAsesoria.getPsicologo();
                    asesoriaListNewAsesoria.setPsicologo(psicologo);
                    asesoriaListNewAsesoria = em.merge(asesoriaListNewAsesoria);
                    if (oldPsicologoOfAsesoriaListNewAsesoria != null && !oldPsicologoOfAsesoriaListNewAsesoria.equals(psicologo)) {
                        oldPsicologoOfAsesoriaListNewAsesoria.getAsesoriaList().remove(asesoriaListNewAsesoria);
                        oldPsicologoOfAsesoriaListNewAsesoria = em.merge(oldPsicologoOfAsesoriaListNewAsesoria);
                    }
                }
            }
            for (Facultad facultadListNewFacultad : facultadListNew) {
                if (!facultadListOld.contains(facultadListNewFacultad)) {
                    Psicologo oldPsicologoOfFacultadListNewFacultad = facultadListNewFacultad.getPsicologo();
                    facultadListNewFacultad.setPsicologo(psicologo);
                    facultadListNewFacultad = em.merge(facultadListNewFacultad);
                    if (oldPsicologoOfFacultadListNewFacultad != null && !oldPsicologoOfFacultadListNewFacultad.equals(psicologo)) {
                        oldPsicologoOfFacultadListNewFacultad.getFacultadList().remove(facultadListNewFacultad);
                        oldPsicologoOfFacultadListNewFacultad = em.merge(oldPsicologoOfFacultadListNewFacultad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = psicologo.getCedula();
                if (findPsicologo(id) == null) {
                    throw new NonexistentEntityException("The psicologo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Psicologo psicologo;
            try {
                psicologo = em.getReference(Psicologo.class, id);
                psicologo.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The psicologo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asesoria> asesoriaListOrphanCheck = psicologo.getAsesoriaList();
            for (Asesoria asesoriaListOrphanCheckAsesoria : asesoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Psicologo (" + psicologo + ") cannot be destroyed since the Asesoria " + asesoriaListOrphanCheckAsesoria + " in its asesoriaList field has a non-nullable psicologo field.");
            }
            List<Facultad> facultadListOrphanCheck = psicologo.getFacultadList();
            for (Facultad facultadListOrphanCheckFacultad : facultadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Psicologo (" + psicologo + ") cannot be destroyed since the Facultad " + facultadListOrphanCheckFacultad + " in its facultadList field has a non-nullable psicologo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(psicologo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Psicologo> findPsicologoEntities() {
        return findPsicologoEntities(true, -1, -1);
    }

    public List<Psicologo> findPsicologoEntities(int maxResults, int firstResult) {
        return findPsicologoEntities(false, maxResults, firstResult);
    }

    private List<Psicologo> findPsicologoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Psicologo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Psicologo findPsicologo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Psicologo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPsicologoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Psicologo> rt = cq.from(Psicologo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
