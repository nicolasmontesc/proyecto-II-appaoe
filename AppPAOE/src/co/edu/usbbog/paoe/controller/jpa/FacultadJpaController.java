/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.jpa;

import co.edu.usbbog.paoe.controller.jpa.exceptions.IllegalOrphanException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.NonexistentEntityException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.PreexistingEntityException;
import co.edu.usbbog.paoe.model.Facultad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.usbbog.paoe.model.Psicologo;
import co.edu.usbbog.paoe.model.Programa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase se encarga de las funciones básicas que se pueden realizar sobre
 * una tabla en un base de datos, en este caso son las funciones de insertar,
 * buscar, actualizar y borrar datos correspondientes a dicha tabla.
 *
 * @author Juan Nicolas, Juan Sebastián, Santiago Echeverri
 * @version 29/09/2020/1.0
 * @serial V1.0
 * @since Versión API Netbeans IDE 8.2
 */
public class FacultadJpaController implements Serializable {

    /**
     * Constructor de la clse FacultadJpaController, en este se determina la
     * unidad de persistencia de la base de datos.
     *
     * @since Versión API NetBeans IDE 8.2
     */
    public FacultadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }

    /**
     * Este objeto es el encargado de representar la unidad de persistencia de
     * la base de datos.
     */
    private EntityManagerFactory emf = null;

    /**
     * Este método se encarga de inicializar o crear el objeto de unidad de
     * persistencia de la base de datos, en este se inicializa la conexión y
     * funciones correspondientes de la base de datos y se retorna dicho
     * componete.
     *
     * @return objeto que representa la unidad de persistencia y conexión de la
     * base de datos con la aplicación, en caso contrario, no se retornará la
     * unidad de persistencia de la base de datos.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Este método se encarga de insertar un dato en la tabla Facultad del
     * modelo.
     *
     * @param facultad
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Facultad facultad) throws PreexistingEntityException, Exception {
        if (facultad.getProgramaList() == null) {
            facultad.setProgramaList(new ArrayList<Programa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Psicologo psicologo = facultad.getPsicologo();
            if (psicologo != null) {
                psicologo = em.getReference(psicologo.getClass(), psicologo.getCedula());
                facultad.setPsicologo(psicologo);
            }
            List<Programa> attachedProgramaList = new ArrayList<Programa>();
            for (Programa programaListProgramaToAttach : facultad.getProgramaList()) {
                programaListProgramaToAttach = em.getReference(programaListProgramaToAttach.getClass(), programaListProgramaToAttach.getNomPrograma());
                attachedProgramaList.add(programaListProgramaToAttach);
            }
            facultad.setProgramaList(attachedProgramaList);
            em.persist(facultad);
            if (psicologo != null) {
                psicologo.getFacultadList().add(facultad);
                psicologo = em.merge(psicologo);
            }
            for (Programa programaListPrograma : facultad.getProgramaList()) {
                Facultad oldFacultadOfProgramaListPrograma = programaListPrograma.getFacultad();
                programaListPrograma.setFacultad(facultad);
                programaListPrograma = em.merge(programaListPrograma);
                if (oldFacultadOfProgramaListPrograma != null) {
                    oldFacultadOfProgramaListPrograma.getProgramaList().remove(programaListPrograma);
                    oldFacultadOfProgramaListPrograma = em.merge(oldFacultadOfProgramaListPrograma);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultad(facultad.getNomFacultad()) != null) {
                throw new PreexistingEntityException("Facultad " + facultad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facultad facultad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad persistentFacultad = em.find(Facultad.class, facultad.getNomFacultad());
            Psicologo psicologoOld = persistentFacultad.getPsicologo();
            Psicologo psicologoNew = facultad.getPsicologo();
            List<Programa> programaListOld = persistentFacultad.getProgramaList();
            List<Programa> programaListNew = facultad.getProgramaList();
            List<String> illegalOrphanMessages = null;
            for (Programa programaListOldPrograma : programaListOld) {
                if (!programaListNew.contains(programaListOldPrograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programa " + programaListOldPrograma + " since its facultad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (psicologoNew != null) {
                psicologoNew = em.getReference(psicologoNew.getClass(), psicologoNew.getCedula());
                facultad.setPsicologo(psicologoNew);
            }
            List<Programa> attachedProgramaListNew = new ArrayList<Programa>();
            for (Programa programaListNewProgramaToAttach : programaListNew) {
                programaListNewProgramaToAttach = em.getReference(programaListNewProgramaToAttach.getClass(), programaListNewProgramaToAttach.getNomPrograma());
                attachedProgramaListNew.add(programaListNewProgramaToAttach);
            }
            programaListNew = attachedProgramaListNew;
            facultad.setProgramaList(programaListNew);
            facultad = em.merge(facultad);
            if (psicologoOld != null && !psicologoOld.equals(psicologoNew)) {
                psicologoOld.getFacultadList().remove(facultad);
                psicologoOld = em.merge(psicologoOld);
            }
            if (psicologoNew != null && !psicologoNew.equals(psicologoOld)) {
                psicologoNew.getFacultadList().add(facultad);
                psicologoNew = em.merge(psicologoNew);
            }
            for (Programa programaListNewPrograma : programaListNew) {
                if (!programaListOld.contains(programaListNewPrograma)) {
                    Facultad oldFacultadOfProgramaListNewPrograma = programaListNewPrograma.getFacultad();
                    programaListNewPrograma.setFacultad(facultad);
                    programaListNewPrograma = em.merge(programaListNewPrograma);
                    if (oldFacultadOfProgramaListNewPrograma != null && !oldFacultadOfProgramaListNewPrograma.equals(facultad)) {
                        oldFacultadOfProgramaListNewPrograma.getProgramaList().remove(programaListNewPrograma);
                        oldFacultadOfProgramaListNewPrograma = em.merge(oldFacultadOfProgramaListNewPrograma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = facultad.getNomFacultad();
                if (findFacultad(id) == null) {
                    throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.");
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
            Facultad facultad;
            try {
                facultad = em.getReference(Facultad.class, id);
                facultad.getNomFacultad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Programa> programaListOrphanCheck = facultad.getProgramaList();
            for (Programa programaListOrphanCheckPrograma : programaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facultad (" + facultad + ") cannot be destroyed since the Programa " + programaListOrphanCheckPrograma + " in its programaList field has a non-nullable facultad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Psicologo psicologo = facultad.getPsicologo();
            if (psicologo != null) {
                psicologo.getFacultadList().remove(facultad);
                psicologo = em.merge(psicologo);
            }
            em.remove(facultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facultad> findFacultadEntities() {
        return findFacultadEntities(true, -1, -1);
    }

    public List<Facultad> findFacultadEntities(int maxResults, int firstResult) {
        return findFacultadEntities(false, maxResults, firstResult);
    }

    private List<Facultad> findFacultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facultad.class));
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

    public Facultad findFacultad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facultad> rt = cq.from(Facultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
