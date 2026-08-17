/**
 * Classe modelo (entidade) que representa um usuário do sistema.
 * Guarda apenas o login e o HASH da senha (nunca a senha em texto puro).
 */
public class Usuario {

    private String login;
    private String senhaHash;

    public Usuario(String login, String senhaHash) {
        this.login = login;
        this.senhaHash = senhaHash;
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    /**
     * Formato usado para salvar no arquivo: login;senhaHash
     */
    public String toLinhaArquivo() {
        return login + ";" + senhaHash;
    }

    /**
     * Cria um Usuario a partir de uma linha do arquivo (login;senhaHash).
     * Retorna null se a linha estiver em formato inválido.
     */
    public static Usuario fromLinhaArquivo(String linha) {
        String[] partes = linha.split(";", 2);
        if (partes.length != 2) {
            return null;
        }
        return new Usuario(partes[0], partes[1]);
    }

    @Override
    public String toString() {
        return "Usuario{login='" + login + "'}";
    }
}