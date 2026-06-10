import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean status = true;

        while (status) {
            System.out.println("\n--- AES Encrypt / Decrypt ---");
            System.out.println(" ".repeat(10) + "with ECB");

            System.out.println("What to do?");
            System.out.println("\t1. Encrypt");
            System.out.println("\t2. Decrypt");
            System.out.print("Choose option (1-2): ");
            String option = scanner.nextLine().trim();

            if (!option.equals("1") && !option.equals("2")) {
                System.out.println("❌ Invalid option! Please choose 1 or 2.");
                continue; // Skip to next iteration
            }

            System.out.print("Type the text: ");
            String text = scanner.nextLine();
            if (text.isEmpty()) {
                System.out.println("❌ Text cannot be empty!");
                continue;
            }

            System.out.print("Type the key: ");
            String key = scanner.nextLine();
            if (key.isEmpty()) {
                System.out.println("❌ Key cannot be empty!");
                continue;
            }

            try {
                if (option.equals("1")) {
                    String encrypted = AESCipher.encrypt(text, key);
                    System.out.println("🔐 Encrypted Text: " + encrypted);
                } else if (option.equals("2")) {
                    String decrypted = AESCipher.decrypt(text, key);
                    System.out.println("🔓 Decrypted Text: " + decrypted);
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }

            System.out.print("Restart? (Y/N) ");
            String restart = scanner.nextLine().toLowerCase();

            if (restart.equals("n")) {
                break;}
        }
        scanner.close();
    }
}
