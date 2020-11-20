/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.jpa;

import co.edu.usbbog.paoe.controller.jpa.exceptions.NonexistentEntityException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.usbbog.paoe.model.Paciente;
import co.edu.usbbog.paoe.model.Programa;
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import co.edu.usbbog.paoe.model.ProgramasEstudiantePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Nicolas
 */
public class ProgramasEstudianteJpaController implements Serializable {

    public ProgramasEstudianteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramasEstudiante programasEstudiante) throws PreexistingEntityException, Exception {
        if (programasEstudiante.getProgramasEstudiantePK() == null) {
            programasEstudiante.setProgramasEstudiantePK(new ProgramasEstudiantePK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente1 = programasEstudiante.getPaciente1();
            if (paciente1 != null) {
                paciente1 = em.getReference(paciente1.getClass(), paciente1.getDocumento());
                programasEstudiante.setPaciente1(paciente1);
            }
            Programa programa1 = programasEstudiante.getPrograma1();
            if (programa1 != null) {
                programa1 = em.getReference(programa1.getClass(), programa1.getNomPrograma());
                programasEstudiante.setPrograma1(programa1);
            }
            em.persist(programasEstudiante);
            if (paciente1 != null) {
                paciente1.getProgramasEstudianteList().add(programasEstudiante);
                paciente1 = em.merge(paciente1);
            }
            if (programa1 != null) {
                programa1.getProgramasEstudianteList().add(programasEstudiante);
                programa1 = em.merge(programa1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramasEstudiante(programasEstudiante.getProgramasEstudiantePK()) != null) {
                throw new PreexistingEntityException("ProgramasEstudiante " + programasEstudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramasEstudiante programasEstudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramasEstudiante persistentProgramasEstudiante = em.find(ProgramasEstudiante.class, programasEstudiante.getProgramasEstudiantePK());
            Paciente paciente1Old = persistentProgramasEstudiante.getPaciente1();
            Paciente paciente1New = programasEstudiante.getPaciente1();
            Programa programa1Old = persistentProgramasEstudiante.getPrograma1();
            Programa programa1New = programasEstudiante.getPrograma1();
            if (paciente1New != null) {
                paciente1New = em.getReference(paciente1New.getClass(), paciente1New.getDocumento());
                programasEstudiante.setPaciente1(paciente1New);
            }
            if (programa1New != null) {
                programa1New = em.getReference(programa1New.getClass(), programa1New.getNomPrograma());
                programasEstudiante.setPrograma1(programa1New);
            }
            programasEstudiante = em.merge(programasEstudiante);
            if (paciente1Old != null && !paciente1Old.equals(paciente1New)) {
                paciente1Old.getProgramasEstudianteList().remove(programasEstudiante);
                paciente1Old = em.merge(paciente1Old);
            }
            if (paciente1New != null && !paciente1New.equals(paciente1Old)) {
                paciente1New.getProgramasEstudianteList().add(programasEstudiante);
                paciente1New = em.merge(paciente1New);
            }
            if (programa1Old != null && !programa1Old.equals(programa1New)) {
                programa1Old.getProgramasEstudianteList().remove(programasEstudiante);
                programa1Old = em.merge(programa1Old);
            }
            if (programa1New != null && !programa1New.equals(programa1Old)) {
                programa1New.getProgramasEstudianteList().add(programasEstudiante);
                programa1New = em.merge(programa1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramasEstudiantePK id = programasEstudiante.getProgramasEstudiantePK();
                if (findProgramasEstudiante(id) == null) {
                    throw new NonexistentEntityException("The programasEstudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramasEstudiantePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramasEstudiante programasEstudiante;
            try {
                programasEstudiante = em.getReference(ProgramasEstudiante.class, id);
                programasEstudiante.getProgramasEstudiantePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programasEstudiante with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente1 = programasEstudiante.getPaciente1();
            if (paciente1 != null) {
                paciente1.getProgramasEstudianteList().remove(programasEstudiante);
                paciente1 = em.merge(paciente1);
            }
            Programa programa1 = programasEstudiante.getPrograma1();
            if (programa1 != null) {
                programa1.getProgramasEstudianteList().remove(programasEstudiante);
                programa1 = em.merge(programa1);
            }
            em.remove(programasEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramasEstudiante> findProgramasEstudianteEntities() {
        return findProgramasEstudianteEntities(true, -1, -1);
    }

    public List<ProgramasEstudiante> findProgramasEstudianteEntities(int maxResults, int firstResult) {
        return findProgramasEstudianteEntities(false, maxResults, firstResult);
    }

    private List<ProgramasEstudiante> findProgramasEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramasEstudiante.class));
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

    public ProgramasEstudiante findProgramasEstudiante(ProgramasEstudiantePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramasEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramasEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramasEstudiante> rt = cq.from(ProgramasEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
