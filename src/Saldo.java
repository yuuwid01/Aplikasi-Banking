
public class Saldo {
    private int saldo = 0;

    public void tambahSaldo(int nominal) {
        saldo += nominal;
    }

    public boolean ambilSaldo(int nominal) {
        if (saldo >= nominal) {
            saldo -= nominal;
            return true;
        }
        return false;
    }

    public int getSaldo() {
        return saldo;
    }

}
