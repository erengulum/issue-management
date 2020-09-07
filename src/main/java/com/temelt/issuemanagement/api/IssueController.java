package com.temelt.issuemanagement.api;

import com.temelt.issuemanagement.dto.IssueDto;
import com.temelt.issuemanagement.service.impl.IssueServiceImpl;
import com.temelt.issuemanagement.util.ApiPaths;
import com.temelt.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
public class IssueController {

    //Client won't be able to see Entity objects. We are going to return DTO objects instead of Entity objects:
    //Entity-DTO transformations is handled on the Service Layer

    private final IssueServiceImpl issueServiceImpl;
    //There is no need for @Autowired annotation since Spring supports Constructor injection without annotation
    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }


    //Return entity will be a TPage(created inside "util" package)
    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }



    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id) {
        IssueDto issue = issueServiceImpl.getById(id);
        return ResponseEntity.ok(issue); //.ok = HTTP-200 - Successfull
    }


    @PostMapping //no specific path for this mapping
    @ApiOperation(value = "Create Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issue) { //@Valid means it cant'be null
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }
    //@RequestBody -> for JSON type that client send to the Server
    //@PathVariable -> for paths
    //@Valid -> Validation of the RequestBody sections


    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody IssueDto issue) {
        return ResponseEntity.ok(issueServiceImpl.update(id, issue));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }
}
