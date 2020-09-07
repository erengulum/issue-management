package com.temelt.issuemanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname", length = 100, unique = true)
    private String username;

    @Column(name = "pwd", length = 200)
    private String password;

    @Column(name = "name_surname", length = 200)
    private String nameSurname;

    @Column(name = "email", length = 100)
    private String email;

    //Bi-directional mapping
    @JoinColumn(name = "assignee_user_id")
    @OneToMany(fetch = FetchType.LAZY) //Lazy fetch means it will be bring only if there is a call for this column.
    private List<Issue> issues;
}
