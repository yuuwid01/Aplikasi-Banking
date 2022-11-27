
import java.util.Scanner;

public class HomePage {
    private Scanner input = new Scanner(System.in);

    public HomePage() {
        initialPage();
    }

    private void initialPage() {
        System.out.println("=======================================");
        System.out.println("--------------- BANKING ---------------");
        System.out.println("=======================================");
        int menu;
        do {
            System.out.print("""
                    1. Login
                    2. Daftar Rekening
                    0. Keluar
                    """);

            System.out.print("Pilih Menu: ");
            menu = input.nextInt();
            input.nextLine();
            switchMenu(menu);
        } while (menu != 0);
    }

    private void switchMenu(int menu) {
        switch (menu) {
            case 1:
                new LoginPage();
                break;
            case 2:
                new RegistrasiPage();
                break;
            case 0:
                break;
        }
    }

}
