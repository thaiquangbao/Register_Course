package com.iuh.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Builder
@Table(name = "fields")
public class Field implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int fieldId;
    public String fieldName;
    @ManyToMany
    @JoinTable(
            name = "field_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id"))
    public Set<Course> courseId;

}
