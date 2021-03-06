package com.temelt.issuemanagement.service.impl;

import com.temelt.issuemanagement.dto.IssueDto;
import com.temelt.issuemanagement.entity.Issue;
import com.temelt.issuemanagement.repository.IssueRepository;
import com.temelt.issuemanagement.service.IssueService;
import com.temelt.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class IssueServiceImpl implements IssueService {

    //Repository will be injected for DB operations
    //ModelMapper will be injected for DTO-Entity transformations/mapping
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDto save(IssueDto issue) {
        // Bussiness Logic
        if (issue.getDate() == null) {
            throw new IllegalArgumentException("Issue Date cannot be null");
        }

        //DTO-Entity mapping->Transform IssueDTO to the Issue entity
        Issue issueEntity = modelMapper.map(issue, Issue.class);
        issueEntity = issueRepository.save(issueEntity);
        issue.setId(issueEntity.getId());
        return issue;
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id); //Buradan Entity gelecek.Aşağıda DTO'ya cevirip return edecegiz
        return modelMapper.map(issue, IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDto> response = new TPage<IssueDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
        return response;
    }

    @Override
    public Boolean delete(Long issueId) {
        issueRepository.deleteById(issueId);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {
        return null;
    }

}
