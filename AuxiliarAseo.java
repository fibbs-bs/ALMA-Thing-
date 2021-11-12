public class AuxiliarAseo extends Trabajador{

    public AuxiliarAseo(String rut, int sueldo) {
        super(rut, sueldo);
    }

    private Departamento ultimodepartamentoaseado;


    public Departamento getUltimodepartamentoaseado() {
        return ultimodepartamentoaseado;
    }

    /**
     * Este método también limpia el departamento ingresado
     * @param ultimodepartamentoaseado
     */
    public void setUltimodepartamentoaseado(Departamento ultimodepartamentoaseado) {
        this.ultimodepartamentoaseado = ultimodepartamentoaseado;
        ultimodepartamentoaseado.Limpiar(true);
    }

    @Override
    public String toString(){
        return "Rut: "+rut+" - Sueldo: $"+sueldo;
    }

}
