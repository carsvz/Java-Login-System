/**
 * Camada de serviço (regras de negócio).
 * Responsável pela lógica de cadastro e login, usando o repositório
 * para persistência e o HashUtil para segurança da senha.
 */
public class AutenticadorService {

    private final UsuarioRepositorio repositorio;

    public AutenticadorService(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Cadastra um novo usuário.
     * Lança IllegalArgumentException se os dados forem inválidos.
     */
    public void cadastrar(String login, String senha, String confirmacaoSenha) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("O login não pode ser vazio.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }

        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }

        if (repositorio.existeLogin(login)) {
            throw new IllegalArgumentException("Esse login já está em uso.");
        }

        String senhaHash = HashUtil.gerarHash(senha);
        Usuario novoUsuario = new Usuario(login, senhaHash);
        repositorio.salvar(novoUsuario);
    }

    /**
     * Tenta autenticar um usuário.
     * Retorna true se login e senha estiverem corretos, false caso contrário.
     */
    public boolean autenticar(String login, String senha) {
        Usuario usuario = repositorio.buscarPorLogin(login);

        if (usuario == null) {
            return false;
        }

        String hashDigitado = HashUtil.gerarHash(senha);
        return hashDigitado.equals(usuario.getSenhaHash());
    }
}