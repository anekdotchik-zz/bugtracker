package org.test.bugtracker.impl.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.bugtracker.bo.CommentBO;
import org.test.bugtracker.dao.CommentDAO;
import org.test.bugtracker.model.Comment;

@Repository
public class CommentBOImpl implements CommentBO {
    private static final long serialVersionUID = 529139453786274041L;
    
    @Autowired
    private CommentDAO commentDAO;

    @Transactional
    public void save(Comment entity) {
        commentDAO.save(entity);
    }

    @Transactional
    public Comment findById(Long id) {
        return commentDAO.findById(id);
    }

    @Transactional
    public void update(Comment entity) {
        commentDAO.update(entity);
    }
    
    @Transactional
    public void delete(Comment entity) {
        commentDAO.delete(entity);
    }

}
