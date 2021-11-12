import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        SistemaAlmaImpl system = new SistemaAlmaImpl();
        Lectura(system);
        Menu(system);
    }

    private static void Menu(SistemaAlmaImpl system) {
        System.out.println(system.obtenerDatosGeneralesAlma());
        opciones(system);
    }

    private static void desplegarOpciones(){
        System.out.println("\u001B[1m \t\t\t-Opciones-\u001B[0m");
        System.out.println("\u001B[1m[1]\u001B[0m Asignar a auxiliar la limpieza de un departamento y desplegar sus datos.");
        System.out.println("\u001B[1m[2]\u001B[0m Realizar observación astronómica. ");
        System.out.println("\u001B[1m[3]\u001B[0m Desplegar paper de Astrónomo. ");
        System.out.println("\u001B[1m[4]\u001B[0m Desplegar astrónomos en departamentos limpios. ");
        System.out.println("\u001B[1m[5]\u001B[0m Desplegar porcentaje de ingenieros con sueldo mayor a $4M y el porcentaje de ocupación del total de ingenieros en el total de departamentos.");
        System.out.println("\u001B[1m[6]\u001B[0m Alerta Covid-19. Eliminación del departamento.");
        System.out.println("\u001B[1m[7]\u001B[0m Mantener base de datos. ");
        System.out.println("\u001B[1m[0]\u001B[0m Salir del sistema. ");
    }

    private static void opciones(SistemaAlmaImpl system) {
        desplegarOpciones();
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese una opción: ");
        String opcion = scan.nextLine();
        int op;
        while(true){
            try{
                op = Integer.parseInt(opcion);
                if (op>0 && op<8){
                    if (op==1){
                        System.out.println(system.obtenerDatosDepartamentos());
                        System.out.println("Ingrese calle del departamento: ");
                        String calle = scan.nextLine();
                        System.out.println("Ingrese número del departamento: ");
                        String numero = scan.nextLine();
                        System.out.println(system.obtenerDatosAuxiliares());
                        System.out.println("Ingrese rut del Auxiliar de aseo: ");
                        String rut = scan.nextLine();
                        if(system.limpiarDepartamento(calle, numero, rut)){
                            System.out.println("\n\u001B[1mDepartamento ubicado en "+calle+", "+numero+" higienizado correctamente.\u001B[0m");
                            throw new NullPointerException("");
                        }
                    }
                    if (op==2){
                        System.out.println(system.obtenerDatosAstronomos());
                        System.out.println("Ingrese rut del Astrónomo: ");
                        String rut = scan.nextLine();
                        if(system.registrarNuevaObservacionAstronomica(rut)){
                            System.out.println("\n\u001B[1mEl astrónomo de rut "+rut+" ha observado los cielos y ha vigilado el campo magnético. \u001B[0m");
                            throw new NullPointerException("");
                        }
                        else{
                            System.out.println("El rut "+rut+" no está asociado a ningún astrónomo. ");
                            throw new NullPointerException("");
                        }
                    }
                    if (op==3){
                        System.out.println(system.obtenerDatosAstronomos());
                        System.out.println("Ingrese rut del Astrónomo: ");
                        String rut = scan.nextLine();
                        String paper = system.obtenerDatosDePaper(rut);
                        System.out.println("\n\u001B[1m"+paper+"\n\u001B[0m");
                        throw new NullPointerException("");
                    }
                    if (op==4){
                        System.out.println("\n\u001B[1m"+system.obtenerDatosAstronomosEnDepartamentosLimpios()+"\u001B[0m");
                        throw new NullPointerException("");
                    }
                    if (op==5){
                        System.out.println("\n\u001B[1m"+system.obtenerDatosIngenierosOcupacion()+"\n\u001B[0m");
                        throw new NullPointerException("");
                    }
                    if (op==6){
                        if(system.eliminarDepartamentosSucios()){
                            System.out.println("\n\u001B[1mDepartamentos Sucios eliminados.\n\u001B[0m");
                            throw new NullPointerException("");
                        }
                        else{
                            System.out.println("\n\u001B[1mDepartamentos Sucios no eliminados.\n\u001B[0m");
                            throw new NullPointerException("");
                        }
                    }
                    if (op==7){
                        System.out.println(system.obtenerDatosIngenieros());
                        System.out.println("Ingrese rut del Astrónomo: ");
                        String rut = scan.nextLine();
                        System.out.println("\n\u001B[1m"+system.mantenerBaseDatos(rut)+"\n\u001B[0m");
                        throw new NullPointerException("");
                    }
                    else{
                        throw new NumberFormatException();
                    }
                }
                else{
                    if(op==0){
                        break;
                    }
                    else{
                        throw new NumberFormatException();
                    }
                }
            }
            catch (NumberFormatException exception){
                System.out.print("Ingrese una opción válida: ");
                opcion = scan.nextLine();
            }
            catch (NullPointerException e){
                System.out.println(e.getMessage());
                desplegarOpciones();
                System.out.print("Ingrese una opción: ");
                opcion = scan.nextLine();
            }   
        }
    }

    private static void Lectura(SistemaAlmaImpl system) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("alma.txt"));
        while (scan.hasNextLine()) {
            String linea = scan.nextLine();
            String[] partes = linea.split(",");
            String rol = partes[0];
            String rut = partes[1];
            int sueldo = Integer.parseInt(partes[2]);
            if (rol.equals("Auxiliar")) {
                if (!system.ingresarAuxiliar(rut, sueldo)) {
                    error("Lectura");
                }
            } else {
                String calle = partes[4];
                String numero = partes[5];
                if (!system.ingresarDepartamento(calle, numero)) {
                    error("Lectura");
                }
                if (rol.equals("Astronomo")) {
                    int celestes = Integer.parseInt(partes[3]);
                    if (!system.ingresarAstronomo(rut, sueldo, celestes)) {
                        error("Lectura");
                    }
                }
                if (rol.equals("Ingeniero")) {
                    int experiencia = Integer.parseInt(partes[3]);
                    if (!system.ingresarIngeniero(rut, sueldo, experiencia)) {
                        error("Lectura");
                    }
                }
                system.asociarTrabajadorDepartamento(calle, numero, rut);
            }
        }
    }

    private static void error(String metodo) {
        System.out.println("Error en " + metodo);
        System.exit(0);
    }

}
