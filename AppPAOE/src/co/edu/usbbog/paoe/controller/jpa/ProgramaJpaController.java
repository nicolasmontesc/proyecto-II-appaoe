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
import co.edu.usbbog.paoe.model.Facultad;
import co.edu.usbbog.paoe.model.Programa;
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Nicolas
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getProgramasEstudianteList() == null) {
            programa.setProgramasEstudianteList(new ArrayList<ProgramasEstudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultad = programa.getFacultad();
            if (facultad != null) {
                facultad = em.getReference(facultad.getClass(), facultad.getNomFacultad());
                programa.setFacultad(facultad);
            }
            List<ProgramasEstudiante> attachedProgramasEstudianteList = new ArrayList<ProgramasEstudiante>();
            for (ProgramasEstudiante programasEstudianteListProgramasEstudianteToAttach : programa.getProgramasEstudianteList()) {
                programasEstudianteListProgramasEstudianteToAttach = em.getReference(programasEstudianteListProgramasEstudianteToAttach.getClass(), programasEstudianteListProgramasEstudianteToAttach.getProgramasEstudiantePK());
                attachedProgramasEstudianteList.add(programasEstudianteListProgramasEstudianteToAttach);
            }
            programa.setProgramasEstudianteList(attachedProgramasEstudianteList);
            em.persist(programa);
            if (facultad != null) {
                facultad.getProgramaList().add(programa);
                facultad = em.merge(facultad);
            }
            for (ProgramasEstudiante programasEstudianteListProgramasEstudiante : programa.getProgramasEstudianteList()) {
                Programa oldPrograma1OfProgramasEstudianteListProgramasEstudiante = programasEstudianteListProgramasEstudiante.getPrograma1();
                programasEstudianteListProgramasEstudiante.setPrograma1(programa);
                programasEstudianteListProgramasEstudiante = em.merge(programasEstudianteListProgramasEstudiante);
                if (oldPrograma1OfProgramasEstudianteListProgramasEstudiante != null) {
                    oldPrograma1OfProgramasEstudianteListProgramasEstudiante.getProgramasEstudianteList().remove(programasEstudianteListProgramasEstudiante);
                    oldPrograma1OfProgramasEstudianteListProgramasEstudiante = em.merge(oldPrograma1OfProgramasEstudianteListProgramasEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getNomPrograma()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getNomPrograma());
            Facultad facultadOld = persistentPrograma.getFacultad();
            Facultad facultadNew = programa.getFacultad();
            List<ProgramasEstudiante> programasEstudianteListOld = persistentPrograma.getProgramasEstudianteList();
            List<ProgramasEstudiante> programasEstudianteListNew = programa.getProgramasEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (ProgramasEstudiante programasEstudianteListOldProgramasEstudiante : programasEstudianteListOld) {
                if (!programasEstudianteListNew.contains(programasEstudianteListOldProgramasEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramasEstudiante " + programasEstudianteListOldProgramasEstudiante + " since its programa1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facultadNew != null) {
                facultadNew = em.getReference(facultadNew.getClass(), facultadNew.getNomFacultad());
                programa.setFacultad(facultadNew);
            }
            List<ProgramasEstudiante> attachedProgramasEstudianteListNew = new ArrayList<ProgramasEstudiante>();
            for (ProgramasEstudiante programasEstudianteListNewProgramasEstudianteToAttach : programasEstudianteListNew) {
                programasEstudianteListNewProgramasEstudianteToAttach = em.getReference(programasEstudianteListNewProgramasEstudianteToAttach.getClass(), programasEstudianteListNewProgramasEstudianteToAttach.getProgramasEstudiantePK());
                attachedProgramasEstudianteListNew.add(programasEstudianteListNewProgramasEstudianteToAttach);
            }
            programasEstudianteListNew = attachedProgramasEstudianteListNew;
            programa.setProgramasEstudianteList(programasEstudianteListNew);
            programa = em.merge(programa);
            if (facultadOld != null && !facultadOld.equals(facultadNew)) {
                facultadOld.getProgramaList().remove(programa);
                facultadOld = em.merge(facultadOld);
            }
            if (facultadNew != null && !facultadNew.equals(facultadOld)) {
                facultadNew.getProgramaList().add(programa);
                facultadNew = em.merge(facultadNew);
            }
            for (ProgramasEstudiante programasEstudianteListNewProgramasEstudiante : programasEstudianteListNew) {
                if (!programasEstudianteListOld.contains(programasEstudianteListNewProgramasEstudiante)) {
                    Programa oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante = programasEstudianteListNewProgramasEstudiante.getPrograma1();
                    programasEstudianteListNewProgramasEstudiante.setPrograma1(programa);
                    programasEstudianteListNewProgramasEstudiante = em.merge(programasEstudianteListNewProgramasEstudiante);
                    if (oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante != null && !oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante.equals(programa)) {
                        oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante.getProgramasEstudianteList().remove(programasEstudianteListNewProgramasEstudiante);
                        oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante = em.merge(oldPrograma1OfProgramasEstudianteListNewProgramasEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = programa.getNomPrograma();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getNomPrograma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProgramasEstudiante> programasEstudianteListOrphanCheck = programa.getProgramasEstudianteList();
            for (ProgramasEstudiante programasEstudianteListOrphanCheckProgramasEstudiante : programasEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the ProgramasEstudiante " + programasEstudianteListOrphanCheckProgramasEstudiante + " in its programasEstudianteList field has a non-nullable programa1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facultad = programa.getFacultad();
            if (facultad != null) {
                facultad.getProgramaList().remove(programa);
                facultad = em.merge(facultad);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
