package com.medkandirou.youwatch;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.reaction.*;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
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
class ReactionServiceTest {

    @Mock
    private ReactionRepository reactionRepository;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private VideoRepository videoRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ReactionService reactionService;

    @Test
    void findAll_existingReactions_returnListOfReactions() {
        List<Reaction> reactions = Arrays.asList(new Reaction(), new Reaction());
        when(reactionRepository.findAll()).thenReturn(reactions);
        when(modelMapper.map(any(), eq(ReactionDTOres.class))).thenReturn(new ReactionDTOres());

        List<ReactionDTOres> result = reactionService.findAll();

        assertNotNull(result);
        assertEquals(reactions.size(), result.size());
        verify(reactionRepository).findAll();
        verify(modelMapper, times(reactions.size())).map(any(), eq(ReactionDTOres.class));
    }

    @Test
    void save_validReactionDTO_entitySavedAndMappedToDTO() {
        ReactionDTOreq reactionDTOreq = new ReactionDTOreq();
        Video_channel_Id videoChannelId = new Video_channel_Id();
        videoChannelId.setChannel(new Channel());
        videoChannelId.setVideo(new Video());
        reactionDTOreq.setVideo_channel_id(videoChannelId);
        Reaction reaction = new Reaction();
        when(modelMapper.map(reactionDTOreq, Reaction.class)).thenReturn(reaction);
        when(channelRepository.findById(reactionDTOreq.getVideo_channel_id().getChannel().getId())).thenReturn(Optional.of(new Channel()));
        when(videoRepository.findById(reactionDTOreq.getVideo_channel_id().getVideo().getId())).thenReturn(Optional.of(new Video()));
        when(reactionRepository.findById(videoChannelId)).thenReturn(Optional.empty());
        when(reactionRepository.save(reaction)).thenReturn(reaction);
        when(modelMapper.map(reaction, ReactionDTOres.class)).thenReturn(new ReactionDTOres());

        ReactionDTOres result = reactionService.save(reactionDTOreq);

        assertNotNull(result);
        verify(modelMapper).map(reactionDTOreq, Reaction.class);
        verify(channelRepository).findById(reactionDTOreq.getVideo_channel_id().getChannel().getId());
        verify(videoRepository).findById(reactionDTOreq.getVideo_channel_id().getVideo().getId());
        verify(reactionRepository).findById(videoChannelId);
        verify(reactionRepository).save(reaction);
        verify(modelMapper).map(reaction, ReactionDTOres.class);
    }

    @Test
    void findById_existingVideoChannelId_returnReactionDTO() {
        Video_channel_Id videoChannelId = new Video_channel_Id();
        Reaction reaction = new Reaction();
        when(reactionRepository.findById(videoChannelId)).thenReturn(Optional.of(reaction));
        when(modelMapper.map(reaction, ReactionDTOres.class)).thenReturn(new ReactionDTOres());

        ReactionDTOres result = reactionService.findById(videoChannelId);

        assertNotNull(result);
        verify(reactionRepository).findById(videoChannelId);
        verify(modelMapper).map(reaction, ReactionDTOres.class);
    }

    @Test
    void findById_nonExistingVideoChannelId_throwResourceNotFoundException() {
        Video_channel_Id videoChannelId = new Video_channel_Id();
        when(reactionRepository.findById(videoChannelId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reactionService.findById(videoChannelId));
        verify(reactionRepository).findById(videoChannelId);
        verifyNoInteractions(modelMapper);
    }

    @Test
    void deleteById_existingVideoChannelId_reactionDeletedAndMappedToDTO() {
        Video_channel_Id videoChannelId = new Video_channel_Id();
        Reaction reaction = new Reaction();
        when(reactionRepository.findById(videoChannelId)).thenReturn(Optional.of(reaction));
        when(modelMapper.map(reaction, ReactionDTOreq.class)).thenReturn(new ReactionDTOreq());

        ReactionDTOreq result = reactionService.deleteById(videoChannelId);

        assertNotNull(result);
        verify(reactionRepository).findById(videoChannelId);
        verify(reactionRepository).deleteById(videoChannelId);
        verify(modelMapper).map(reaction, ReactionDTOreq.class);
    }

    @Test
    void deleteById_nonExistingVideoChannelId_throwResourceNotFoundException() {
        Video_channel_Id videoChannelId = new Video_channel_Id();
        when(reactionRepository.findById(videoChannelId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reactionService.deleteById(videoChannelId));
        verify(reactionRepository).findById(videoChannelId);
        verifyNoMoreInteractions(reactionRepository);
        verifyNoInteractions(modelMapper);
    }


}
