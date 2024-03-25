package com.medkandirou.youwatch;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.channel.*;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ChannelService channelService;

    @Test
    void findAll_existingChannels_returnListOfChannels() {
        // Arrange
        List<Channel> channels = Arrays.asList(new Channel(), new Channel());
        when(channelRepository.findAll()).thenReturn(channels);
        when(modelMapper.map(any(), eq(ChannelDTOres.class))).thenReturn(new ChannelDTOres());

        // Act
        List<ChannelDTOres> result = channelService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(channels.size(), result.size());
        verify(channelRepository).findAll();
        verify(modelMapper, times(channels.size())).map(any(), eq(ChannelDTOres.class));
    }

    @Test
    void save_validChannelDTO_entitySavedAndMappedToDTO() {
        // Arrange
        ChannelDTOreq channelDTOreq = new ChannelDTOreq();
        Channel channel = new Channel();
        when(modelMapper.map(channelDTOreq, Channel.class)).thenReturn(channel);
        when(channelRepository.save(channel)).thenReturn(channel);
        when(modelMapper.map(channel, ChannelDTOres.class)).thenReturn(new ChannelDTOres());

        // Act
        ChannelDTOres result = channelService.save(channelDTOreq);

        // Assert
        assertNotNull(result);
        verify(modelMapper).map(channelDTOreq, Channel.class);
        verify(channelRepository).save(channel);
        verify(modelMapper).map(channel, ChannelDTOres.class);
    }



    @Test
    void deleteById_existingId_channelDeletedAndMappedToDTO() {
        // Arrange
        Long id = 1L;
        Channel channel = new Channel();
        when(channelRepository.findById(id)).thenReturn(Optional.of(channel));
        doNothing().when(channelRepository).deleteById(id);
        when(modelMapper.map(channel, ChannelDTOreq.class)).thenReturn(new ChannelDTOreq());

        // Act
        ChannelDTOreq result = channelService.deleteById(id);

        // Assert
        assertNotNull(result);
        verify(channelRepository).findById(id);
        verify(channelRepository).deleteById(id);
        verify(modelMapper).map(channel, ChannelDTOreq.class);
    }

    @Test
    void deleteById_nonExistingId_resourceNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;
        when(channelRepository.findById(id)).thenReturn(Optional.empty());
        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> channelService.deleteById(id));
        verify(channelRepository).findById(id);
        verifyNoInteractions(modelMapper);
    }

    @Test
    void statistiques_returnStatisticsList() {
        // Arrange
        List<Channel> channels = Arrays.asList(new Channel(), new Channel());
        List<Category> categories = Arrays.asList(new Category(), new Category());
        List<Video> videos = Arrays.asList(new Video(), new Video(), new Video());

        when(channelRepository.findAll()).thenReturn(channels);
        when(videoRepository.findAll()).thenReturn(videos);
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Integer> result = channelService.statistiques();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(channels.size(), result.get(0));
        assertEquals(videos.size(), result.get(1));
        assertEquals(categories.size(), result.get(2));
        verify(channelRepository).findAll();
        verify(videoRepository).findAll();
        verify(categoryRepository).findAll();
    }
}
