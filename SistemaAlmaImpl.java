
public class SistemaAlmaImpl implements SistemaAlma {

    ListaTrabajadores trabajadores;//Cambiar en el diagrama de clases
    ListaDepartamento departamentos;//Cambiar en el diagrama de clases

    public SistemaAlmaImpl(){
        trabajadores= new ListaTrabajadores(3000);
        departamentos= new ListaDepartamento(20);
    }
    
    @Override
    public boolean ingresarAstronomo(String rut, int sueldo, int celestes) {
        Trabajador astronomito = new Astronomo(rut, sueldo, celestes);
        return trabajadores.agregarTrabajador(astronomito);
    }

    @Override
    public boolean ingresarAuxiliar(String rut, int sueldo) {
        Trabajador auxiliar = new AuxiliarAseo(rut, sueldo);
        return trabajadores.agregarTrabajador(auxiliar);
    }

    @Override
    public boolean ingresarIngeniero(String rut, int sueldo, int años_experiencia) {
        Trabajador ingeniero = new Ingeniero(rut, sueldo, años_experiencia);
        return trabajadores.agregarTrabajador(ingeniero);
    }

    @Override
    public boolean ingresarDepartamento(String calle, String numero) {
        Departamento departamentoExistente = departamentos.buscarDepartamento(calle, numero);
        if (departamentoExistente==null){
            Departamento nuevoDepartamento = new Departamento(calle, numero);
            return departamentos.agregarDepartamento(nuevoDepartamento);
        }
        else{
            return true;
        }
    }

    @Override
    public boolean asociarTrabajadorDepartamento(String calle, String numero, String rut) {
        Departamento departamentoExistente = departamentos.buscarDepartamento(calle, numero);
        Trabajador trabajadorExistente = trabajadores.buscarTrabajador(rut);
        if (departamentoExistente==null || trabajadorExistente==null){
            throw new NullPointerException("El departamento o el trabajador no existen.");
        }
        else{
            if (!departamentoExistente.getListaTrabajadores().agregarTrabajador(trabajadorExistente)){
                throw new NullPointerException("El departamento está lleno.");
            }
            if (trabajadorExistente instanceof Astronomo){
                Astronomo astronomoExistente = (Astronomo) trabajadorExistente;
                astronomoExistente.setDepa(departamentoExistente);
            }
            else{
                if (trabajadorExistente instanceof Ingeniero){
                    Ingeniero ingenieroExistente = (Ingeniero) trabajadorExistente;
                    ingenieroExistente.setDepa(departamentoExistente);
                }
            }
        }
        return false;
    }

