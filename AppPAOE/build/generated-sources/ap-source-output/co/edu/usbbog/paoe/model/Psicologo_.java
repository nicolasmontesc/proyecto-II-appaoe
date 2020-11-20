package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Asesoria;
import co.edu.usbbog.paoe.model.Facultad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(Psicologo.class)
public class Psicologo_ { 

    public static volatile SingularAttribute<Psicologo, String> apellidos;
    public static volatile SingularAttribute<Psicologo, Integer> cedula;
    public static volatile SingularAttribute<Psicologo, String> correo;
    public static volatile SingularAttribute<Psicologo, String> contrasena;
    public static volatile SingularAttribute<Psicologo, String> telefono;
    public static volatile ListAttribute<Psicologo, Facultad> facultadList;
    public static volatile SingularAttribute<Psicologo, Integer> edad;
    public static volatile ListAttribute<Psicologo, Asesoria> asesoriaList;
    public static volatile SingularAttribute<Psicologo, String> nombres;

}