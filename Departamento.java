public class Departamento {
    private String calle;
    private boolean limpio;
    private String numero;
    private ListaTrabajadores lista;

    public Departamento(String street, String number){
        numero=number;
        calle=street;
        limpio=false;
        lista = new ListaTrabajadores(100);
    }

    public String getCalle() {
        return calle;
    }

    public boolean isLimpio() {
        return limpio;
    }

    public String getNumero() {
        return numero;
    }
    
    public ListaTrabajadores getListaTrabajadores(){
        return lista;
    }

    public void Limpiar(boolean limpieza){
        limpio=limpieza;
    }
    
    @Override
    public String toString(){
        return "Departamento ubicado en: "+calle+","+numero;
    }
}
