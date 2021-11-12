public class ListaDepartamento {
    private int cant;
    private int max;
    private Departamento [] lista;

    public ListaDepartamento(int max){
        this.max=max;
        cant =0;
        lista = new Departamento[max];
    }

    public int getCant() {
        return cant;
    }

    public boolean agregarDepartamento(Departamento Departamento){
        if (cant<max){
            lista[cant]=Departamento;
            cant++;
            return true;
        }
        return false;
    }

    public boolean eliminarDepartamento(String calle, String numero){
        int j;
        for (j = 0; j < cant; j++) {
            if (lista[j].getCalle().equals(calle) && lista[j].getNumero().equals(numero) ){
                break;
            }
        }
        if(j<cant){
            for (int i = j; i < cant-1; i++) {
                lista[i]=lista[i+1];
            }
            cant--;
            return true;
        }
        return false;
    }

    public Departamento buscarDepartamento(String calle, String numero){
        for(int i = 0;i<cant;i++){
            if (lista[i].getCalle().equals(calle) && lista[i].getNumero().equals(numero) ){
                return lista[i];
            }
        }   
        return null;
    }
    
    public Departamento getDepartamentoIndex(int index){
        return lista[index];
    }
}
