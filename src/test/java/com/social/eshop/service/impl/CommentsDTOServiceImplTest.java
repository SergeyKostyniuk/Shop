package com.social.eshop.service.impl;

import com.social.eshop.domain.Comments;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.service.dto.CommentsDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CommentsDTOServiceImplTest {

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private CustomerRepository customerRepository;


    //@Autowired
    private CommentsDTOServiceImpl sut = new CommentsDTOServiceImpl(commentsRepository, customerRepository);

    @Test
    public void findOne() throws Exception {

        CommentsDTO comments =  sut.findOne(1001L);
        //assertThat(commentsDTO.getCustomerDTO().getPersonalInformation().getFirstName()).isEqualTo("Vasy");

        if (comments == null) System.out.println("ISKYSTVINII INTELEKT RULIT");//System.out.println(commentsDTO.toString());
    }
}
