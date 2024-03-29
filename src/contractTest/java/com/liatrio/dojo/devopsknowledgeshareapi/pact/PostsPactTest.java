package com.liatrio.dojo.devopsknowledgeshareapi.pact;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;

import com.liatrio.dojo.devopsknowledgeshareapi.Post;
import com.liatrio.dojo.devopsknowledgeshareapi.PostController;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@ExtendWith(SpringExtension.class)
@Provider("DKS API")
@Consumer("DKS UI")
@PactBroker
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PostsPactTest {

  @Autowired
  private PostController postController;

  Post post = new Post();

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void verifyPact(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("Posts list provided")
  public void postsAreRetrievedWithTheGivenFields() throws Exception {
    Date date = new Date();
    post.setId(0L);
    post.setFirstName("Ethan");
    post.setTitle("Awesome Blog");
    post.setLink("https://blog.com");
    post.setDatePosted(date);
    post.setImageUrl("https://www.example.com/blog/post-1/img.png");
    postController.post(post, null);
  }
}
