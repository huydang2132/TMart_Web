package com.project.tmartweb.services.cart;

import com.project.tmartweb.domain.dtos.CartDTO;
import com.project.tmartweb.domain.entities.Cart;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.Pagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.repositories.CartRepository;
import com.project.tmartweb.services.product.IProductService;
import com.project.tmartweb.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final IUserService userService;
    private final IProductService productService;
    private final ModelMapper mapper;

    @Override
    public Cart insert(CartDTO cartDTO) {
        Cart cart = mapper.map(cartDTO, Cart.class);
        User user = userService.getById(cartDTO.getUserId());
        Product product = productService.getById(cartDTO.getProductId());
        cart.setUser(user);
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart update(UUID id, CartDTO cartDTO) {
        Cart cart = getById(id);
        mapper.map(cartDTO, cart);
        User user = userService.getById(cartDTO.getUserId());
        Product product = productService.getById(cartDTO.getProductId());
        cart.setUser(user);
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public PaginationDTO<Cart> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(cartRepository.findAll(), null);
        }
        BasePagination<Cart, CartRepository> basePagination = new BasePagination<>(cartRepository);
        PaginationDTO<Cart> pageRequest = basePagination.paginate(page, perPage);
        return new PaginationDTO<>(pageRequest.getData(), pageRequest.getPagination());
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Giỏ hàng không tồn tại!", "Cart not found"));
    }

    @Override
    public PaginationDTO<Cart> getAllByUser(UUID userId, Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(cartRepository.findAllByUser_Id(userId), null);
        }
        Page<Cart> pageRequest = cartRepository.findAllByUser_Id(userId, PageRequest.of(page, perPage, Sort.by("createdAt").descending()));
        Pagination pagination = new Pagination(page, perPage, pageRequest.getTotalPages() - 1, pageRequest.getTotalElements());
        return new PaginationDTO<>(pageRequest.getContent(), pagination);
    }

    @Override
    public List<Cart> insertMultiple(List<CartDTO> cartDTOS) {
        return List.of();
    }

    @Override
    public List<Cart> updateMultiple(List<CartDTO> cartDTOS) {
        return List.of();
    }

    @Override
    public int deleteMultiple(List<UUID> ids) {
        int count = 0;
        for (UUID id : ids) {
            Cart cart = getById(id);
            delete(cart);
            count++;
        }
        return count;
    }
}
