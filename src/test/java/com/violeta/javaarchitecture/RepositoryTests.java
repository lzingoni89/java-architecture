package com.violeta.javaarchitecture;

import com.violeta.javaarchitecture.domain.entity.Contact;
import com.violeta.javaarchitecture.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired
    ContactRepository contactRepository;

    @Test
    void createContact() {
        var contact = new Contact();
        contact.setFirstName("Leandro");
        contact.setLastName("Zingoni");
        contact.setEmail("lzingoni89@gmail.com");
        contact = contactRepository.saveAndFlush(contact);
        Assert.notNull(contact.getId(), "lalalala");
    }

}
