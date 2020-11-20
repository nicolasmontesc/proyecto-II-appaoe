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
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import java.util.ArrayList;
import java.util.List;
import co.edu.usbbog.paoe.model.Asesoria;
import co.edu.usbbog.paoe.model.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Nicolas
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws PreexistingEntityException, Exception {
        if (paciente.getProgramasEstudianteList() == null) {
            paciente.setProgramasEstudianteList(new ArrayList<ProgramasEstudiante>());
        }
        if (paciente.getAsesoriaList() == null) {
            paciente.setAsesoriaList(new ArrayList<Asesoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProgramasEstudiante> attachedProgramasEstudianteList = new ArrayList<ProgramasEstudiante>();
            for (ProgramasEstudiante programasEstudianteListProgramasEstudianteToAttach : paciente.getProgramasEstudianteList()) {
                programasEstudianteListProgramasEstudianteToAttach = em.getReference(programasEstudianteListProgramasEstudianteToAttach.getClass(), programasEstudianteListProgramasEstudianteToAttach.getProgramasEstudiantePK());
                attachedProgramasEstudianteList.add(programasEstudianteListProgramasEstudianteToAttach);
            }
            paciente.setProgramasEstudianteList(attachedProgramasEstudianteList);
            List<Asesoria> attachedAsesoriaList = new ArrayList<Asesoria>();
            for (Asesoria asesoriaListAsesoriaToAttach : paciente.getAsesoriaList()) {
                asesoriaListAsesoriaToAttach = em.getReference(asesoriaListAsesoriaToAttach.getClass(), asesoriaListAsesoriaToAttach.getNumAsesoria());
                attachedAsesoriaList.add(asesoriaListAsesoriaToAttach);
            }
            paciente.setAsesoriaList(attachedAsesoriaList);
            em.persist(paciente);
            for (ProgramasEstudiante programasEstudianteListProgramasEstudiante : paciente.getProgramasEstudianteList()) {
                Paciente oldPaciente1OfProgramasEstudianteListProgramasEstudiante = programasEstudianteListProgramasEstudiante.getPaciente1();
                programasEstudianteListProgramasEstudiante.setPaciente1(paciente);
                programasEstudianteListProgramasEstudiante = em.merge(programasEstudianteListProgramasEstudiante);
                if (oldPaciente1OfProgramasEstudianteListProgramasEstudiante != null) {
                    oldPaciente1OfProgramasEstudianteListProgramasEstudiante.getProgramasEstudianteList().remove(programasEstudianteListProgramasEstudiante);
                    oldPaciente1OfProgramasEstudianteListProgramasEstudiante = em.merge(oldPaciente1OfProgramasEstudianteListProgramasEstudiante);
                }
            }
            for (Asesoria asesoriaListAsesoria : paciente.getAsesoriaList()) {
                Paciente oldPacienteOfAsesoriaListAsesoria = asesoriaListAsesoria.getPaciente();
                asesoriaListAsesoria.setPaciente(paciente);
                asesoriaListAsesoria = em.merge(asesoriaListAsesoria);
                if (oldPacienteOfAsesoriaListAsesoria != null) {
                    oldPacienteOfAsesoriaListAsesoria.getAsesoriaList().remove(asesoriaListAsesoria);
                    oldPacienteOfAsesoriaListAsesoria = em.merge(oldPacienteOfAsesoriaListAsesoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaciente(paciente.getDocumento()) != null) {
                throw new PreexistingEntityException("Paciente " + paciente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integer id = paciente.getDocumento();
            if (findPaciente(id) == null) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
            } else {
                Paciente persistentPaciente = em.find(Paciente.class, paciente.getDocumento());
                List<ProgramasEstudiante> programasEstudianteListOld = persistentPaciente.getProgramasEstudianteList();
                List<ProgramasEstudiante> programasEstudianteListNew = paciente.getProgramasEstudianteList();
                List<Asesoria> asesoriaListOld = persistentPaciente.getAsesoriaList();
                List<Asesoria> asesoriaListNew = paciente.getAsesoriaList();
                List<String> illegalOrphanMessages = null;
                for (ProgramasEstudiante programasEstudianteListOldProgramasEstudiante : programasEstudianteListOld) {
                    if (!programasEstudianteListNew.contains(programasEstudianteListOldProgramasEstudiante)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain ProgramasEstudiante " + programasEstudianteListOldProgramasEstudiante + " since its paciente1 field is not nullable.");
                    }
                }
                for (Asesoria asesoriaListOldAsesoria : asesoriaListOld) {
                    if (!asesoriaListNew.contains(asesoriaListOldAsesoria)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Asesoria " + asesoriaListOldAsesoria + " since its paciente field is not nullable.");
                    }
                }
                if (illegalOrphanMessages != null) {
                    throw new IllegalOrphanException(illegalOrphanMessages);
                }
                List<ProgramasEstudiante> attachedProgramasEstudianteListNew = new ArrayList<ProgramasEstudiante>();
                for (ProgramasEstudiante programasEstudianteListNewProgramasEstudianteToAttach : programasEstudianteListNew) {
                    programasEstudianteListNewProgramasEstudianteToAttach = em.getReference(programasEstudianteListNewProgramasEstudianteToAttach.getClass(), programasEstudianteListNewProgramasEstudianteToAttach.getProgramasEstudiantePK());
                    attachedProgramasEstudianteListNew.add(programasEstudianteListNewProgramasEstudianteToAttach);
                }
                programasEstudianteListNew = attachedProgramasEstudianteListNew;
                paciente.setProgramasEstudianteList(programasEstudianteListNew);
                List<Asesoria> attachedAsesoriaListNew = new ArrayList<Asesoria>();
                for (Asesoria asesoriaListNewAsesoriaToAttach : asesoriaListNew) {
                    asesoriaListNewAsesoriaToAttach = em.getReference(asesoriaListNewAsesoriaToAttach.getClass(), asesoriaListNewAsesoriaToAttach.getNumAsesoria());
                    attachedAsesoriaListNew.add(asesoriaListNewAsesoriaToAttach);
                }
                asesoriaListNew = attachedAsesoriaListNew;
                paciente.setAsesoriaList(asesoriaListNew);
                paciente = em.merge(paciente);
                for (ProgramasEstudiante programasEstudianteListNewProgramasEstudiante : programasEstudianteListNew) {
                    if (!programasEstudianteListOld.contains(programasEstudianteListNewProgramasEstudiante)) {
                        Paciente oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante = programasEstudianteListNewProgramasEstudiante.getPaciente1();
                        programasEstudianteListNewProgramasEstudiante.setPaciente1(paciente);
                        programasEstudianteListNewProgramasEstudiante = em.merge(programasEstudianteListNewProgramasEstudiante);
                        if (oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante != null && !oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante.equals(paciente)) {
                            oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante.getProgramasEstudianteList().remove(programasEstudianteListNewProgramasEstudiante);
                            oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante = em.merge(oldPaciente1OfProgramasEstudianteListNewProgramasEstudiante);
                        }
                    }
                }
                for (Asesoria asesoriaListNewAsesoria : asesoriaListNew) {
                    if (!asesoriaListOld.contains(asesoriaListNewAsesoria)) {
                        Paciente oldPacienteOfAsesoriaListNewAsesoria = asesoriaListNewAsesoria.getPaciente();
                        asesoriaListNewAsesoria.setPaciente(paciente);
                        asesoriaListNewAsesoria = em.merge(asesoriaListNewAsesoria);
                        if (oldPacienteOfAsesoriaListNewAsesoria != null && !oldPacienteOfAsesoriaListNewAsesoria.equals(paciente)) {
                            oldPacienteOfAsesoriaListNewAsesoria.getAsesoriaList().remove(asesoriaListNewAsesoria);
                            oldPacienteOfAsesoriaListNewAsesoria = em.merge(oldPacienteOfAsesoriaListNewAsesoria);
                        }
                    }
                }
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProgramasEstudiante> programasEstudianteListOrphanCheck = paciente.getProgramasEstudianteList();
            for (ProgramasEstudiante programasEstudianteListOrphanCheckProgramasEstudiante : programasEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the ProgramasEstudiante " + programasEstudianteListOrphanCheckProgramasEstudiante + " in its programasEstudianteList field has a non-nullable paciente1 field.");
            }
            List<Asesoria> asesoriaListOrphanCheck = paciente.getAsesoriaList();
            for (Asesoria asesoriaListOrphanCheckAsesoria : asesoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Asesoria " + asesoriaListOrphanCheckAsesoria + " in its asesoriaList field has a non-nullable paciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
