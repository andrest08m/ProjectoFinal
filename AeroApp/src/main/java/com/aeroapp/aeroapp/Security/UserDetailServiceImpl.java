package com.aeroapp.aeroapp.Security;

import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Customer customer = customerRepository.
                findOneByName(name)
                .orElseThrow(()-> new UsernameNotFoundException("The reservation code does not exist."));

        return new UserDetailsImp(customer);
    }

}
