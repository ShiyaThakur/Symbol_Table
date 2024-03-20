import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SymbolEntry {
    String name;
    String type;
    int address;
    int size;

    public SymbolEntry(String name, String type, int address, int size) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-10d %-10d", name, type, address, size);
    }
}

class SymbolTable12 {
    private SymbolEntry[] entries;
    private int count;

    public SymbolTable12(int capacity) {
        entries = new SymbolEntry[capacity];
        count = 0;
    }

    public void addToSymbolTable(String name, String type, int size) {
        if (count < entries.length) {
            int address = (count == 0) ? 0 : (entries[count - 1].address + entries[count - 1].size);

            entries[count] = new SymbolEntry(name, type, address, size);
            count++;
        } else {
            System.out.println("Symbol table is full.");
        }
    }

    public void displaySymbolTable() {
        System.out.println("Symbol Table:");
        System.out.printf("%-20s %-20s %-10s %-10s\n", "Name", "Type", "Address", "Size");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.println(entries[i]);
        }
    }
}

public class SymbolTable12{
    public static void main(String[] args) {
        SymbolTable12 symbolTable = new SymbolTable12(100);

        try (BufferedReader br = new BufferedReader(new FileReader("inputex.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String name = parts[0];
                    String type = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    symbolTable.addToSymbolTable(name, type, size);
                } else {
                    System.err.println("Invalid line in input file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the symbol table
        symbolTable.displaySymbolTable();
    }
}
