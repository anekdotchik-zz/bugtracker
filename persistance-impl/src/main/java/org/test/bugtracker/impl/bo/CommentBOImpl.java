package org.test.bugtracker.impl.bo;

import org.test.bugtracker.bo.CommentBO;
import org.test.bugtracker.dao.CommentDAO;
import org.test.bugtracker.model.Comment;

public class CommentBOImpl implements CommentBO {
    private static final long serialVersionUID = 529139453786274041L;

    private CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void save(Comment entity) {
        commentDAO.save(entity);
    }

    public Comment findById(Long id) {
        return commentDAO.findById(id);
    }

    public void update(Comment entity) {
        commentDAO.update(entity);
    }

    public void delete(Comment entity) {
        commentDAO.delete(entity);
    }

}
