package ch.bbw.service;

import ch.bbw.entity.User;
import ch.bbw.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    if (userRepository.findByUsername("admin") == null) {
      User admin = new User();
      admin.setUsername("admin");
      admin.setPassword(passwordEncoder.encode("adminPW"));
      admin.setRole("ADMIN");
      userRepository.save(admin);
    }

    if (userRepository.findByUsername("user") == null) {
      User user = new User();
      user.setUsername("user");
      user.setPassword(passwordEncoder.encode("userPW"));
      user.setRole("USER");
      userRepository.save(user);
    }
  }

  public Optional<User> findByUsername(String username) {
    return Optional.ofNullable(userRepository.findByUsername(username));
  }
}
