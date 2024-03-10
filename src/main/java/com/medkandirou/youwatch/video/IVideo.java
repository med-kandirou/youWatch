package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.helpers.IData;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface IVideo extends IData<VideoDTOreq,VideoDTOres,Long> {
    Page<VideoDTOres> paginate(Pageable pageable);
}
