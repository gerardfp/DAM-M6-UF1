package com.company.newsfeed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

class Feeds {
    public List<Feed> feeds;

    public Feeds(List<Feed> feeds) {
        this.feeds = feeds;
    }
}

class Feed {
    public String id;
    public String title;
    public String description;
    public String url;
    public List<Author> authors;
    public List<Item> items;

    public Feed(String id, String title, String description, String url, List<Author> authors, List<Item> items) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.authors = authors;
        this.items = items;
    }
}

class Author {
    public String name;
    public String url;

    public Author(String name, String url) {
        this.name = name;
        this.url = url;
    }
}

class Item {
    public String id;
    public String description;
    public String url;
    public List<Author> authors;
    public List<Attachment> attachments;

    public Item(String id, String description, String url, List<Author> authors, List<Attachment> attachments) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.authors = authors;
        this.attachments = attachments;
    }
}

class Attachment {
    public String url;
    public String mimetype;

    public Attachment(String url, String mimetype) {
        this.url = url;
        this.mimetype = mimetype;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {

        Feeds feeds = new Feeds(
                List.of(
                        new Feed(
                                "21345",
                                "News of the world feed",
                                "News from all the world",
                                "http://news.world/feed",
                                List.of(
                                        new Author(
                                                "John Smith",
                                                "http://jonhmisth.com")
                                ),
                                List.of(
                                        new Item(
                                                "45678",
                                                "Pictures and videos showing thousands of ice spheres that recently formed on the surface of Canada's Lake Manitoba have been widely shared online.",
                                                "https://twitter.com/strombo/status/1463671539451187202",
                                                List.of(
                                                        new Author(
                                                                "Sakshita Khosla",
                                                                "http://skshita.com"
                                                        )
                                                ),
                                                List.of(
                                                        new Attachment(
                                                                "https://twitter.com/strombo/1463671539451187202.jpg",
                                                                "image/jpg"
                                                        )
                                                )
                                        ),
                                        new Item(
                                                "98756",
                                                "The first real-world assessment of the effectiveness of Covaxin against symptomatic COVID-19 at least 14 days after second dose was 50%.",
                                                "https://www.thequint.com/amp/story/coronavirus/covaxin-50-effective-against-delta-variant",
                                                List.of(
                                                        new Author(
                                                                "Apaar Sharma",
                                                                "http://apaar.com"
                                                        )
                                                ),
                                                List.of(
                                                        new Attachment(
                                                                "https://www.thequint.com/3435532532.jpg",
                                                                "image/jpg"
                                                        )
                                                )
                                        )
                                )
                        ),
                        new Feed(
                                "765456",
                                "Sports feed",
                                "Sports news from all the world",
                                "http://news.sports/feed",
                                List.of(
                                        new Author(
                                                "Anant Kaur",
                                                "http://anantkaur.com"
                                        ),
                                        new Author(
                                                "Mahima Kharbanda",
                                                "http://mahima.com"
                                        )
                                ),
                                List.of(
                                        new Item(
                                                "45678",
                                                "Argentina and PSG forward Lionel Messi's",
                                                "https://www.latestly.com/sports/football/lionel-messis-son-thiagos",
                                                List.of(
                                                        new Author(
                                                                "Mahima Kharbanda",
                                                                "http://mahima.com"
                                                        )
                                                ),
                                                List.of(
                                                        new Attachment(
                                                                "https://www.latestly.com/1463671539451187202.jpg",
                                                                "image/jpg"
                                                        )
                                                )
                                        ),
                                        new Item(
                                                "98756",
                                                "The 2022 Winter Olympics is expected to go ahead smoothly and successfully despite the emergence of a new COVID-19 variant.",
                                                "https://www.reuters.com/lifestyle/sports/china-says-olympics-will-go",
                                                List.of(
                                                        new Author(
                                                                "Veer Badani",
                                                                "http://badani.ver"
                                                        )
                                                ),
                                                List.of(
                                                        new Attachment(
                                                                "https://www.reuters.com/3435532532.jpg",
                                                                "image/jpg"
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        for (Feed feed: feeds.feeds) {
            System.out.println("[" + feed.id + "] " + feed.title);
            System.out.println(feed.description);
            System.out.println(feed.url);

            System.out.println("\tAuthors: ");
            for (Author author:feed.authors) {
                System.out.println("\t\t- " + author.name + " ("+author.url+")");
            }

            System.out.println("\tItems: ");
            for (Item item: feed.items) {
                System.out.println("\t\t- [" + item.id + "] " + item.description);
                System.out.println("\t\t  " + item.url);
                System.out.println("\t\t\tAuthors:");
                for (Author author: item.authors) {
                    System.out.println("\t\t\t\t- " + author.name + " ("+author.url+")");
                }
                System.out.println("\t\t\tAttachments:");
                for (Attachment attachment:item.attachments) {
                    System.out.println("\t\t\t\t- " + attachment.url + " (" + attachment.mimetype + ")");
                }
            }
        }


        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(Paths.get("news.json").toFile(), feeds);
    }
}
