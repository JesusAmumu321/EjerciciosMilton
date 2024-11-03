import utils.DoubleLinkedList;
import utils.Nodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() {
        System.out.println("\n⥧⥧⥧⥧⥧⥧⥧ Menu ⥧⥧⥧⥧⥧⥧⥧");
        System.out.println("1. Crear una lista y agregar numeros para multiplicacion");
        System.out.println("2. Multiplicar listas");
        System.out.println("3. Mostrar lista");
        System.out.println("777. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void pressEnterToContinue() throws IOException {
        try {
            System.out.print("\n Presiona enter para continuar...");
            leer.readLine();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws IOException {
        DoubleLinkedList<Integer> lista1 = new DoubleLinkedList<>();
        DoubleLinkedList<Integer> lista2 = new DoubleLinkedList<>();
        DoubleLinkedList<Integer> resultado = null;

        boolean isON = true;


        while (isON) {
            menu();
            int opc = Integer.parseInt(leer.readLine());

            switch (opc) {
                case 1:
                    // Anadir cosas a la lista
                    System.out.print("\nSeleccione la lista (1 o 2): ");
                    int listaNum = Integer.parseInt(leer.readLine());

                    DoubleLinkedList<Integer> listaSeleccionada = (listaNum == 1) ? lista1 : lista2;

                    System.out.print("Ingrese la cantidad de dígitos del numero: ");
                    int digitos = Integer.parseInt(leer.readLine());
                    System.out.println("Ingrese los digitos del numero uno por uno (de izquierda a derecha):");

                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    for (int i = 0; i < digitos; i++) {
                        System.out.print("Ingrese el digito " + (i + 1) + ": ");
                        int digito = Integer.parseInt(leer.readLine());
                        listaSeleccionada.add(digito);
                    }
                    System.out.println("Numero agregado a la lista " + listaNum);

                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    break;
                case 2:
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    if (lista1.isEmpty() || lista2.isEmpty()) {
                        throw new IllegalArgumentException("Las listas no pueden estar vacias");
                    } else {
                        resultado = lista1.genMult(lista1, lista2);
                        System.out.println("Multiplicacion realizada");
                    }
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    break;

                case 3:
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    System.out.print("\nSeleccione la lista a mostrar (1, 2 o 3 para resultado): ");
                    int listaMostrar = Integer.parseInt(leer.readLine());
                    switch (listaMostrar) {
                        case 1:
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            System.out.print("Lista 1: ");
                            imprimirLista(lista1);
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            break;
                        case 2:
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            System.out.println("Lista 2: ");
                            imprimirLista(lista2);
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            break;

                        case 3:
                            if (resultado == null) {
                                System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                                System.out.println("No se ha hecho la multiplicacion, realizela e intente de nuevo");
                                System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            } else {
                                System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                                System.out.print("Resultado de la multiplicacion: ");
                                imprimirLista(resultado);
                                System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            }
                            break;
                        default:
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                            System.out.println("Opción invalida");
                            System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    }
                    break;

                case 777:
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    System.out.println("Gracias por usar 😊😊😊");
                    isON = false;
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    break;

                default:
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
                    System.out.println("Opción invalida. Intente de nuevo");
                    System.out.println("⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧⥧");
            }
        }
    }

    private static void imprimirLista(DoubleLinkedList<Integer> lista) {
        Nodo<Integer> actual = lista.getRoot().getRight();
        System.out.print("<");
        while (actual != null && actual != lista.getTail()) {
            System.out.print(actual.getValue());
            if (actual.getRight() != lista.getTail()) {
                System.out.print(",");
            }
            actual = actual.getRight();
        }
        System.out.println(">");
    }
}
