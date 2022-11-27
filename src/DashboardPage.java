
import java.util.ArrayList;
import java.util.Scanner;

public class DashboardPage {
    private Scanner input = new Scanner(System.in);

    public DashboardPage() {
        initialPage();
    }

    private void initialPage() {
        System.out.println("=======================================");
        System.out.println("------------- HOME BANKING ------------");
        System.out.println("=======================================");
        int menu;
        do {
            System.out.print("""
                    1. Info Saldo
                    2. Setor Tunai
                    3. Tarik Tunai
                    4. Transfer
                    5. Mutasi Rekening
                    6. Info Akun
                    0. Keluar
                    """);
            System.out.print("Pilih Menu: ");
            menu = input.nextInt();
            input.nextLine();

            System.out.println();

            switchMenu(menu);
        } while (menu != 0);
    }

    private void switchMenu(int menu) {
        switch (menu) {
            case 1:
                infoSaldo();
                break;
            case 2:
                setorTunai();
                break;
            case 3:
                tarikTunai();
                break;
            case 4:
                transfer();
                break;
            case 5:
                mutasiRekening();
                break;
            case 6:
                infoAkun();
                break;
            case 0:
                System.out.println("Keluar...");
                break;
            default:
                System.out.println("Menu Tidak valid !");
                break;
        }
    }

    private void infoSaldo() {
        System.out.println("-------------- INFO SALDO -------------");

        System.out.println("Saldo: ");
        int saldo = Akun.getUserLogged().getRekening().getSaldo();
        System.out.println("Rp. " + Rupiah.format(saldo));

        System.out.println("---------------------------------------");
    }

    private void setorTunai() {
        System.out.println("------------- SETOR  SALDO ------------");

        if (verifPin()) {
            System.out.print("Masukan Nominal Saldo: Rp. ");
            int nominal = input.nextInt();
            input.nextLine();

            Bank.setorTunai(Akun.getUserLogged(), new SetorTunai(nominal));
        }

        System.out.println("---------------------------------------");
    }

    private void tarikTunai() {
        System.out.println("------------- TARIK  SALDO ------------");

        if (verifPin()) {
            System.out.println("""
                    Masukan Nominal Saldo:
                    1. Rp. 50.000
                    2. Rp. 100.000
                    3. Rp. 200.000
                    4. Rp. 500.000
                    5. Rp. 1.000.000
                    6. Rp. 2.000.000
                    0. Batal""");

            System.out.print("Pilih: ");
            int pilihNominal = input.nextInt();
            input.nextLine();

            int nominal = 0;
            switch (pilihNominal) {
                case 1:
                    nominal = 50000;
                    break;
                case 2:
                    nominal = 100000;
                    break;
                case 3:
                    nominal = 200000;
                    break;
                case 4:
                    nominal = 500000;
                    break;
                case 5:
                    nominal = 1000000;
                    break;
                case 6:
                    nominal = 2000000;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid !");
                    break;
            }

            if (pilihNominal != 0) {
                boolean status = Bank.tarikTunai(Akun.getUserLogged(), new TarikTunai(nominal));

                if (status) {
                    System.out.println("Berhasil Tarik Tunai sebesar Rp." + Rupiah.format(nominal));
                } else {
                    System.out.println("Saldo Rekening Anda Tidak Cukup !");
                }
            }
        }

        System.out.println("---------------------------------------");
    }

