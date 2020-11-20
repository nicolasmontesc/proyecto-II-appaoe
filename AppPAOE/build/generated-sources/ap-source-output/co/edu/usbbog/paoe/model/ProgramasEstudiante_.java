package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Paciente;
import co.edu.usbbog.paoe.model.Programa;
import co.edu.usbbog.paoe.model.ProgramasEstudiantePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(ProgramasEstudiante.class)
public class ProgramasEstudiante_ { 

    public static volatile SingularAttribute<ProgramasEstudiante, String> estado;
    public static volatile SingularAttribute<ProgramasEstudiante, Date> ingreso;
    public static volatile SingularAttribute<ProgramasEstudiante, Programa> programa1;
    public static volatile SingularAttribute<ProgramasEstudiante, ProgramasEstudiantePK> programasEstudiantePK;
    public static volatile SingularAttribute<ProgramasEstudiante, Date> egreso;
    public static volatile SingularAttribute<ProgramasEstudiante, Paciente> paciente1;

}