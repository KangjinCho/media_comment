package org.project.media_comment.service;

import org.project.media_comment.domain.PercentMapVO;
import org.project.media_comment.domain.VideoVO;

import java.util.List;

public interface PercentMapService {
	public PercentMapVO getPercentMap(int percentMapId)throws Exception;
	public PercentMapVO getPercentMapByReplyId(int reply_id)throws Exception;
	public void makeNewMapConnectedWithNewComment(int replyId,PercentMapVO vo)throws Exception;
	public void updateANDmapOrdering(PercentMapVO vo)throws Exception;
}
