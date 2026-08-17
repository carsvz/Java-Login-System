import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 * Classe utilitária responsável por gerar o hash das senhas.
 * Centraliza a lógica de segurança: nenhuma outra classe deve
 * manipular senha em texto puro além do necessário para gerar o hash.
 */
public class HashUtil {

    // Construtor privado: classe utilitária não deve ser instanciada.
    private HashUtil() {
    }

    /**
     * Gera o hash SHA-256 de um texto (usado para a senha).
     */
    public static String gerarHash(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }
}