
public class Akun {
    private static User userLogged = null;

    public static void initialUser() {
        User user = new User("Test 1", "123456", "0811", "user1", "123");
        Bank.tambahUser(user, new Rekening("123456", "123"));
        Bank.setorTunai(user, new SetorTunai(1000000));

        User user2 = new User("Test 2", "654321", "08122", "user2", "321");
        Bank.tambahUser(user2, new Rekening("654321", "321"));
    }

    public static User getUserLogged() {
        return userLogged;
    }

    public static boolean login(String username, String kodeAkses) {
        for (User user : Bank.getUsers()) {
            if ((user.getUsername().equals(username)) && (user.getKodeAkses().equals(kodeAkses))) {
                userLogged = user;
                return true;
            }
        }
        return false;
    }

    public static boolean verifPin(String pin) {
        return userLogged.getRekening().getPin().equals(pin);
    }

    public static User cariRekening(String rekening) {
        for (User user : Bank.getUsers()) {
            if (user.getRekening().getNoRekening().equals(rekening)) {
                return user;
            }
        }

        return null;
    }
    
}
