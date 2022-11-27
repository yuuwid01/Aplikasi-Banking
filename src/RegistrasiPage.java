
import java.util.Scanner;

public class RegistrasiPage {
    private Scanner input = new Scanner(System.in);

    public RegistrasiPage() {
        daftar();
    }

    private void daftar() {
        String namaLengkap, nik, noTelp, username, kodeAkses;

        System.out.println("============ BUKA REKENING ============");
        System.out.print("Nama Lengkap  : ");
        namaLengkap = input.nextLine();

        System.out.print("NIK           : ");
        nik = input.nextLine();

        System.out.print("No Telp       : ");
        noTelp = input.nextLine();

        System.out.print("Username      : ");
        username = input.nextLine();

        System.out.print("Kode Akses    : ");
        kodeAkses = input.nextLine();

        User user = new User(namaLengkap, nik, noTelp, username, kodeAkses);

        System.out.print("Buat PIN      : ");
        String pin = input.nextLine();

        String noRekening = Random.getNumeric(6);

        Rekening rekening = new Rekening(noRekening, pin);

        Bank.tambahUser(user, rekening);

        System.out.println("Berhasil membuat Akun !");
    }
}
