package com.company.agenda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Contact {
    public String name, email, phone;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

class Agenda {
    public List<Contact> contacts = new ArrayList<>();
}

public class Main {
    
    static ObjectMapper objectMapper = new ObjectMapper();
    static String jsonFile = "/tmp/agenda.json";
    static Agenda agenda;

    public static void main(String[] args) throws IOException {

        try {
            agenda = objectMapper.readValue(Paths.get(jsonFile).toFile(), Agenda.class);
        } catch (Exception e) {
            agenda = new Agenda();
        }

        add("James Gosling", "6666","james@java.net");
        add("Anders Hejlsberg", "6666","anders@typescript.com");
        add("Chris Lattner", "6666","chris@swift.com");
        add("Brendan Eich", "6666","brendan@javascript.org");
        add("Bjarne Stroustrup", "6666","bjarne@cplusplus.com");
        add("Guido van Rossum", "6666","guido@python.org");

        list();

        del("Bjarne Stroustrup");

        list();

        search("chris@swift.com");

        objectMapper.writeValue(Paths.get(jsonFile).toFile(), agenda);
    }

    static void add(String name, String phone, String email) {
        agenda.contacts.removeIf(c -> c.name.equals(name));
        agenda.contacts.add(new Contact(name, email,phone ));
    }

    static void del(String name) {
        agenda.contacts.removeIf(c -> c.name.equals(name));
    }

    static void list() {
        System.out.println("\n\n Contact list");
        agenda.contacts.forEach(System.out::println);
    }

    static void search(String email){
        System.out.println("\n\n Search: " + email);
        agenda.contacts.stream().filter(c -> c.email.equals(email)).forEach(System.out::println);
    }
}
