package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Facultad;
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile SingularAttribute<Programa, String> modalidad;
    public static volatile SingularAttribute<Programa, String> nomPrograma;
    public static volatile SingularAttribute<Programa, String> correo;
    public static volatile ListAttribute<Programa, ProgramasEstudiante> programasEstudianteList;
    public static volatile SingularAttribute<Programa, Integer> telefono;
    public static volatile SingularAttribute<Programa, String> nomDirector;
    public static volatile SingularAttribute<Programa, Facultad> facultad;

}