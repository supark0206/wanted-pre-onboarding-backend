package com.assignment.recurit.service.impl;

import com.assignment.recurit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyServiceImpl implements CompanyService {

}
