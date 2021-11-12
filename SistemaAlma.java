public interface SistemaAlma {
    /**
     * Se ingresa un astronomo al sistema, esto significa que se ingresa a la lista de trabajadores.
     * @param rut
     * @param sueldo
     * @param celestes
     * @return retorna true si se pudo concretar el ingreso, sino false
     */
    public boolean ingresarAstronomo(String rut, int sueldo, int celestes);

    /**
     * Se obtienen la cantidad de departamentos, la cantidad de ingenieros, auxiliares y astrónomos, junto con el
     * promedio de sueldo por cada tipo de trabajador .
     */
    public String obtenerDatosGeneralesAlma();

    /**
     * Se ingresa un auxiliar de aseo al sistema, esto significa que se ingresa a la lista de trabajadores.
     * @param rut
     * @param sueldo
     * @return retorna true si se pudo concretar el ingreso, sino false
     */
    public boolean ingresarAuxiliar(String rut, int sueldo);

    /**
     * Se ingresa un ingeniero de aseo al sistema, esto significa que se ingresa a la lista de trabajadores.
     * @param rut
     * @param sueldo
     * @param años_experiencia
     * @return retorna true si se pudo concretar el ingreso, si no, false.
     */
    public boolean ingresarIngeniero(String rut, int sueldo, int años_experiencia);

    /**
     * Se ingresa un departamento al sistema, esto significa que se ingresa a la lista de departamentos.
     * @param calle
     * @param numero
     * @return retorna true si se pudo contretar el ingreso (o el departamento ya existía en el sistema), si no, false.
     */
    public boolean ingresarDepartamento(String calle, String numero);

    /**
     * Con un departamento y un trabajador existentes, se procede a asociarlos:
     * 1.- Se ingresa el trabajador a la lista de trabajadores del departamento.
     * 2.- Se referencia al departamento desde el atributo departamento en el objeto del trabajador.
     * @param calle
     * @param numero
     * @param rut
     * @return retorna true si se realizó la asociación, si no, false.
     */
    public boolean asociarTrabajadorDepartamento(String calle, String numero, String rut);

    /**
     * Con un departamento y un auxiliar de aseo existente, el departamento cambia su atributo limpiado de falso a true.
     * @param direccion
     * @param numero
     * @param rut
     * @return retorna true si se pudo limpiar el departamento, en caso de que alguno de los dos objetos en cuestión no existan, se devolverá falso.
     */
    public boolean limpiarDepartamento(String direccion, String numero, String rut);

    /**
     * Con un astronomo existente, se obtienen los datos de su paper.
     * @param rut
     * @return retorna una cadena de caracteres que contiene el resumen de su paper.
     */
    public String obtenerDatosDePaper(String rut);

    /**
     * Se obtienen los datos de los astronomos que están trabajando en departamentos limpios.
     * @return retorna una cadena de texto que contiene el método .toString() de los astronomos en departamentos limpios.
     */
    public String obtenerDatosAstronomosEnDepartamentosLimpios();

    /**
     * Se eliminan los departamentos sucios del sistema, esto significa:
     * 1.- Eliminar del sistema departamentos con atributo limpio falso.
     * 2.- Hacer nula la referencia del departamento en cada trabajador.
     * @return retorna true si fueron eliminados. Si no existian departamentos sucios se retorna false.
     */
    public boolean eliminarDepartamentosSucios();

    /**
     * 
     * @param rut
     * @return retorna una cadena de texto si se encontró el rut y este rut estaba asociado a un Ingeniero
     */
    public String mantenerBaseDatos(String rut);

    /**
     * Con un astronomo existente, se utilizan sus metodos de observación
     * @param rut
     * @return retorna true si el astrono existe. Si no retorna false.
     */
    public boolean registrarNuevaObservacionAstronomica(String rut);

    /**
     * Obtiene la dirección de todos los departamentos.
     * @return
     */
    public String obtenerDatosDepartamentos();

    /**
     * Obtiene los datos de todos los Auxiliares registrados en el sistema
     * @return
     */
    public String obtenerDatosAuxiliares();

    /**
     * Obtiene los datos de todos los Atrónomos registrados en el sistema
     * @return
     */
    public String obtenerDatosAstronomos();

    /**
     * Obtiene el porcentaje de ingenieros que ganan mas de $ 4 millones y el porcentaje de ocupación de todos los ingenieros en la totalidad de los departamentos.
     */
    public String obtenerDatosIngenierosOcupacion();

    /**
     * Obtiene los datos de los ingenieros.
     */
    public String obtenerDatosIngenieros();
}
