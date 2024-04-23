package com.project.tmartweb.services.feedback;

import com.project.tmartweb.domain.dtos.FeedbackDTO;
import com.project.tmartweb.domain.entities.Feedback;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IFeedbackService extends IBaseService<Feedback, FeedbackDTO, UUID> {
    PaginationDTO<Feedback> getAllByProduct(UUID id, Integer page, Integer perPage);
}
