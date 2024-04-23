package com.project.tmartweb.services.notification;

import com.project.tmartweb.domain.dtos.NotificationDTO;
import com.project.tmartweb.domain.entities.Notification;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface INotificationService extends IBaseService<Notification, NotificationDTO, UUID> {
    Notification readNotification(UUID id);

    PaginationDTO<Notification> getAllByUser(UUID userId, Integer page, Integer perPage);
}
