package org.ch0c4;

import io.micronaut.data.jpa.repository.criteria.Specification;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort.Order;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.ch0c4.entities.Account;
import org.ch0c4.repositories.AccountRepository;
import org.junit.jupiter.api.Test;

@MicronautTest
class MnDataOrderByTest {

  @Inject private AccountRepository repository;

  @Test
  void test_Pageable() {
    // working
    repository.list(Pageable.from(0).order(new Order("customer.title")));
  }

  @Test
  void test_Specification() {
    // working
    repository.findAll(testJoin("title"));
  }

  @Test
  void test_SpecificationAndPageableUnpaged() {
    // working
    repository.findAll(testJoin("title2"), Pageable.unpaged());
  }

  @Test
  void test_SpecificationAndPageable() {
    // not working
    // Invalid path: 'generatedAlias1.title'
    // [select count(generatedAlias0) from org.ch0c4.entities.Account as generatedAlias0 where
    // generatedAlias1.title=:param0]
    repository.findAll(testJoin("title3"), Pageable.from(0));
  }

  @Test
  void test_SpecificationAndPageableJoin() {
    // not working
    // Unable to locate Attribute  with the the given name [customer.title] on this ManagedType
    // [org.ch0c4.entities.Account]
    repository.findAll(testJoin("title4"), Pageable.from(0).order(new Order("customer.title")));
  }

  private static Specification<Account> testJoin(String value) {
    return ((root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.join("customer").get("title"), value));
  }
}
