package com.jigglink.badge.service.model.service;

import com.jigglink.badge.service.model.DTO.BadgeDTO;
import com.jigglink.badge.service.model.entity.Badge;
import com.jigglink.badge.service.model.exception.BadgeNotFoundException;
import com.jigglink.badge.service.model.repository.BadgeRepository;
import com.jigglink.badge.service.model.service.interfaces.BadgeServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeService implements BadgeServiceInterface {
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    private ModelMapper badgeModelMapper;
    @Autowired
    private RestTemplate badgeRestTemplate;

    @Override
    public BadgeDTO createBadgeBy(String studentUsername, BadgeDTO newBadge) {
        BadgeDTO badgeToSave = setNewBadgeData(studentUsername, newBadge);
        return getBadgeDTOFromEntity(badgeRepository.save(getBadgeEntityFromDTO(badgeToSave)));
    }

    @Override
    public List<BadgeDTO> getBadgesBy(String username) {
        return null;
    }

    @Override
    public List<BadgeDTO> getBadges() {
        return badgeRepository.findAll().stream().map(this::getBadgeDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public BadgeDTO getBadgeBy(String studentUsername) {
        return badgeRepository.findById(studentUsername).map(this::getBadgeDTOFromEntity)
                .orElseThrow(() -> new BadgeNotFoundException("The badge does not exist"));
    }

    @Override
    public void deleteBadge(String studentUsername) {
        badgeRepository.deleteBadgesByStudentUsername(studentUsername);
    }

    private BadgeDTO getBadgeDTOFromEntity(Badge badge) {
        return badgeModelMapper.map(badge, BadgeDTO.class);
    }

    private Badge getBadgeEntityFromDTO(BadgeDTO badgeDTO) {
        return badgeModelMapper.map(badgeDTO, Badge.class);
    }

    private BadgeDTO setNewBadgeData(String username, BadgeDTO badgeToSet){
        return BadgeDTO.builder().name(badgeToSet.getName()).studentUsername(username).coachMessage(badgeToSet.getCoachMessage()).icon(badgeToSet.getIcon()).build();
    }
}
