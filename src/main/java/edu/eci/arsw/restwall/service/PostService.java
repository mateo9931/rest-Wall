package edu.eci.arsw.restwall.service;

import edu.eci.arsw.restwall.auth.models.UserDto;
import edu.eci.arsw.restwall.auth.models.PostDto;
import edu.eci.arsw.restwall.model.Post;
import edu.eci.arsw.restwall.model.User;
import edu.eci.arsw.restwall.repository.PostRepository;
import edu.eci.arsw.restwall.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;



    public Post savePost(UserDto userDto, String content){
        Post post = new Post();
        User user = userRepository.findUserByEmail(userDto.getEmail());
        post.setUser(user);
        post.setContent(content);
        post.setCreatedDate(new Date());
        return postRepository.save(post);
    }

    public List<PostDto> getPostsOfUser(Integer userId){
        List<Post> postList= postRepository.findPostByUserOrderById(userRepository.findUserById(userId));
        List<PostDto> postDtoList= new ArrayList<>();
        for (Post post :postList) {
            postDtoList.add(modelMapper.map(post,PostDto.class));
        }
        return postDtoList;
    }

    public List<Post> getAllPost(){
        System.out.println(postRepository.findAllByOrderByIdDesc());
        return postRepository.findAllByOrderByIdDesc();
    }

}