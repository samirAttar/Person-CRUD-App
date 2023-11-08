# Person-with-CRUD
Test repository
In This repository we did conversion of JSON into XML.

Implimentation to convert json to xml


1. Add jkson json processing dependency

 <!-- Jackson for JSON processing -->
    <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-xml</artifactId>
    </dependency>
</dependencies>


2. create DTO class with respect to entity
ex.

class PersonXml {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private String city;
 
//constructor and getters setters


3. Modify service method as 

 public String savexmlPerson(Person person) {
        Person savedPerson = personDAO.save(person);

        // Convert the saved person data to XML
        try {
            PersonXml personXml = new PersonXml(savedPerson.getId(), savedPerson.getName(), savedPerson.getCity());
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(personXml);
            return xml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	make sure for XmlMapper impoert correct as import com.fasterxml.jackson.dataformat.xml.XmlMapper;

4. Modify controller as

@PostMapping("/savePerson")
    public ResponseEntity<String> savexmlPerson(@RequestBody Person person) {
        String xml = personService.savexmlPerson(person);
        return new ResponseEntity<>(xml, HttpStatus.CREATED);
    }

In postman when you test the api in return you will get xml formate response.
