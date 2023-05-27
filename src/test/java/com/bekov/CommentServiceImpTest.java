package com.bekov.service.impl;

import com.bekov.model.Comment;
import com.bekov.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommentServiceImpTest {
    @Mock
    private CommentRepository mockCommentRepository;

    private CommentServiceImp commentServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        commentServiceImp = new CommentServiceImp(mockCommentRepository);
    }

    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        when(mockCommentRepository.saveAndFlush(comment)).thenReturn(comment);

        Comment result = commentServiceImp.save(comment);

        assertEquals(comment, result);
        verify(mockCommentRepository).saveAndFlush(comment);
    }
}
