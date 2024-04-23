package com.project.tmartweb.services.feedback;

import com.project.tmartweb.domain.dtos.FeedbackDTO;
import com.project.tmartweb.domain.entities.Feedback;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.repositories.FeedbackRepository;
import com.project.tmartweb.services.product.IProductService;
import com.project.tmartweb.services.user.IUserService;
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
public class FeedbackService implements IFeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final IUserService userService;
    private final IProductService productService;
    private final BasePagination<Feedback, FeedbackRepository> basePagination;
    private final ModelMapper mapper;

    @Override
    public Feedback insert(FeedbackDTO feedbackDTO) {
        Feedback feedback = mapper.map(feedbackDTO, Feedback.class);
        User user = userService.getById(feedbackDTO.getUserId());
        Product product = productService.getById(feedbackDTO.getProductId());
        feedback.setUser(user);
        feedback.setProduct(product);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(UUID id, FeedbackDTO feedbackDTO) {
        Feedback feedback = getById(id);
        mapper.map(feedbackDTO, feedback);
        User user = userService.getById(feedbackDTO.getUserId());
        Product product = productService.getById(feedbackDTO.getProductId());
        feedback.setUser(user);
        feedback.setProduct(product);
        return feedbackRepository.save(feedback);
    }

    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    @Override
    public PaginationDTO<Feedback> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(feedbackRepository.findAll(), null);
        }
        return basePagination.paginate(page, perPage);
    }

    @Override
    public Optional<Feedback> findById(UUID id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Feedback getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Đánh giá không tồn tại!", "Feedback not found"));
    }

    @Override
    public PaginationDTO<Feedback> getAllByProduct(UUID id, Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(feedbackRepository.findAllByProductId(id), null);
        }
        Page<Feedback> feedbacks = feedbackRepository.findAllByProductId(id,
                PageRequest.of(page, perPage, Sort.by("createdAt").descending()));
        return basePagination.paginate(page, perPage, feedbacks);
    }
}
