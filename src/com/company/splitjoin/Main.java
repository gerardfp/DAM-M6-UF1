package com.company.splitjoin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        split("/tmp/file", 4);
        join("/tmp/file");
    }

    static void split(String path, int parts) throws IOException {
        Path file = Paths.get(path);

        InputStream inputStream = Files.newInputStream(file);

        // Tamaño en bytes que se guarda en cada fichero-parte
        int partSize = (int) Math.ceil((float) inputStream.available() / parts);

        // Indica el numero de fichero-parte. Se añade a la extension del fichero: .part.1  .part.2  .part.3 ...
        int partNum = 0;

        OutputStream outputStream = Files.newOutputStream(Paths.get(path + ".part." + partNum));

        // Cuenta los bytes escritos en una fichero-parte. Cuando se llega al maximo se cambia de fichero-parte
        // y se vuelve a empezar
        int bytesWritten = 0;

        for(int b; (b = inputStream.read()) != -1;){
            outputStream.write(b);
            bytesWritten++;
            if (bytesWritten == partSize) {
                partNum++;
                outputStream = Files.newOutputStream(Paths.get(path + ".part." + partNum));
                bytesWritten=0;
            }
        }
    }

    static void join(String path) throws IOException {
        OutputStream outputStream = Files.newOutputStream(Paths.get(path));

        for (int partNum = 0; ; partNum++) {
            try (InputStream inputStream = Files.newInputStream(Paths.get(path + ".part." + partNum))) {
                for(int b; (b = inputStream.read()) != -1;){
                    outputStream.write(b);
                }
            } catch (Exception e) {
                break;
            }
        }
    }
}


/*
2000 / 4
 */
