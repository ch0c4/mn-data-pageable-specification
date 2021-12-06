package org.ch0c4.repositories;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Join.Type;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import org.ch0c4.entities.Account;

@Repository
public interface AccountRepository
    extends PageableRepository<Account, Long>, JpaSpecificationExecutor<Account> {

  @Join(value = "customer", type = Type.FETCH)
  Page<Account> list(Pageable pageable);
}
