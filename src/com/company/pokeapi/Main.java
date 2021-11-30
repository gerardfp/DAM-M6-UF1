package com.company.pokeapi;

import java.util.List;

class Result {
    public List<Pokemon> documents;
}

class Pokemon {
    public String name;
    public Fields fields;
    public String createTime;
    public String updateTime;
}

class Fields {
    public StringValue imagen, nombre, elemento;

}

class StringValue {
    public String stringValue;
}

public class Main {
}

/*

{
  "documents": [
    {
      "name": "projects/pruebaretrofit-668f6/databases/(default)/documents/Albums/BMHDTNTPkj8NFBdCqOOz",
      "fields": {
        "imagen": {
          "stringValue": "https://firebasestorage.googleapis.com/v0/b/pruebaretrofit-668f6.appspot.com/o/charmander.png?alt=media&token=403ae552-06ed-4e60-a292-c894964adc49"
        },
        "nombre": {
          "stringValue": "Charmander"
        },
        "elemento": {
          "stringValue": "fuego"
        }
      },
      "createTime": "2021-11-18T14:40:08.509574Z",
      "updateTime": "2021-11-18T14:48:20.873481Z"
    },
    {
      "name": "projects/pruebaretrofit-668f6/databases/(default)/documents/Albums/CXchsBgE9d0qTP3IPvOz",
      "fields": {
        "imagen": {
          "stringValue": "https://firebasestorage.googleapis.com/v0/b/pruebaretrofit-668f6.appspot.com/o/pikachu.png?alt=media&token=9bac1cd4-fc30-4288-afec-ec94e4449aee"
        },
        "nombre": {
          "stringValue": "Pikachu"
        },
        "elemento": {
          "stringValue": "rayo"
        }
      },
      "createTime": "2021-11-18T14:39:24.404009Z",
      "updateTime": "2021-11-18T14:46:23.705027Z"
    },
    {
      "name": "projects/pruebaretrofit-668f6/databases/(default)/documents/Albums/a87JQRVJUuy42MgJ8ZEK",
      "fields": {
        "imagen": {
          "stringValue": "https://firebasestorage.googleapis.com/v0/b/pruebaretrofit-668f6.appspot.com/o/bulbasaur.png?alt=media&token=ec5c88b0-742e-48cf-9d01-5673a351b53a"
        },
        "nombre": {
          "stringValue": "Bulbasaur"
        },
        "elemento": {
          "stringValue": "agua"
        }
      },
      "createTime": "2021-11-18T14:40:39.511220Z",
      "updateTime": "2021-11-18T14:47:21.530638Z"
    }
  ]
}


 */