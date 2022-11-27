import java.util.ArrayList;

public class Bank {
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void tambahUser(User user, Rekening rekening) {
        int newId;
        if (users.size() == 0) {
            newId = 1;
        } else {
            newId = users.get(users.size() - 1).getIdUser() + 1;
        }
        user.setIdUser(newId);
        user.buatRekening(rekening);
        users.add(user);
    }

    public static int cekSaldo(User user) {
        return user.getRekening().getSaldo();
    }

    public static void setorTunai(User user, Transaksi transaksi) {
        user.getRekening().tambahTransaksi(transaksi);
        user.getRekening().tambahSaldo(transaksi.getNominal());
    }

    public static boolean tarikTunai(User user, Transaksi transaksi) {
        boolean status = user.getRekening().ambilSaldo(transaksi.getNominal());
        if (status) {
            user.getRekening().tambahTransaksi(transaksi);
        }
        return status;
    }

    public static boolean transfer(Transaksi transaksi) {
        Transfer transfer = (Transfer) transaksi;
        User userAsal = transfer.getUserAsal();
        User userTujuan = transfer.getUserTujuan();

        for (User akunTujuan : users) {
            if (akunTujuan.getIdUser() == userTujuan.getIdUser()) {
                userTujuan.getRekening().tambahTransaksi(transfer);
                userTujuan.getRekening().tambahSaldo(transfer.getNominal());

                userAsal.getRekening().tambahTransaksi(transfer);
                userAsal.getRekening().ambilSaldo(transfer.getNominal());
                return true;
            }
        }

        return false;
    }
}
