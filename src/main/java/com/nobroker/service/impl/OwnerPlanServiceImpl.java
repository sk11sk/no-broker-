package com.nobroker.service.impl;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.repository.OwnerPlanRepository;
import com.nobroker.service.OwnerPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerPlanServiceImpl implements OwnerPlanService {

    private ModelMapper modelMapper;

    private  OwnerPlanRepository ownerPlanRepository;

    public OwnerPlanServiceImpl(OwnerPlanRepository ownerPlanRepository, ModelMapper  modelMapper ) {
        this.ownerPlanRepository = ownerPlanRepository;
        this.modelMapper= modelMapper;
    }



    @Override
    public OwnerPlanDto createOwnerPlans(OwnerPlanDto ownerPlanDto) {
        OwnerPlan ownerPlan = mapToEntity(ownerPlanDto);

        OwnerPlan  savedOwnerPlan= ownerPlanRepository.save(ownerPlan);

        OwnerPlanDto dto = mapToDto(savedOwnerPlan);
        return  dto;


    }

    OwnerPlan mapToEntity(OwnerPlanDto  ownerPlanDto){    // returns entity class(OwnerPlan) object
        OwnerPlan ownerPlan = modelMapper.map(ownerPlanDto, OwnerPlan.class); //  modelMapper.map(from , to )
         return ownerPlan;

    }

    OwnerPlanDto mapToDto(OwnerPlan ownerPlan) {    // returns OwnerPlanDto class object
        OwnerPlanDto ownerPlanDto = modelMapper.map(ownerPlan, OwnerPlanDto.class);
        return ownerPlanDto;

    }





    @Override
    public List<OwnerPlanDto> getAllOwnerPlans() {
        List<OwnerPlan> ownerPlans = ownerPlanRepository.findAll();
        List<OwnerPlanDto> ownerPlanDtos = ownerPlans.stream().map(i -> mapToDto(i)).collect(Collectors.toList());
        return ownerPlanDtos;
    }
}
