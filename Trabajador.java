public class Trabajador{
    protected String rut;
    protected int sueldo;

    public Trabajador(String rut, int sueldo){
        this.sueldo=sueldo;
        this.rut=rut;
    }

    public String getRut() {
        return rut;
    }

    public int getSueldo() {
        return sueldo;
    }

    @Override
    public String toString(){
        return "Rut: "+rut+", sueldo: "+sueldo;
    }
}