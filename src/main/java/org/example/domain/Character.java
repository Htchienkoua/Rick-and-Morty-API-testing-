package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Character {
//.id(1L)
//                .name("Rick Sanchez")
//                .gender("Male")
//                .type("")
//                .status("Alive")
//                .species("Human")
//                .build();

    public Long id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Origin origin;
    public Location location;
    public List<String> Episode;


    //method to convert the json object code to string type for prints and display
    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) && Objects.equals(name, character.name) && Objects.equals(status, character.status) && Objects.equals(species, character.species) && Objects.equals(type, character.type) && Objects.equals(gender, character.gender);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
