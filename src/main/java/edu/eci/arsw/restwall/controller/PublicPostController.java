package edu.eci.arsw.restwall.controller;

import edu.eci.arsw.restwall.auth.models.UserDto;
import edu.eci.arsw.restwall.auth.models.*;
import edu.eci.arsw.restwall.model.Post;
import edu.eci.arsw.restwall.model.User;
import edu.eci.arsw.restwall.service.PostService;
import edu.eci.arsw.restwall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class PublicPostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("test")
    ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList = postService.getAllPost();
        return ResponseEntity.ok(postList);
    }
    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostofUser(@PathVariable Integer userId){
        List<PostDto> postList = postService.getPostsOfUser(userId);
        return ResponseEntity.ok(postList);
    }
    @PostMapping("addpost/{email}")
    public ResponseEntity<?> addPost(PostDto post,@PathVariable String email) throws NullPointerException {
    	UserDto userDto = new UserDto();
    	userDto.setEmail(email);
        Post savedPost = postService.savePost(userDto,post.getContent());
        return ResponseEntity.ok(savedPost);
    }
    

}