    @Override
    public boolean limpiarDepartamento(String direccion, String numero, String rut) {
        Departamento departamentoExistente = departamentos.buscarDepartamento(direccion, numero);
        Trabajador trabajadorExistente = trabajadores.buscarTrabajador(rut);
        if (trabajadorExistente==null){
            throw new NullPointerException("\nDatos del trabajador ingresados no coinciden con los del sistema. \n");
        }
        if (departamentoExistente==null){
            throw new NullPointerException("\nDatos del departamento ingresados no coinciden con los del sistema. \n");
        }
        else{
            if (trabajadorExistente instanceof AuxiliarAseo){
                AuxiliarAseo auxiliarAseoExistente = (AuxiliarAseo) trabajadorExistente;
                auxiliarAseoExistente.setUltimodepartamentoaseado(departamentoExistente);
                departamentoExistente.Limpiar(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public String obtenerDatosDePaper(String rut) {
        Trabajador trabajadorExistente = trabajadores.buscarTrabajador(rut);
        if (trabajadorExistente!=null){
            if (trabajadorExistente instanceof Astronomo){
                Astronomo astronomoExistente = (Astronomo) trabajadorExistente;
                try{
                    String paper = astronomoExistente.getLibreta().toString();
                    return paper;
                }
                catch (NullPointerException ex){
                    throw new NullPointerException(ex.getMessage());
                }
            }
            throw new NullPointerException("El rut está asociado a un "+trabajadorExistente.getClass());
        }
        throw new NullPointerException("El rut no está asociado a ningún trabajador.");
    }

    @Override
    public String obtenerDatosAstronomosEnDepartamentosLimpios() {
        String salida = "\n";
        int cant =0;
        for (int i = 0; i < trabajadores.getCant(); i++) {
            Trabajador trabajadorObservado = trabajadores.getTrabajadorIndex(i);
            if (trabajadorObservado instanceof Astronomo){
                Astronomo astronomoObservado = (Astronomo) trabajadorObservado;
                if (astronomoObservado.getDepa()!=null){
                    if (astronomoObservado.getDepa().isLimpio()){
                        salida+=astronomoObservado.toString()+"\n";
                        salida+=astronomoObservado.getDepa().toString()+"\n";
                        cant++;
                    }
                }
            }
        }
        if (cant==0){
            throw new NullPointerException("No hay astrónomos en departamentos limpios. ");
        }
        else{
            return salida;
        }
    }

    @Override
    public boolean eliminarDepartamentosSucios() {
        int index = 0;
        boolean salida = true;
        while (index<departamentos.getCant()){
            if (!departamentos.getDepartamentoIndex(index).isLimpio()){
                Departamento depaAEliminar = departamentos.getDepartamentoIndex(index);
                for (int i = 0; i < depaAEliminar.getListaTrabajadores().getCant(); i++) {
                    Trabajador t = depaAEliminar.getListaTrabajadores().getTrabajadorIndex(i);
                    if (t instanceof Astronomo){
                        Astronomo as = (Astronomo) t;
                        as.setDepa(null);
                    }
                    else{
                        Ingeniero ing = (Ingeniero) t;
                        ing.setDepa(null);
                    }
                }
                salida = departamentos.eliminarDepartamento(depaAEliminar.getCalle(), depaAEliminar.getNumero());
                index--;
                if (!salida){
                    throw new NullPointerException("No se pudo eliminar el departamento ubicado en "+depaAEliminar.getCalle()+","+depaAEliminar.getNumero());
                }
            }
            index++;
        }
        return salida;
    }

    @Override
    public String mantenerBaseDatos(String rut) {
        Trabajador trabajador = trabajadores.buscarTrabajador(rut);
        if (trabajador==null){
            throw new NullPointerException("El trabajador con el rut "+rut+" no existe en el sistema");
        }
        else{
            if (trabajador instanceof Ingeniero){
                return "El ingeniero "+rut+" ha mantenido la base de datos.";
            }
            else{
                throw new NullPointerException("El rut ingresado no corresponde a un Ingeniero, corresponde a un "+trabajador.getClass());
            }
        }
    }

    @Override
    public boolean registrarNuevaObservacionAstronomica(String rut) {
    Trabajador trabajador = trabajadores.buscarTrabajador(rut);
        if (trabajador == null){
            throw new NullPointerException("El trabajador con el rut "+rut+" no existe en el sistema");
        }
        else{
            if (trabajador instanceof Astronomo){
                Astronomo astronomo = (Astronomo) trabajador;
                astronomo.vigilarCampoMagnetico();
                astronomo.observarEspacio();
                return true;
            }
            return false;
        }
    }

    @Override
    public String obtenerDatosGeneralesAlma() {
        String text = "\u001B[1m \t\t\t-Datos Generales ALMA-\u001B[0m";
        text += "\nALMA posee \u001B[1m"+departamentos.getCant()+" departamentos.\u001B[0m";
        int cantAstronomos=0;
        int cantAuxiliares=0;
        int cantIngenieros=0;
        int sueldoPromAstronomos=0;
        int sueldoPromAuxiliares=0;
        int sueldoPromIngenieros=0;
        for (int i = 0; i < trabajadores.getCant(); i++) {
            Trabajador trabajador = trabajadores.getTrabajadorIndex(i);
            if ( trabajador instanceof Astronomo){
                cantAstronomos++;
                sueldoPromAstronomos+=trabajadores.getTrabajadorIndex(i).getSueldo();
            }
            else{
                if(trabajador instanceof AuxiliarAseo){
                    cantAuxiliares++;
                    sueldoPromAuxiliares+=trabajadores.getTrabajadorIndex(i).getSueldo();
                }
                else{
                    if(trabajador instanceof Ingeniero){
                        cantIngenieros++;
                        sueldoPromIngenieros+=trabajadores.getTrabajadorIndex(i).getSueldo();
                    }
                }
            }
        }
        sueldoPromAstronomos=sueldoPromAstronomos/cantAstronomos;
        sueldoPromAuxiliares=sueldoPromAuxiliares/cantAuxiliares;
        sueldoPromIngenieros=sueldoPromIngenieros/cantIngenieros;
        text+="\nExisten \u001B[1m"+cantAstronomos+" Astrónomos\u001B[0m. Cuyo sueldo promedio es \u001B[1m$"+sueldoPromAstronomos+"\u001B[0m";
        text+="\nExisten \u001B[1m"+cantIngenieros+" Ingenieros\u001B[0m. Cuyo sueldo promedio es \u001B[1m$"+sueldoPromIngenieros+"\u001B[0m";
        text+="\nExisten \u001B[1m"+cantAuxiliares+" Auxiliares\u001B[0m. Cuyo sueldo promedio es \u001B[1m$"+sueldoPromAuxiliares+"\u001B[0m";
        return text;
    }

    @Override
    public String obtenerDatosDepartamentos() {
        String text ="";
        for (int i = 0; i < departamentos.getCant(); i++) {
            text+="\u001B[1m["+(i+1)+"]\u001B[0m\t"+departamentos.getDepartamentoIndex(i).toString()+"\n";
        }
        if (departamentos.getCant()==0){
            throw new NullPointerException("No hay departamentos.");
        }
        return text;
    }

    @Override
    public String obtenerDatosAuxiliares() {
        String text = "";
        for (int i = 0; i < trabajadores.getCant(); i++) {
            if (trabajadores.getTrabajadorIndex(i) instanceof AuxiliarAseo){
                AuxiliarAseo aux = (AuxiliarAseo) trabajadores.getTrabajadorIndex(i);
                if (i+1<10){
                    text+="\u001B[1m[Trabajador 00"+(i+1)+"]\u001B[0m\t Auxiliar "+aux.toString()+"\n";
                }
                else{
                    if (i+1<100){
                        text+="\u001B[1m[Trabajador 0"+(i+1)+"]\u001B[0m\t Auxiliar "+aux.toString()+"\n";
                    }
                    else{
                        text+="\u001B[1m[Trabajador "+(i+1)+"]\u001B[0m\t Auxiliar "+aux.toString()+"\n";
                    }
                }
            }
        }
        return text;
    }

    @Override
    public String obtenerDatosAstronomos(){
        String text = "";
        for (int i = 0; i < trabajadores.getCant(); i++) {
            if (trabajadores.getTrabajadorIndex(i) instanceof Astronomo){
                Astronomo aux = (Astronomo) trabajadores.getTrabajadorIndex(i);
                if (i+1<10){
                    text+="\u001B[1m[Trabajador 00"+(i+1)+"]\u001B[0m\t Astrónomo "+aux.toString()+"\n";
                }
                else{
                    if (i+1<100){
                        text+="\u001B[1m[Trabajador 0"+(i+1)+"]\u001B[0m\t Astrónomo "+aux.toString()+"\n";
                    }
                    else{
                        text+="\u001B[1m[Trabajador "+(i+1)+"]\u001B[0m\t Astrónomo "+aux.toString()+"\n";
                    }
                }
            }
        }
        return text;
    }

    @Override
    public String obtenerDatosIngenierosOcupacion() {
        String text = "";
        int cantSueldoMayor=0;
        int totalIng=0;
        int ocupaciontotalDepartamentos = departamentos.getCant()*100; //existen hasta 100 trabajadores en un departamento
        for (int i = 0; i < trabajadores.getCant(); i++) {
            Trabajador t = trabajadores.getTrabajadorIndex(i);
            if (t instanceof Ingeniero){
                Ingeniero ing = (Ingeniero) t;
                totalIng++;
                if (ing.getSueldo()>4000000){
                    cantSueldoMayor++;
                }
            }
        }
        double porcentaje = (totalIng/(double)ocupaciontotalDepartamentos)*100;
        double porcentaje2 = (cantSueldoMayor/(double)totalIng)*100;
        text+="\n El "+porcentaje2+"% de los ingenieros gana más de 4 millones de pesos.";
        if(ocupaciontotalDepartamentos==0){
            text+="\n No hay departamentos disponibles. Los ingenieros están trabajando en la interperie.";
        }
        else{
            text+="\n El porcentaje de ocupación de la totalidad de ingenieros en los departamentos es "+porcentaje+"%";
        }
        return text;
    }
    
    @Override
    public String obtenerDatosIngenieros(){
        String text = "";
        for (int i = 0; i < trabajadores.getCant(); i++) {
            if (trabajadores.getTrabajadorIndex(i) instanceof Ingeniero){
                Ingeniero aux = (Ingeniero) trabajadores.getTrabajadorIndex(i);
                if (i+1<10){
                    text+="\u001B[1m[Trabajador 00"+(i+1)+"]\u001B[0m\t Ingeniero "+aux.toString()+"\n";
                }
                else{
                    if (i+1<100){
                        text+="\u001B[1m[Trabajador 0"+(i+1)+"]\u001B[0m\t Ingeniero "+aux.toString()+"\n";
                    }
                    else{
                        text+="\u001B[1m[Trabajador "+(i+1)+"]\u001B[0m\t Ingeniero "+aux.toString()+"\n";
                    }
                }
            }
        }
        return text;
    }
}