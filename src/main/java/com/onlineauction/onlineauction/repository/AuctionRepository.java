package com.onlineauction.onlineauction.repository;

import com.onlineauction.onlineauction.model.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, Long> {
    // custom queries if needed
}
