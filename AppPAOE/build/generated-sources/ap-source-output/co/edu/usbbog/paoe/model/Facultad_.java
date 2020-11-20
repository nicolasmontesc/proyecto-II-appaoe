package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Programa;
import co.edu.usbbog.paoe.model.Psicologo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile SingularAttribute<Facultad, String> nomDecano;
    public static volatile ListAttribute<Facultad, Programa> programaList;
    public static volatile SingularAttribute<Facultad, String> nomFacultad;
    public static volatile SingularAttribute<Facultad, String> correo;
    public static volatile SingularAttribute<Facultad, Psicologo> psicologo;
    public static volatile SingularAttribute<Facultad, Integer> telefono;

}