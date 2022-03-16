import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.io.*;

public class Main {

    PrintWriter escribir;
    FileWriter fich;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        File file = new File("/home/dam1/IdeaProjects/GitHub/token.properties");
        String arch = "/home/dam1/IdeaProjects/GitHub/token.properties";
        if (file.exists()) {
            main.creacionRepositorio(arch); // Se llama al metodo si el archivo existe y si no se escribe el token y se crea
        } else {
            main.escribirElToken(arch);
            main.creacionRepositorio(arch);
        }
    }
    public  void creacionRepositorio(String fichero){
        GitHub github = null; //Si no se inicia en null posteriomente nos puede dar problemas
        try {
            new GitHubBuilder();
            github = GitHubBuilder
                    .fromPropertyFile(fichero)//Aqui le decimos que a github le ponga el file con el token para posteriormente crearlo.
                    .build();
        } catch (IOException e) {
            System.out.println("No autentico bien"); //Aviso de que no leyo el token bien
        }
        try {
            if (github != null) {
                GHRepository repo1 = github.createRepository(
                                "ApiCreadaPorCode")
                        .create();
            }
        } catch (IOException e) {
            System.out.println("Error porque ya existe el repo");
        }
    }

    public  void escribirElToken(String fichero){
        try {
            fich = new FileWriter(fichero, false);
            escribir.println(fichero);
            escribir.println("oauth="+JOptionPane.showInputDialog("Introducir token")); //Crea el archivo
        } catch (IOException e) { //Captura el error
            System.out.println("error escritura" + e.getMessage()); //Te salta el correspondiente error
        }finally { //codigo que se ejecuta siempre
            try {
                fich.close(); //se cierra el archivo y vuelve a capturar el error y su mensaje correspondiente
            } catch (IOException e) { //Coge el error
                System.out.println("no se pudo cerrar el archivo");
            }
        }
    }


}
