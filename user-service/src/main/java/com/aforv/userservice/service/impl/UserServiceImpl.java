package com.aforv.userservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aforv.userservice.dto.DepartmentDto;
import com.aforv.userservice.dto.ResponseDto;
import com.aforv.userservice.dto.UserDto;
import com.aforv.userservice.entity.User;
import com.aforv.userservice.repository.UserRepository;
import com.aforv.userservice.service.APIClient;
import com.aforv.userservice.service.UserService;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private APIClient apiClient;

    @Value("${department.baseUrl}")
    private static String departmentServiceUrl;

//    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

       // DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());
      
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate
//                .getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + user.getDepartmentId(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        System.out.println(responseEntity.getStatusCode());

        DepartmentDto departmentDto = webClientBuilder.build().get()
                .uri("http://DEPARTMENT-SERVICE/api/departments/" + user.getDepartmentId())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

   //     DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());
        
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}