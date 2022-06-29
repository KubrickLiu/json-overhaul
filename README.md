![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.apache.maven/apache-maven)
## What this is

This is an SDK component for JSON structure reconstruction. In order to avoid writing too much code in the process of JSON structure conversion, which leads to very redundancy of the project as a whole, an interpreted protocol is implemented here to convert JSON structure and content according to the protocol content.

## Quick-start
Maven:

```xml
<dependency>
    <groupId>io.github.MagicLiuJiayu</groupId>
    <artifactId>json-overhaul</artifactId>
    <version>1.0.1</version>
</dependency>
```

I use the following cases as quick examples:
### Function 1 : @ (assignment symbol)
| @Symbol connected to **new_word** and **word**, mean that **word** in the original JSON structure is assigned to **new_word** in the new structure:

* config :

```config
{
  "new_word@word": ""
}
```
* raw struct :

```raw
{
  "word": "hello world"
}
```

* code :

```
 public static void main(String[] args) {
        String config = "{\"new_word@word\":\"\"}";
        String raw = "{\"word\":\"hello world\"}";

        Overhauler overhauler = Overhauler.build(config);
        JSONObject ret = overhauler.parse(raw);
        System.out.println(ret);
    }
```
* result :

```
{
    "new_word":"hello world"
}
```

### Function 2 : # (array parser symbol)
| # symbol is to parse the array, disassemble each item of the array, and then process JSON objects;

* In the following cases, up and array are hierarchical relationships, and **.** can be used to connect the two levels;

* config :

```
{
  "new_array#up.array": [
    {
      "new_word_a@a": "",
      "new_word_b@b": ""
    }
  ]
}
```

* raw struct :

```
{
  "up": {
    "array": [
      {
        "a": "wordA0",
        "b": "wordB0"
      },
      {
        "a": "wordA1",
        "b": "wordB1"
      }
    ]
  }
}
```

* code : The code is consistent with the logic of the first function. It is not written here;

* result :

```
{
    "new_array":[
        {
            "new_word_b":"wordB0",
            "new_word_a":"wordA0"
        },
        {
            "new_word_b":"wordB1",
            "new_word_a":"wordA1"
        }
    ]
}
```

### Function 3 : construct a new structure

* config:

```
{
  "new_structor_one": {
    "new_word_A@wordA": "",
    "new_word_B@wordB": ""
  },
  "new_structor_two": "second_value"
}
```