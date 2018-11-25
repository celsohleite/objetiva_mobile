package zi.objetivamobile.util;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leite on 19/09/17.
 */

public class ValidacaoUtil {
    /**
     *
     * @param email
     * @return
     */
    //valida e-mail
    public static boolean isValidEmail(String email) {

        boolean result = false;

        if ((email != null) || (email.trim().length() != 0)) {
            result = false;

            String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
            Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            result = matcher.matches();
        }
        return result;
    }


    public static String md5passConvert(String pass){

        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(pass.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }
}