    private void transfer() {
        System.out.println("--------------- TRANSFER --------------");

        System.out.print("Masukan No Rekening Tujuan: ");
        String noRekeningTujuan = input.nextLine();

        User userTujuan = Akun.cariRekening(noRekeningTujuan);

        if (userTujuan != null) {
            if (userTujuan.getIdUser() != Akun.getUserLogged().getIdUser()) {
                System.out.print("Masukan Nominal Transfer  : ");
                int nominal = input.nextInt();
                input.nextLine();

                if (nominal < Akun.getUserLogged().getRekening().getSaldo()) {
                    System.out.println("---------------------------------------");
                    System.out.println("Transfer Ke");
                    System.out.println("No Rekening : " + userTujuan.getRekening().getNoRekening());
                    System.out.println("Atas Nama   : " + userTujuan.getNamaLengkap());
                    System.out.println("Nominal Sebesar");
                    System.out.println("Rp. " + Rupiah.format(nominal));
                    System.out.println("---------------------------------------");
                    System.out.print("Transfer (y/n) : ");
                    char konf = input.nextLine().charAt(0);

                    if (konf == 'y') {
                        if (verifPin()) {
                            boolean status = Bank.transfer(new Transfer(nominal, Akun.getUserLogged(), userTujuan));

                            if (status) {
                                System.out.println("Berhasil ");
                            }
                        } else {
                            System.out.println("Coba Lagi !");
                        }
                    } else {
                        System.out.println("Dibatalkan !");
                    }
                } else {
                    System.out.println("Saldo Tidak Cukup !");
                }

            } else {
                System.out.println("Tidak dapat Transfer ke Rekening Sendiri !");
            }

        } else {
            System.out.println("No Rekening tidak Ditemukan !");
        }

        System.out.println("---------------------------------------");
    }

    private void mutasiRekening() {
        System.out.println("----------- MUTASI  REKENING ----------");

        User userLogged = Akun.getUserLogged();

        ArrayList<Transaksi> mutasi = userLogged.getRekening().getMutasi();

        for (Transaksi transaksi : mutasi) {

            if (transaksi instanceof SetorTunai) {
                System.out.println("Transaksi           : Setor Tunai");
                System.out.println("Tanggal Setor Tunai : " + transaksi.getTanggalTransaksi());
                System.out.println("Nominal Setor Tunai : Rp. " + Rupiah.format(transaksi.getNominal()));
            } else if (transaksi instanceof TarikTunai) {
                System.out.println("Transaksi           : Tarik Tunai");
                System.out.println("Tanggal Tarik Tunai : " + transaksi.getTanggalTransaksi());
                System.out.println("Nominal Tarik Tunai : Rp. " + Rupiah.format(transaksi.getNominal()));

            } else if (transaksi instanceof Transfer) {
                Transfer tf = (Transfer) transaksi;
                System.out.println("Transaksi           : Transfer");
                System.out.println("Tanggal Transfer    : " + transaksi.getTanggalTransaksi());
                System.out.println("Rekening Asal       : " + tf.getUserAsal().getRekening().getNoRekening());
                System.out.println("Asal Atas Nama      : " + tf.getUserAsal().getNamaLengkap());
                System.out.println("Rekening Tujuan     : " + tf.getUserTujuan().getRekening().getNoRekening());
                System.out.println("Tujuan Atas Nama    : " + tf.getUserTujuan().getNamaLengkap());
                System.out.println("Nominal Transfer    : Rp." + Rupiah.format(transaksi.getNominal()));

            }
            System.out.println("---------------------------------------");
        }
        System.out.println("---------------------------------------");
    }

    private void infoAkun() {
        User user = Akun.getUserLogged();
        System.out.println("-------------- INFO  AKUN -------------");
        System.out.println("No Rekening     : " + user.getRekening().getNoRekening());
        System.out.println("Nama Lengkap    : " + user.getNamaLengkap());
        System.out.println("Username        : " + user.getUsername());
        System.out.println("NIK             : " + user.getNik());
        System.out.println("No Telp         : " + user.getNoTelp());
        System.out.println("---------------------------------------");
    }

    private boolean verifPin() {
        int percobaan = 3;

        do {
            percobaan -= 1;
            System.out.print("Masukan PIN: ");
            String pin = input.nextLine();

            boolean status = Akun.verifPin(pin);

            if (!status) {
                if (percobaan == 0) {
                    System.out.println("Anda Salah memasukan PIN sebanyak 3x");
                    return false;
                } else {
                    System.out.println("PIN Salah !");
                }
            } else {
                return true;
            }
        } while (percobaan != 0);

        return false;
    }
}
