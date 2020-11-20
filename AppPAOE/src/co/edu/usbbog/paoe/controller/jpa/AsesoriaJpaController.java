/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.jpa;

import co.edu.usbbog.paoe.controller.jpa.exceptions.NonexistentEntityException;
import co.edu.usbbog.paoe.controller.jpa.exceptions.PreexistingEntityException;
import co.edu.usbbog.paoe.model.Asesoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.usbbog.paoe.model.Paciente;
import co.edu.usbbog.paoe.model.Psicologo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase se encarga de las funciones básicas que se pueden realizar sobre
 * una tabla en un base de datos relacional, en este caso son las funciones de
 * insertar, buscar, actualizar y borrar los datos correspondientes a dicha
 * tabla.
 *
 * @author Juan Nicolas, Juan Sebastián, Santiago Echeverri
 * @version 15/09/2020/1.0 (Cambio del proyecto)
 * @serial JPA_1.0
 * @since 15/09/2020
 */
public class AsesoriaJpaController implements Serializable {

    /**
     * Constructor de la clase AsesoriaJpaController, en este se determina la
     * unidad de persistencia de la base de datos sobre la aplicación.
     *
     * @since 15/09/2020
     */
    public AsesoriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("APPAOE");
    }

    /**
     * Este objeto es el encargado de representar la unidad de persistencia de
     * la base de datos de la aplicación APPAOE.
     *
     * @since 15/09/2020
     * @serial JPA_1.0
     * @serialField 1.0
     */
    private EntityManagerFactory emf = null;

    /**
     * Este método se encarga de entregar el objeto representativo de la unidad
     * de persistencia actual con sus respectivas funcionalidades sobre la base
     * de datos de la aplicación.
     *
     * @since 15/09/2020
     * @serialData 1.0
     * @return objeto que representa la unidad de persistencia y conexión de la
     * base de datos con la aplicación, en caso contrario, no se retornará la
     * unidad de persistencia de la base de datos, sino un dato nulo.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Este método se encarga de inserta un dato o registro en la tabla Asesoria
     * del modelo de la base de datos.
     *
     * @since 15/09/2020
     * @serialData 1.0
     * @param asesoria, representa a el objeto asesoria que se va a guardar en
     * la base de datos. Este objeto no puede ser almacenado en la base de datos
     * como nulo o ya existente.
     * @throws PreexistingEntityException, esta excepción representa el caso en
     * que la entidad asesoría que se quiere guardar en la base de datos ya
     * existe como registro, y en tal caso se ejecutará dicha excepción.
     * @throws Exception, esta excepción representa cualquier problema que se
     * presente a la hora de ejercutar este método en base a un registro que se
     * quiere almacenar en la correspondiente tabla de asesoria.
     */
    public void create(Asesoria asesoria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente = asesoria.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getDocumento());
                asesoria.setPaciente(paciente);
            }
            Psicologo psicologo = asesoria.getPsicologo();
            if (psicologo != null) {
                psicologo = em.getReference(psicologo.getClass(), psicologo.getCedula());
                asesoria.setPsicologo(psicologo);
            }
            em.persist(asesoria);
            if (paciente != null) {
                paciente.getAsesoriaList().add(asesoria);
                paciente = em.merge(paciente);
            }
            if (psicologo != null) {
                psicologo.getAsesoriaList().add(asesoria);
                psicologo = em.merge(psicologo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsesoria(asesoria.getNumAsesoria()) != null) {
                throw new PreexistingEntityException("Asesoria " + asesoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Este método se encarga de editar datos o registros de la tabla Asesoria
     * en el modelo de la base de datos.
     *
     * @param asesoria, representa a el objeto asesoria que se va a editar en la
     * base de datos. Este objeto no puede ser de tipo nulo, o no puede ser
     * inexistente en los registros de la tabla correspondiente, ya que esto
     * generaría una excepción a la hora de ejecutar dicho método.
     *
     * @throws NonexistentEntityException, esta excepción representa el caso en
     * que la entidad que se quiere editar no se encuentre en los registros de
     * la tabla asesoria, por tal motivo se difine tal excepción.
     * @throws Exception, esta excepción representa cualquier problema que se
     * puede presentar a la hora de editar un registro determinado de la tabla
     * asesoria en la base de datos de la aplicación.
     */
    public void edit(Asesoria asesoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoria persistentAsesoria = em.find(Asesoria.class, asesoria.getNumAsesoria());
            Paciente pacienteOld = persistentAsesoria.getPaciente();
            Paciente pacienteNew = asesoria.getPaciente();
            Psicologo psicologoOld = persistentAsesoria.getPsicologo();
            Psicologo psicologoNew = asesoria.getPsicologo();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getDocumento());
                asesoria.setPaciente(pacienteNew);
            }
            if (psicologoNew != null) {
                psicologoNew = em.getReference(psicologoNew.getClass(), psicologoNew.getCedula());
                asesoria.setPsicologo(psicologoNew);
            }
            asesoria = em.merge(asesoria);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getAsesoriaList().remove(asesoria);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getAsesoriaList().add(asesoria);
                pacienteNew = em.merge(pacienteNew);
            }
            if (psicologoOld != null && !psicologoOld.equals(psicologoNew)) {
                psicologoOld.getAsesoriaList().remove(asesoria);
                psicologoOld = em.merge(psicologoOld);
            }
            if (psicologoNew != null && !psicologoNew.equals(psicologoOld)) {
                psicologoNew.getAsesoriaList().add(asesoria);
                psicologoNew = em.merge(psicologoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesoria.getNumAsesoria();
                if (findAsesoria(id) == null) {
                    throw new NonexistentEntityException("The asesoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Este método se encarga de borrar un elemento insertado en la tabla
     * Asesoria de la base de datos.
     *
     * @param id
     * @throws NonexistentEntityException
     */
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoria asesoria;
            try {
                asesoria = em.getReference(Asesoria.class, id);
                asesoria.getNumAsesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesoria with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = asesoria.getPaciente();
            if (paciente != null) {
                paciente.getAsesoriaList().remove(asesoria);
                paciente = em.merge(paciente);
            }
            Psicologo psicologo = asesoria.getPsicologo();
            if (psicologo != null) {
                psicologo.getAsesoriaList().remove(asesoria);
                psicologo = em.merge(psicologo);
            }
            em.remove(asesoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Este método sirve para crear una lista con dimensiones determinada para
     * la representación de la entidades que se encuentra en la base de datos.
     *
     * @return una lista con dimensiones correspondientes a la entidades que se
     * encontraron en la tabla Asesoria de la base de datos. En caso contrario
     * retorna null si no se encontró nada. Si la lista está vacía, quiere decir
     * que la tabla se encuentra vacía.
     */
    public List<Asesoria> findAsesoriaEntities() {
        return findAsesoriaEntities(true, -1, -1);
    }

    /**
     * Este método sirve para crear una lista con dimensiones determinada para
     * la representación de la entidades que se encuentra en la base de datos.
     *
     * @param maxResults
     * @param firstResult
     * @return una lista con dimensiones correspondientes a la entidades que se
     * encontraron en la tabla Asesoria de la base de datos. En caso contrario
     * retorna null si no se encontró nada.
     */
    public List<Asesoria> findAsesoriaEntities(int maxResults, int firstResult) {
        return findAsesoriaEntities(false, maxResults, firstResult);
    }

    /**
     * Este método sirve para crear una lista con dimensiones determinada para
     * la representación de la entidades que se encuentra en la base de datos.
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return una lista con dimensiones correspondientes a la entidades que se
     * encontraron en la tabla Asesoria de la base de datos. En caso contrario
     * retorna null si no se encontró nada.
     */
    private List<Asesoria> findAsesoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asesoria.class));
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

    /**
     * Este método se encarga de encontrar un dato específica insertado en la
     * tabla Asesoria del modelo de base de datos, de igual forma lo almacena en
     * un objeto del mismo tipo, para de esa forma usarlo en operaciones
     * relacionada a la base de datos.
     *
     * @param id
     * @return un elemento específico insertado en la tabla Asesoria del modelo
     * de base de datos, en caso contrario de que no se encuentre el elemento en
     * específico, este retornará null.
     */
    public Asesoria findAsesoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesoria.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Este método sirve para obtener el número de registros que se han
     * insertado en la tabla Asesoria del modelo de bases de datos.
     *
     * @return El número de Asesorias o registros encontrados en la tabla de
     * Asesoria del modelo de bases de datos, en caso contrario, se retornará
     * null.
     */
    public int getAsesoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asesoria> rt = cq.from(Asesoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
