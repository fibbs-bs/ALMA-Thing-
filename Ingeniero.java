public class Ingeniero extends Trabajador{

    private int experiencia;
    private Departamento depa;
    
    public Ingeniero(String rut, int sueldo,int años_experiencia) {
        super(rut, sueldo);
        this.experiencia = años_experiencia;
    }

    public Departamento getDepa() {
        return depa;
    }

    public void setDepa(Departamento depa) {
        this.depa = depa;
    }

    @Override
    public String toString(){
        return "Rut: "+rut+" \tExperiencia: "+experiencia+" años, Sueldo: "+sueldo;
    }
}
