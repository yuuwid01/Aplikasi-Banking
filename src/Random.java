
public class Random {

    public static String getNumeric(int n) {
        String numericString = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (numericString.length() * Math.random());

            sb.append(numericString.charAt(index));
        }

        return sb.toString();
    }
}
