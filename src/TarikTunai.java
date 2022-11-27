
public class TarikTunai extends Transaksi {
    private int nominalTarik;

    public TarikTunai(int nominalTarik) {
        this.nominalTarik = nominalTarik;
    }

    @Override
    public int getNominal() {
        return nominalTarik;
    }

}
