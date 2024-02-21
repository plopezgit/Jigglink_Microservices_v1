package com.jigglink.badge.service.model.service.interfaces;

import com.jigglink.badge.service.model.DTO.BadgeDTO;

import java.util.List;

public interface BadgeServiceInterface {
    public BadgeDTO createBadgeBy(String studentUsername, BadgeDTO newBadge);
    public List<BadgeDTO> getBadgesBy(String studentUsername);
    public List<BadgeDTO> getBadges();
    public BadgeDTO getBadgeBy(String studentUsername);
    public void deleteBadge(String studentUsername);
}
