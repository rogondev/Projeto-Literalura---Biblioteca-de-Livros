package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.DadosResposta;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ***************************************************
                    1 - Buscar livro pelo título
                    2 - Listar livros buscados
                    3 - Listar autores buscados
                    4 - Listar autores vivos em determinado ano
                    5 - Exibir quantidade de livros num determinado idioma
                    0 - Sair
                    ***************************************************
                    Escolha uma opção: """;
            System.out.println(menu);

            if (leitura.hasNextInt()) {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1 -> buscarLivroWeb();
                    case 2 -> listarLivrosBuscados();
                    case 3 -> listarAutoresBuscados();
                    case 4 -> listarAutoresVivosNoAno();
                    case 5 -> exibirQuantidadePorIdioma();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } else {
                System.out.println("Por favor, digite um número.");
                leitura.nextLine();
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        DadosResposta dados = conversor.obterDados(json, DadosResposta.class);

        if (dados != null && !dados.resultado().isEmpty()) {
            DadosLivro dadosLivro = dados.resultado().get(0);
            Livro livro = new Livro(dadosLivro);

            if (!dadosLivro.autores().isEmpty()) {
                Autor autor = new Autor(dadosLivro.autores().get(0));
                livro.setAutor(autor);
            }

            try {
                livroRepositorio.save(livro);
                System.out.println("\nLIVRO SALVO COM SUCESSO:\n" + livro);
            } catch (Exception e) {
                System.out.println("Erro ao salvar (Livro já existe ou erro no banco).");
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivrosBuscados() {
        livroRepositorio.findAll().forEach(System.out::println);
    }

    private void listarAutoresBuscados() {
        autorRepositorio.findAll().forEach(a -> System.out.println(
                "Autor: " + a.getNome() + " | Nascimento: " + a.getAnoNascimento() + " | Falecimento: " + a.getAnoFalecimento()
        ));
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano:");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> vivos = autorRepositorio.buscarAutoresVivosNoAno(ano);
        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor vivo em " + ano);
        } else {
            vivos.forEach(System.out::println);
        }
    }

    private void exibirQuantidadePorIdioma() {
        System.out.println("""
                Digite o idioma para busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = leitura.nextLine();
        Long quantidade = livroRepositorio.countByIdioma(idioma);
        System.out.println("Existem " + quantidade + " livros em " + idioma + " no banco de dados.");
    }
}