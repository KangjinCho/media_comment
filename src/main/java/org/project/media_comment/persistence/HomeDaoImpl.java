package org.project.media_comment.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDaoImpl implements HomeDao {
	@Autowired
	private SqlSession session;
	
	// mapper 네임스페이스
	private static String namespace = "org.project.media_comment.mapper.homeMapper";

	@Override
	public List<Object> getVideoCode() throws Exception {
		return session.selectList(namespace + ".getVideoCode");
	}
}
