package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Paciente;
import co.edu.usbbog.paoe.model.Psicologo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(Asesoria.class)
public class Asesoria_ { 

    public static volatile SingularAttribute<Asesoria, String> remitidoPor;
    public static volatile SingularAttribute<Asesoria, Date> fechaHora;
    public static volatile SingularAttribute<Asesoria, String> culminacion;
    public static volatile SingularAttribute<Asesoria, String> accionesRealizadas;
    public static volatile SingularAttribute<Asesoria, String> remitidoA;
    public static volatile SingularAttribute<Asesoria, String> problematicaRefTrab;
    public static volatile SingularAttribute<Asesoria, String> accesoServicio;
    public static volatile SingularAttribute<Asesoria, Integer> numAsesoria;
    public static volatile SingularAttribute<Asesoria, String> asistencia;
    public static volatile SingularAttribute<Asesoria, Paciente> paciente;
    public static volatile SingularAttribute<Asesoria, String> observaciones;
    public static volatile SingularAttribute<Asesoria, String> tipoAcompanamiento;
    public static volatile SingularAttribute<Asesoria, Psicologo> psicologo;

}