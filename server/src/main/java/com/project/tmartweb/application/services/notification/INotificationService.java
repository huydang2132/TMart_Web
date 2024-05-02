package com.project.tmartweb.application.services.notification;

import com.project.tmartweb.application.services.base.IBaseService;
import com.project.tmartweb.domain.dtos.NotificationDTO;
import com.project.tmartweb.domain.entities.Notification;
import com.project.tmartweb.domain.paginate.PaginationDTO;

import java.util.UUID;

public interface INotificationService extends IBaseService<Notification, NotificationDTO, UUID> {
    Notification readNotification(UUID id);

    PaginationDTO<Notification> getAllByUser(UUID userId, Integer page, Integer perPage);
}
