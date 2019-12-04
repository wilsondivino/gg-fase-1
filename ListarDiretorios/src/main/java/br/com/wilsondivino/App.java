package br.com.wilsondivino;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 */
public class App 
{
    public static void main(String[] args) throws IOException {
        if (!Files.isDirectory(Paths.get(args[0]))) {
            System.out.print("Isso não é um diretório!");
            return;
        }

        System.out.println("\t \t \n Diretorio");

        System.out.printf("\t => %s \n", args[0]);

        Files.list(Paths.get(args[0]))
                .forEach(path -> {
                    if (Files.isDirectory(Paths.get(path.toString()))) {
                        try {
                            System.out.print("\t \n SubDiretorio \n");
                            System.out.printf("\t => %s \n", path.toString());
                            listarArquivos(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.printf("=> %s \n", path.toString());
                    }
                });
    }

    public static void listarArquivos (Path path) throws IOException {
        Files.newDirectoryStream(Paths.get(path.toString()))
                .forEach(subPath -> {
                    StringBuffer buffer = new StringBuffer();

                    if (Files.isDirectory(Paths.get(subPath.toString()))) {
                        try {
                            buffer.append("\t");
                            System.out.printf("%s => %s \n", buffer.toString(), subPath.toString());
                            listarArquivos(subPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        buffer.append("\t");
                        buffer.append("\t");
                        System.out.printf("%s => %s \n", buffer.toString(), subPath.toString());
                    }
                });
    }
}
