import java.util.Scanner;

/**
 * Classe principal (camada de apresentação / UI de console).
 * Responsável apenas por exibir o menu e capturar a entrada do usuário.
 * Toda a lógica de negócio fica delegada ao AutenticadorService.
 */
public class SistemaLogin {

    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public static void main(String[] args) {
        UsuarioRepositorio repositorio = new UsuarioRepositorio(ARQUIVO_USUARIOS);
        AutenticadorService autenticadorService = new AutenticadorService(repositorio);
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.\n");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner, autenticadorService);
                    break;
                case 2:
                    fazerLogin(scanner, autenticadorService);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== SISTEMA DE LOGIN =====");
        System.out.println("1 - Cadastrar novo usuário");
        System.out.println("2 - Fazer login");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarUsuario(Scanner scanner, AutenticadorService service) {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");

        System.out.print("Digite o login desejado: ");
        String login = scanner.nextLine().trim();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.print("Confirme a senha: ");
        String confirmaSenha = scanner.nextLine();

        try {
            service.cadastrar(login, senha, confirmaSenha);
            System.out.println("Usuário cadastrado com sucesso!\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro: " + e.getMessage() + "\n");
        }
    }

    private static void fazerLogin(Scanner scanner, AutenticadorService service) {
        System.out.println("\n--- LOGIN ---");

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean autenticado = service.autenticar(login, senha);

        if (autenticado) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + login + "!\n");
        } else {
            System.out.println("Login ou senha inválidos.\n");
        }
    }
}