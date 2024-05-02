package com.project.tmartweb.application.services.notification;

import com.project.tmartweb.application.repositories.NotificationRepository;
import com.project.tmartweb.application.services.order_detail.IOrderDetailService;
import com.project.tmartweb.application.services.user.IUserService;
import com.project.tmartweb.config.exceptions.NotFoundException;
import com.project.tmartweb.domain.dtos.NotificationDTO;
import com.project.tmartweb.domain.entities.Notification;
import com.project.tmartweb.domain.entities.OrderDetail;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;
    private final IUserService userService;
    private final IOrderDetailService orderDetailService;
    private final ModelMapper mapper;

    @Override
    public Notification insert(NotificationDTO notificationDTO) {
        Notification notification = mapper.map(notificationDTO, Notification.class);
        User user = userService.getById(notificationDTO.getUserId());
        OrderDetail orderDetail = orderDetailService.getById(notificationDTO.getOrderDetailId());
        notification.setUser(user);
        notification.setOrderDetail(orderDetail);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(UUID id, NotificationDTO notificationDTO) {
        Notification notification = getById(id);
        mapper.map(notificationDTO, notification);
        User user = userService.getById(notificationDTO.getUserId());
        OrderDetail orderDetail = orderDetailService.getById(notificationDTO.getOrderDetailId());
        notification.setUser(user);
        notification.setOrderDetail(orderDetail);
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public PaginationDTO<Notification> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(notificationRepository.findAll(), null);
        }
        BasePagination<Notification, NotificationRepository> basePagination = new BasePagination<>(notificationRepository);
        return basePagination.paginate(page, perPage);
    }

    @Override
    public Optional<Notification> findById(UUID id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Thông báo không tồn tại!", "Notification not found!"));
    }

    @Override
    public Notification readNotification(UUID id) {
        Notification notification = getById(id);
        notification.setRead(Boolean.TRUE);
        return notificationRepository.save(notification);
    }

    @Override
    public PaginationDTO<Notification> getAllByUser(UUID userId, Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(notificationRepository.findAllByUserId(userId), null);
        }
        BasePagination<Notification, NotificationRepository> basePagination = new BasePagination<>(notificationRepository);
        Page<Notification> pageNotifications = notificationRepository.findAllByUserId(userId,
                PageRequest.of(page, perPage, Sort.by("createdAt").descending()));
        return basePagination.paginate(page, perPage, pageNotifications);
    }
}
