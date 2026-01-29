import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("contacts.csv");
        addressBook.load();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENÚ AGENDA ===");
            System.out.println("1) list   - Mostrar contactos");
            System.out.println("2) create - Crear contacto");
            System.out.println("3) delete - Eliminar contacto");
            System.out.println("4) save   - Guardar cambios");
            System.out.println("5) load   - Recargar desde archivo");
            System.out.println("0) exit   - Salir");
            System.out.print("Elige una opción: ");

            String option = sc.nextLine().trim().toLowerCase();

            switch (option) {
                case "1":
                case "list":
                    addressBook.list();
                    break;

                case "2":
                case "create":
                    System.out.print("Nombre: ");
                    String name = sc.nextLine();
                    System.out.print("Número: ");
                    String number = sc.nextLine();

                    if (addressBook.create(number, name)) {
                        System.out.println("Contacto guardado en memoria.");
                    } else {
                        System.out.println("No se pudo crear el contacto (datos inválidos).");
                    }
                    break;

                case "3":
                case "delete":
                    System.out.print("Número a eliminar: ");
                    String numToDelete = sc.nextLine();

                    if (addressBook.delete(numToDelete)) {
                        System.out.println("Contacto eliminado.");
                    } else {
                        System.out.println("No existe un contacto con ese número.");
                    }
                    break;

                case "4":
                case "save":
                    addressBook.save();
                    System.out.println("Cambios guardados en contacts.csv");
                    break;

                case "5":
                case "load":
                    addressBook.load();
                    System.out.println("Contactos recargados desde contacts.csv");
                    break;

                case "0":
                case "exit":
                    addressBook.save();
                    System.out.println("Guardado final realizado. Bye.");
                    running = false;
                    break;

                default:
                    System.out.println("Opción inválida. Intenta otra.");
            }
        }

        sc.close();
    }
}
