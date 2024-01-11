package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.repository.EPRepository;
import org.example.server.service.EPService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Primary
@Service
public class EPServiceImpl implements EPService {
    private final EPRepository epRepository;

    @Override
    public EnterprisePoint getEnterprisePointById(int idEP) {
        return epRepository.findById(idEP).orElse(null);
    }
}
