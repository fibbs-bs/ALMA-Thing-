public class ListaTrabajadores {
    private int cant;
    private int max;
    private Trabajador [] lista;

    public ListaTrabajadores(int max){
        this.max=max;
        cant =0;
        lista = new Trabajador[max];
    }

    public int getCant() {
        return cant;
    }

    public boolean agregarTrabajador(Trabajador trabajador){
        if (cant<max){
            lista[cant]=trabajador;
            cant++;
            return true;
        }
        return false;
    }

    public Trabajador buscarTrabajador(String rut){
        for(int i = 0;i<cant;i++){
            if (lista[i].getRut().equals(rut)){
                return lista[i];
            }
        }   
        return null;
    }
    
    public Trabajador getTrabajadorIndex(int index){
        return lista[index];
    }

}
