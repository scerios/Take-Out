package practice.takeout.service;

import practice.takeout.model.Address;

public interface AddressService {
  void addAddress(Address address);
  Address getAddressById(long id);
}
