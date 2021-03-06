package org.project.media_comment.service;

import org.project.media_comment.domain.*;
import org.project.media_comment.persistence.PercentMapDAO;
import org.project.media_comment.persistence.ReplyDAO;
import org.project.media_comment.util.PercentMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hs on 2017-07-22.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;
    @Autowired
    private PercentMapDAO percentMapDAO;
    @Autowired
    private PercentMapUtil percentMapUtil;
    @Autowired
    private PercentMapService percentMapService;

    @Override
    public List<ReplyVO> listAllReply(int video_id,int user_id) throws Exception {
        if(user_id==0){
            return replyDAO.listAllReply(video_id);
        }
            return replyDAO.listAllReplyLogin(new VideoUserVO(user_id,video_id));
    }

    @Override
    public void insertReply(ReplyVO vo) throws Exception {
        replyDAO.insertReply(vo);


        PercentMapVO mapVO=new PercentMapVO(vo.getReply_id(),percentMapUtil.defaultMap());

        System.out.println("------------------"+mapVO.getMapStr());

        percentMapDAO.insertDefaultMap(mapVO);


    }

    @Override
    public int voteReply(ReplyVoteVO vo) throws Exception {

        Integer flag=vo.getReply_vote_flag();
        int reply_id=vo.getReply_id();
        Integer result=null;
        result=replyDAO.selectReplyVote(vo);

        //-1 : dislike
        // 0 : default
        // 1 : like
        if(result==null){ //기록이 없으면 insert
            replyDAO.voteReply(vo);
            result=flag;
            if(flag==1) {
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 1, 0));
            }else{
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 0, 1));
            }
        }else if(result.equals(flag)){//중복 선택 -> default로
            if(flag==1){//이전 상태가 like이었으면
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, -1, 0));
            }else{
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 0, -1));
            }
            replyDAO.updateReplyVote(new ReplyVoteVO(reply_id,0));
            result=0;
        }else if(!result.equals(flag)){//다른 선택 -> 다른선택으로
            if(result==1&&flag==-1){//like->dislike
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, -1, 1));
                result=-2;
            }else if(result==0&&flag==1){
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 1, 0));
                result=1;
            }else if(result==0&&flag==-1){
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 0, 1));
                result=-1;
            }else{ //dislike->like
                replyDAO.updateReplyCount(new ReplyCountVO(reply_id, 1, -1));
                result=2;
            }
            replyDAO.updateReplyVote(new ReplyVoteVO(reply_id,vo.getReply_vote_flag()));
        }

        return result;
    }

    @Override
    public List<ReplyVO> listBestReply(int video_id) throws Exception {

        List<ReplyVO> list = replyDAO.listBestReply(video_id);

//        if(list!=null) {
//
//            for (ReplyVO vo : list) {
//
//                PercentMapVO tmp = percentMapService.getPercentMapByReplyId(vo.getReply_id());
//                int[] tmpXY = percentMapUtil.sampling(tmp.getMap());
//
//                vo.setReply_x(tmpXY[0]);
//                vo.setReply_y(tmpXY[1]);
//            }
//        }

        return list;
    }

    @Override
    public void updateReplyPos(ReplyPosVO vo) throws Exception {
        replyDAO.updateReplyPos(vo);
    }
}
