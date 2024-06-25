import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("*** Conversor de monedas ***");
            System.out.println("ARS - Peso argentino");
            System.out.println("BOB - Peso boliviano");
            System.out.println("BRL - Real brasileño");
            System.out.println("CLP - Peso chileno");
            System.out.println("COP - Peso colombiano");
            System.out.println("USD - Dólar estadounidense");
            System.out.println("MXN - Peso mexicano");
            System.out.println("EUR - Euro");
            System.out.println("Salir - Finalizar programa");
            try {
                System.out.print("Ingrese la moneda de origen: ");
                String monedaOrigen = sc.nextLine().toUpperCase();
                if (monedaOrigen.equals("SALIR")) {
                    continuar = false;
                    break;
                }

                System.out.print("Ingrese la moneda de salida: ");
                String monedaSalida = sc.nextLine().toUpperCase();
                if (monedaSalida.equals("SALIR")) {
                    continuar = false;
                    break;
                }

                if (monedaOrigen.isEmpty() || monedaSalida.isEmpty() || monedaOrigen.length() != 3 || monedaSalida.length() != 3) {
                    throw new IllegalArgumentException("Código de moneda no válido. Debe ser de 3 caracteres.");
                }

                System.out.print("Ingrese la cantidad que deseas cambiar: ");
                double cantidad = sc.nextDouble();
                System.out.println("Espera un momento...");
                sc.nextLine();

                if (cantidad <= 0) {
                    throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
                }

                consultaAPI.convertirMoneda(monedaOrigen, monedaSalida, cantidad);
                System.out.print("\n¿Deseas hacer otra conversión? (si/no): ");
                String respuesta = sc.nextLine().toLowerCase();
                if (!respuesta.equals("si")) {
                    continuar = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingresa un valor válido.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        System.out.println("Gracias por usar el conversor de monedas.");
    }
}
