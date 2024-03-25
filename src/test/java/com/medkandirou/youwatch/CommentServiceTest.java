package com.medkandirou.youwatch;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.comment.*;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentService commentService;

    @Test
    void findAll_existingComments_returnListOfComments() {
        // Arrange
        List<Comment> comments = Arrays.asList(new Comment(), new Comment());
        when(commentRepository.findAll()).thenReturn(comments);
        when(modelMapper.map(any(), eq(CommentDTOres.class))).thenReturn(new CommentDTOres());

        // Act
        List<CommentDTOres> result = commentService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(comments.size(), result.size());
        verify(commentRepository).findAll();
        verify(modelMapper, times(comments.size())).map(any(), eq(CommentDTOres.class));
    }

    @Test
    void save_validCommentDTO_entitySavedAndMappedToDTO() {
        // Arrange
        CommentDTOreq commentDTOreq = new CommentDTOreq();
        commentDTOreq.setChannelId(1L);
        commentDTOreq.setVideoId(1L);
        Channel channel = new Channel();
        Video video = new Video();
        Comment comment = new Comment();
        when(modelMapper.map(commentDTOreq, Comment.class)).thenReturn(comment);
        when(channelRepository.findById(commentDTOreq.getChannelId())).thenReturn(Optional.of(channel));
        when(videoRepository.findById(commentDTOreq.getVideoId())).thenReturn(Optional.of(video));
        when(commentRepository.save(comment)).thenReturn(comment);
        when(modelMapper.map(comment, CommentDTOres.class)).thenReturn(new CommentDTOres());

        // Act
        CommentDTOres result = commentService.save(commentDTOreq);

        // Assert
        assertNotNull(result);
        verify(modelMapper).map(commentDTOreq, Comment.class);
        verify(channelRepository).findById(commentDTOreq.getChannelId());
        verify(videoRepository).findById(commentDTOreq.getVideoId());
        verify(commentRepository).save(comment);
        verify(modelMapper).map(comment, CommentDTOres.class);
        assertNotNull(comment.getVideo_channel_id());
        assertEquals(LocalDateTime.now().getDayOfYear(), comment.getDatePosting().getDayOfYear()); // Check if date posting is set to current date
    }

}