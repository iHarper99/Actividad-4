import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private final Map<String, String> contacts = new HashMap<>();
    private final Path filePath;

    public AddressBook(String filename) {
        this.filePath = Paths.get(filename);
    }

    public void load() {
        contacts.clear();

        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("No se creó el archivo: " + e.getMessage());
                return;
            }
        }

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String number = parts[0].trim();
                String name = parts[1].trim();

                if (!number.isEmpty() && !name.isEmpty()) {
                    contacts.put(number, name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar contactos: " + e.getMessage());
        }
    }

    public void save() {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    public void list() {
        System.out.println("Contactos:");
        if (contacts.isEmpty()) {
            System.out.println("(Sin contactos)");
            return;
        }
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public boolean create(String number, String name) {
        number = number.trim();
        name = name.trim();

        if (number.isEmpty() || name.isEmpty()) return false;

        if (!number.matches("\\d+")) {
            System.out.println("El número debe tener solo dígitos.");
            return false;
        }

        contacts.put(number, name);
        return true;
    }

    public boolean delete(String number) {
        return contacts.remove(number) != null;
    }
}
