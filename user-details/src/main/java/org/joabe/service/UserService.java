package org.joabe.service;

import java.util.Optional;

import org.joabe.entity.User;
import org.joabe.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
  @Inject
  UserRepository userRepository;

  public Optional<User> findById(String id) {
    Optional<User> user = userRepository.findByIdOptional(Long.parseLong(id));
    return user;
  }
}
