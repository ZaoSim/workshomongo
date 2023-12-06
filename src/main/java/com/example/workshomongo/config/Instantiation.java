package com.example.workshomongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshomongo.domain.Post;
import com.example.workshomongo.domain.User;
import com.example.workshomongo.dto.AuthorDTO;
import com.example.workshomongo.dto.CommentDTO;
import com.example.workshomongo.repository.PostUserRespository;
import com.example.workshomongo.repository.UserRespository;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private PostUserRespository postUserRespository;

    @Autowired
    private UserRespository userRespository;

    @Override
    public void run(String... args) throws Exception {
      
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRespository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRespository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, sdf.parse("06/08/2023"), "Viagens", "É muito bom viajar", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("06/08/2023"), "Cozinha", "É muito bom cozinhar", new AuthorDTO(maria));
        
        CommentDTO c1 = new CommentDTO("Boa viagem", sdf.parse("07/08/2023"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Muito bom", sdf.parse("10/08/2023"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Perfeito", sdf.parse("07/09/2023"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));
        

        postUserRespository.saveAll(Arrays.asList(post1,post2));
        maria.getPost().addAll(Arrays.asList(post1, post2));
        userRespository.save(maria);
    }
    
}
