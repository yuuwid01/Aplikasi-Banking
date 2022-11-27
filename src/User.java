
public class User {
    private int idUser;

    private String namaLengkap;
    private String nik;
    private String noTelp;
    private String username;
    private String kodeAkses;
    private Rekening rekening;

    public User(String namaLengkap, String nik, String noTelp, String username, String kodeAkses) {
        this.namaLengkap = namaLengkap;
        this.nik = nik;
        this.noTelp = noTelp;
        this.username = username;
        this.kodeAkses = kodeAkses;
    }

    public void buatRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getNik() {
        return nik;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getUsername() {
        return username;
    }

    public String getKodeAkses() {
        return kodeAkses;
    }

    public Rekening getRekening() {
        return rekening;
    }

}
