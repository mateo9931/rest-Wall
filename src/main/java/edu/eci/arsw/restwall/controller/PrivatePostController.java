package edu.eci.arsw.restwall.controller;

import edu.eci.arsw.restwall.auth.SecurityService;
import edu.eci.arsw.restwall.auth.models.UserDto;
import edu.eci.arsw.restwall.dtos.PostDto;
import edu.eci.arsw.restwall.exceptions.UnauthorizedException;
import edu.eci.arsw.restwall.model.Post;
import edu.eci.arsw.restwall.model.User;
import edu.eci.arsw.restwall.repository.UserRepository;
import edu.eci.arsw.restwall.service.PostService;
import edu.eci.arsw.restwall.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("private")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrivatePostController {

    @Autowired
    UserService userService;



    @Autowired
    PostService postService;



    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SecurityService securityService;

    @GetMapping("user-details")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDto userDto) {
        User user = userService.getUser(userDto.getEmail());
        return ResponseEntity.ok(user);
    }

    @GetMapping("saveUser")
    public ResponseEntity<UserDto> saveUserInfo(@AuthenticationPrincipal UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    /*@GetMapping("addFriend")
    public ResponseEntity<?> addUserAsFriend(@AuthenticationPrincipal UserDto userDto){
        User user = userRepository.getOne(2);
        UserDto userDto2 = modelMapper.map(user,UserDto.class);
        friendService.saveFriend(userDto,userDto2);
        return ResponseEntity.ok("ok");
    }*/







    @PostMapping("addpost")
    public ResponseEntity<?> addPost(@RequestBody Post post) throws NullPointerException {
        UserDto user = securityService.getUser();
        Post savedPost = postService.savePost(user,post.getContent());
        return ResponseEntity.created(URI.create("/private/mypost")).body(savedPost);
    }

    @GetMapping("mypost")
    public ResponseEntity<?> myPosts() throws NullPointerException {
        User user=userService.getUser(securityService.getUser().getEmail());
        List<PostDto> postList = postService.getPostsOfUser(user.getId());
        return ResponseEntity.ok(postList);
    }


}
