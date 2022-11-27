import java.util.ArrayList;

public class Rekening extends Saldo {
    private String noRekening;
    private String pin;
    private ArrayList<Transaksi> mutasi = new ArrayList<>();

    public Rekening(String noRekening, String pin) {
        this.noRekening = noRekening;
        this.pin = pin;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public String getPin() {
        return pin;
    }

    public ArrayList<Transaksi> getMutasi() {
        return mutasi;
    }

    public void tambahTransaksi(Transaksi transaksi) {
        mutasi.add(transaksi);
    }

}
