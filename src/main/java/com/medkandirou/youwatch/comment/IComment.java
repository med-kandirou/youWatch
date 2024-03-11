package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.IData;
import com.medkandirou.youwatch.helpers.Video_channel_Id;

import java.util.List;

public interface IComment extends IData<CommentDTOreq,CommentDTOres, Video_channel_Id> {

    List<CommentDTOres> getCommentsByVideo(Long videoId);
}
