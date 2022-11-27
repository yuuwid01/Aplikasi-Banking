
public class Transfer extends Transaksi {
    private int nominal;
    private User userAsal;
    private User userTujuan;

    public Transfer(int nominal, User userAsal, User userTujuan) {
        this.nominal = nominal;
        this.userAsal = userAsal;
        this.userTujuan = userTujuan;
    }

    @Override
    public int getNominal() {
        return nominal;
    }

    public User getUserAsal() {
        return userAsal;
    }

    public User getUserTujuan() {
        return userTujuan;
    }

}
