import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Camada de persistência (Repositório).
 * Responsável exclusivamente por salvar e carregar os usuários
 * de um arquivo de texto. Nenhuma regra de negócio deve ficar aqui.
 */
public class UsuarioRepositorio {

    private final String caminhoArquivo;
    private Map<String, Usuario> usuarios;

    public UsuarioRepositorio(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.usuarios = new HashMap<>();
        carregar();
    }

    /**
     * Carrega os usuários do arquivo para a memória.
     */
    private void carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                Usuario usuario = Usuario.fromLinhaArquivo(linha);
                if (usuario != null) {
                    usuarios.put(usuario.getLogin(), usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    /**
     * Persiste todos os usuários atuais no arquivo (sobrescreve o arquivo).
     */
    private void salvarTodos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Usuario usuario : usuarios.values()) {
                bw.write(usuario.toLinhaArquivo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public boolean existeLogin(String login) {
        return usuarios.containsKey(login);
    }

    public Usuario buscarPorLogin(String login) {
        return usuarios.get(login);
    }

    /**
     * Adiciona um novo usuário e persiste no arquivo.
     */
    public void salvar(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
        salvarTodos();
    }
}