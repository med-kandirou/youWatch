package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.helpers.IData;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideo extends IData<VideoDTOreq,VideoDTOres,Long> {
    Page<VideoDTOres> paginate(Pageable pageable);
    List<VideoDTOres> getvideoByChannel(Long channelId);

    List<VideoDTOres> search(String inputSearch);
    List<VideoDTOres> trending();

}
