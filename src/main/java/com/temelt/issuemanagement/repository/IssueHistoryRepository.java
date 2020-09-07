package com.temelt.issuemanagement.repository;

import com.temelt.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data's JPA repository will be used to retrieve data from database
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {

}
