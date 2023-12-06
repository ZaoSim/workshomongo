package com.example.workshomongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshomongo.domain.User;
import com.example.workshomongo.dto.UserDto;
import com.example.workshomongo.repository.UserRespository;
import com.example.workshomongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRespository userRepository;
    
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
    // Buscar o usuário pelo ID
    Optional<User> userOptional = userRepository.findById(id);
    // Verificar se o usuário foi encontrado ou lançar uma exceção personalizada
    User user = userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    // Retornar o usuário encontrado
    return user;
    }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);

    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDto objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
