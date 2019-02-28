package framework.jpa.repo;

import org.springframework.stereotype.Repository;

import coliseum.jpa.repo.BaseRepo;
import framework.jpa.entity.Address;

@Repository
public interface AddressRepo extends BaseRepo<Address, Long> {
}
