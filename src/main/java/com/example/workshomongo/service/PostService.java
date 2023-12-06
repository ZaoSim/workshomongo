package com.example.workshomongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshomongo.domain.Post;
import com.example.workshomongo.repository.PostUserRespository;
import com.example.workshomongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
    
    @Autowired
    private PostUserRespository postRepository;
    
    public Post findById(String id){
    // Buscar o usuário pelo ID
    Optional<Post> userOptional = postRepository.findById(id);
    // Verificar se o usuário foi encontrado ou lançar uma exceção personalizada
    Post post = userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    // Retornar o usuário encontrado
    return post;
    }

    // public List<Post> findByTitle(String text){
    //     return postRepository.findByTitleContainingIgnoreCase(text);
    // }
    public List<Post> findByTitle(String text){
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
