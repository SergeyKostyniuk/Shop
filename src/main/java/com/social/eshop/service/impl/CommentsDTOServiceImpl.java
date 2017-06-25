package com.social.eshop.service.impl;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.service.CommentsDTOService;
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Service
@Transactional
public class CommentDTOServiceImpl {

    private final Logger log = LoggerFactory.getLogger(CommentDTOServiceImpl.class);
    private final CommentsRepository commentRepository;
    private final CustomerDTO authorDTOServiceImpl;

    public CommentDTOServiceImpl(CommentsRepository commentRepository, CustomerDTO authorDTOServiceImpl) {
        this.commentRepository = commentRepository;
        this.authorDTOServiceImpl = authorDTOServiceImpl;
    }

    public CommentsDTO findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        Comments comments = commentRepository.getOne(id);
        return createCommentDTOfromComment (comments);
    }

    public List<CommentsDTO> findAllByPost(Long id) {
        log.debug("Request to get Comments by post : {}", id);
        List<Comments> commentList = commentRepository.findByPostId(id);
        List<CommentsDTO> commentDTOList = new ArrayList<>();
        commentList.forEach((comment) -> {commentDTOList.add(createCommentDTOfromComment (comment));});
        return commentDTOList;
    }

    private CommentsDTO createCommentDTOfromComment (Comments comment) {
        CustomerDTO authorDTO = authorDTOServiceImpl.findOne(comment.());
        CommentDTO commentDTO = new CommentDTO();
        try {
            commentDTO.mappingToDTO(comment, authorDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(CommentDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentDTO;
    }

}
