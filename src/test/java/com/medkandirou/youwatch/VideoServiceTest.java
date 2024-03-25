package com.medkandirou.youwatch;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.video.*;
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
class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VideoService videoService;

    @Test
    void findById_existingId_videoFound() {
        // Arrange
        Long id = 1L;
        Video video = new Video();
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));
        when(modelMapper.map(video, VideoDTOres.class)).thenReturn(new VideoDTOres());

        // Act
        VideoDTOres result = videoService.findById(id);

        // Assert
        assertNotNull(result);
        verify(videoRepository).findById(id);
        verify(modelMapper).map(video, VideoDTOres.class);
    }

    @Test
    void findById_nonExistingId_resourceNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;
        when(videoRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> videoService.findById(id));
        verify(videoRepository).findById(id);
        verifyNoInteractions(modelMapper);
    }

    @Test
    void findAll_existingVideos_returnListOfVideos() {
        // Arrange
        List<Video> videos = Arrays.asList(new Video(), new Video());
        when(videoRepository.findAll()).thenReturn(videos);
        when(modelMapper.map(any(), eq(VideoDTOres.class))).thenReturn(new VideoDTOres());

        // Act
        List<VideoDTOres> result = videoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(videos.size(), result.size());
        verify(videoRepository).findAll();
        verify(modelMapper, times(videos.size())).map(any(), eq(VideoDTOres.class));
    }

    @Test
    void save_validVideoDTO_entitySavedAndMappedToDTO() {
        // Arrange
        VideoDTOreq videoDTOreq = new VideoDTOreq();
        videoDTOreq.setChannelId(1L);
        videoDTOreq.setCategoryId(1);
        Channel channel = new Channel();
        Category category = new Category();
        Video video = new Video();
        when(modelMapper.map(videoDTOreq, Video.class)).thenReturn(video);
        when(channelRepository.findById(videoDTOreq.getChannelId())).thenReturn(Optional.of(channel));
        when(categoryRepository.findById(videoDTOreq.getCategoryId())).thenReturn(Optional.of(category));
        when(videoRepository.save(video)).thenReturn(video);
        when(modelMapper.map(video, VideoDTOres.class)).thenReturn(new VideoDTOres());

        // Act
        VideoDTOres result = videoService.save(videoDTOreq);

        // Assert
        assertNotNull(result);
        verify(modelMapper).map(videoDTOreq, Video.class);
        verify(channelRepository).findById(videoDTOreq.getChannelId());
        verify(categoryRepository).findById(videoDTOreq.getCategoryId());
        verify(videoRepository).save(video);
        verify(modelMapper).map(video, VideoDTOres.class);
        assertEquals(LocalDateTime.now().getDayOfYear(), video.getDatePosting().getDayOfYear()); // Check if date posting is set to current date
    }

    @Test
    void deleteById_existingId_videoDeletedAndMappedToDTO() {
        // Arrange
        Long id = 1L;
        Video video = new Video();
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));
        doNothing().when(videoRepository).deleteById(id);
        when(modelMapper.map(video, VideoDTOreq.class)).thenReturn(new VideoDTOreq());

        // Act
        VideoDTOreq result = videoService.deleteById(id);

        // Assert
        assertNotNull(result);
        verify(videoRepository).findById(id);
        verify(videoRepository).deleteById(id);
        verify(modelMapper).map(video, VideoDTOreq.class);
    }

    @Test
    void deleteById_nonExistingId_resourceNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;
        when(videoRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> videoService.deleteById(id));
        verify(videoRepository).findById(id);
        verifyNoInteractions(modelMapper);
    }
}
