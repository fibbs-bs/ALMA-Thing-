public class Libreta {
    private String estado_campo_magnetico;
    private int celestes_observados;

    public Libreta(){}

    public void setEstado_campo_magnetico(String estado_campo_magnetico) {
        this.estado_campo_magnetico = estado_campo_magnetico;
    }

    public void setCelestes_observados(int celestes_observados) {
        this.celestes_observados = celestes_observados;
    }
    
    /*
     *Este método emite un paper. 
     */
    @Override
    public String toString(){
        String paper;
        if (estado_campo_magnetico == null || celestes_observados == 0){
            throw new NullPointerException("El paper no posee información.");
        }
        paper = "El estado del campo magnético es: "+estado_campo_magnetico+"\nLos celestes observados fueron: "+celestes_observados; 
        return paper;
    }
}
