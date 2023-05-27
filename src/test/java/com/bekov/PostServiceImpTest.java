package com.bekov;

import com.bekov.model.Post;
import com.bekov.model.User;
import com.bekov.repository.PostRepository;
import com.bekov.service.impl.PostServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostServiceImpTest {

    @Mock
    private PostRepository mockPostRepository;

    private PostServiceImp postServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postServiceImp = new PostServiceImp(mockPostRepository);
    }

    @Test
    public void testFindForId() {
        Long id = 1L;
        Post post = new Post();
        when(mockPostRepository.findById(id)).thenReturn(Optional.of(post));

        Optional<Post> result = postServiceImp.findForId(id);

        assertEquals(Optional.of(post), result);
        verify(mockPostRepository).findById(id);
    }

    @Test
    public void testSave() {
        Post post = new Post();
        when(mockPostRepository.saveAndFlush(post)).thenReturn(post);

        Post result = postServiceImp.save(post);

        assertEquals(post, result);
        verify(mockPostRepository).saveAndFlush(post);
    }

    @Test
    public void testFindByUserOrderedByDatePageable() {
        User user = new User();
        int page = 1;
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<Post> expectedPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        when(mockPostRepository.findByUserOrderByCreateDateDesc(user, pageRequest)).thenReturn(expectedPage);

        Page<Post> result = postServiceImp.findByUserOrderedByDatePageable(user, page);

        assertEquals(expectedPage, result);
        verify(mockPostRepository).findByUserOrderByCreateDateDesc(user, pageRequest);
    }

    @Test
    public void testFindAllOrderedByDatePageable() {
        int page = 1;
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<Post> expectedPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        when(mockPostRepository.findAllByOrderByCreateDateDesc(pageRequest)).thenReturn(expectedPage);

        Page<Post> result = postServiceImp.findAllOrderedByDatePageable(page);

        assertEquals(expectedPage, result);
        verify(mockPostRepository).findAllByOrderByCreateDateDesc(pageRequest);
    }

    @Test
    public void testDelete() {
        Post post = new Post();

        postServiceImp.delete(post);

        verify(mockPostRepository).delete(post);
    }
}
