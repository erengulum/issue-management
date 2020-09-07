package com.temelt.issuemanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "issue")
@Data
@NoArgsConstructor
@AllArgsConstructor //All args kullanıyorsan mutlaka NoArgs da kullan cünkü ileride baska bir developer argümansız olarak bir obje olusturmak isteyebilirz
@ToString
@EqualsAndHashCode
public class Issue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Id will be generated automatically by Hibernate/Persistence
    private Long id;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "details", length = 4000)
    private String details;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP) //It will stamp the current time
    private Date date;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING) //IssueStatus isimli bir Enumerated tipi yaratıstık.Entity paketinin icinde: Issue'nın son durumları var orada
    private IssueStatus issueStatus;

    //Bi-directional mapping
    @JoinColumn(name = "assignee_user_id") //Bu kolon sayesinde User ile Issue tablolarını joinliyor
    @ManyToOne(optional = true, fetch = FetchType.LAZY) //optional=true. It is optional to assigne a user/developer to this Issue
    private User assignee;

    //One way mapping
    @JoinColumn(name = "project_id") //Thx to @Id annotation & id column of the project, we can combine them
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Project project; //It states that which Issue belongs to which project

}
