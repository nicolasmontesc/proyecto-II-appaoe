package co.edu.usbbog.paoe.model;

import co.edu.usbbog.paoe.model.Asesoria;
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-17T00:33:59")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, String> apellidos;
    public static volatile SingularAttribute<Paciente, String> codigo;
    public static volatile SingularAttribute<Paciente, String> tipoConsultante;
    public static volatile SingularAttribute<Paciente, String> financiacion;
    public static volatile ListAttribute<Paciente, ProgramasEstudiante> programasEstudianteList;
    public static volatile SingularAttribute<Paciente, Integer> documento;
    public static volatile SingularAttribute<Paciente, Integer> edad;
    public static volatile ListAttribute<Paciente, Asesoria> asesoriaList;
    public static volatile SingularAttribute<Paciente, String> nombres;
    public static volatile SingularAttribute<Paciente, String> genero;
    public static volatile SingularAttribute<Paciente, String> localidad;
    public static volatile SingularAttribute<Paciente, Integer> telefono;
    public static volatile SingularAttribute<Paciente, String> semestre;

}