
public class SetorTunai extends Transaksi {
    private int nominalSetor;

    public SetorTunai(int nominalSetor) {
        this.nominalSetor = nominalSetor;
    }

    @Override
    public int getNominal() {
        return nominalSetor;
    }
}
