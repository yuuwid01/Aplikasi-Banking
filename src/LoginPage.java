
import java.util.Scanner;

public class LoginPage {
    private Scanner input = new Scanner(System.in);

    public LoginPage() {
        login();
    }

    private void login() {
        while (true) {
            String username, kodeAkses;
            System.out.println("================ LOGIN ================");
            System.out.print("Username  : ");
            username = input.nextLine();
            System.out.print("Kode Akses: ");
            kodeAkses = input.nextLine();

            if (Akun.login(username, kodeAkses)) {
                new DashboardPage();
                break;
            } else {
                loginGagal();
            }
        }
    }

    private void loginGagal() {
        System.out.println("---------------------------------------");
        System.out.println("------------- LOGIN GAGAL -------------");
        System.out.println("    Username atau Kode Akses SALAH!    ");
        System.out.println("---------------------------------------");
    }

}
