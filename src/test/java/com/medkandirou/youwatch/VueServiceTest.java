package com.medkandirou.youwatch;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import com.medkandirou.youwatch.vue.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VueServiceTest {

    @Mock
    private VueRepository vueRepository;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VueService vueService;

    @Test
    void findAll_existingVues_returnListOfVues() {
        // Arrange
        List<Vue> vues = Arrays.asList(new Vue(), new Vue());
        when(vueRepository.findAll()).thenReturn(vues);
        when(modelMapper.map(any(), eq(VueDTOres.class))).thenReturn(new VueDTOres());

        // Act
        List<VueDTOres> result = vueService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(vues.size(), result.size());
        verify(vueRepository).findAll();
        verify(modelMapper, times(vues.size())).map(any(), eq(VueDTOres.class));
    }

    @Test
    void save_validVueDTO_entitySavedAndMappedToDTO() {
        // Arrange
        VueDTOreq vueDTOreq = new VueDTOreq();
        Video_channel_Id videoChannelId = new Video_channel_Id();
        videoChannelId.setChannel(new Channel());
        videoChannelId.setVideo(new Video());
        vueDTOreq.setVideo_channel_id(videoChannelId);
        Vue vue = new Vue();
        when(modelMapper.map(vueDTOreq, Vue.class)).thenReturn(vue);
        when(channelRepository.findById(vueDTOreq.getVideo_channel_id().getChannel().getId())).thenReturn(Optional.of(new Channel()));
        when(videoRepository.findById(vueDTOreq.getVideo_channel_id().getVideo().getId())).thenReturn(Optional.of(new Video()));
        when(vueRepository.save(vue)).thenReturn(vue);
        when(modelMapper.map(vue, VueDTOres.class)).thenReturn(new VueDTOres());

        // Act
        VueDTOres result = vueService.save(vueDTOreq);

        // Assert
        assertNotNull(result);
        verify(modelMapper).map(vueDTOreq, Vue.class);
        verify(channelRepository).findById(vueDTOreq.getVideo_channel_id().getChannel().getId());
        verify(videoRepository).findById(vueDTOreq.getVideo_channel_id().getVideo().getId());
        verify(vueRepository).save(vue);
        verify(modelMapper).map(vue, VueDTOres.class);
    }

}
