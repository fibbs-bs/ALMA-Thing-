import java.util.Random;

public class Astronomo extends Trabajador{

    private int celestes;
    private Libreta libreta;
    private Departamento depa;

    public Astronomo(String rut, int sueldo, int celestes) {
        super(rut, sueldo);
        this.celestes=celestes;
        libreta = new Libreta();
    }

    public Departamento getDepa() {
        return depa;
    }

    public void setDepa(Departamento depa) {
        this.depa = depa;
    }

    public Libreta getLibreta() {
        return libreta;
    }

    public void setCelestes(int celestes) {
        this.celestes = celestes;
    }
    
    public void vigilarCampoMagnetico(){
        Random ran = new Random();
        int grados = ran.nextInt(91);
        if (grados >= 0 && grados<45){
            libreta.setEstado_campo_magnetico("Estable, campo magnético con colapso de "+grados+"° en eje.");
        }
        else{
            if (grados<=45 && grados>60){
                libreta.setEstado_campo_magnetico("Peligro, campo magnético con colapso de "+grados+"° en eje.");
            }
            else{
                libreta.setEstado_campo_magnetico("Emergencia\nCampo magnético inestable e inestabilizable\nError en eje de "+grados+"°.\nRecomendación: Observar posible llamarada solar");
            }
        }
    }

    public void observarEspacio(){
        Random ran = new Random();
        int observados = ran.nextInt(10000);
        libreta.setCelestes_observados(observados);
    }

    @Override
    public String toString(){
        return "Rut: "+rut+" ha observado "+celestes+" cuerpos celestes. \tSu paga es $"+sueldo;
    }

}