package com.company.publicapis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

class Respuesta {
    public int resultCount;
    public List<Contenido> results;
}

class Contenido {
    public String artistName;
    public String description;
}

public class Main {

    public static void main(String[] args) throws IOException {
        
        ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String url = "https://itunes.apple.com/search?term=%s";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\033[32mQue quieres buscar?\033[0m");
            String termino = scanner.nextLine();

            Respuesta respuesta = objectMapper.readValue(new URL(String.format(url, termino)), Respuesta.class);

            for (Contenido contenido: respuesta.results ) {
                System.out.println(contenido.artistName);
                System.out.println("\t"+contenido.description);
            }
        }
    }
}