package com.graduationproject.graduationproject.service;

import com.graduationproject.graduationproject.entity.Position;
import com.graduationproject.graduationproject.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll(){
        return positionRepository.findAll();
    }
}
