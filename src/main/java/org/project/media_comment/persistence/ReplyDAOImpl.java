package org.project.media_comment.persistence;

import org.apache.ibatis.session.SqlSession;
import org.project.media_comment.domain.ReplyVO;
import org.project.media_comment.domain.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hs on 2017-07-22.
 */

@Repository
public class ReplyDAOImpl implements ReplyDAO{


    @Autowired
    private SqlSession session;

    //mapper 네임스페이스
    private static String namespace = "org.project.media_comment.mapper.replyMapper";

    @Override
    public List<ReplyVO> listAllReply(int video_id) throws Exception {
        return session.selectList(namespace+".listAllReply",video_id);
    }

    @Override
    public void insertReply(ReplyVO vo) throws Exception {
        session.insert(namespace+".insertReply",vo);
    }
}