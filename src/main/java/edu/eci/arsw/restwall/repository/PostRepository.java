package edu.eci.arsw.restwall.repository;

import edu.eci.arsw.restwall.model.Post;
import edu.eci.arsw.restwall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostByUserOrderById(User user);

    List<Post> findAllByOrderByIdDesc();
}